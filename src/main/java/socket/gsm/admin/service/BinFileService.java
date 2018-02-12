package socket.gsm.admin.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import socket.gsm.admin.bean.BinFile;
import socket.gsm.admin.bean.OtaStatus;
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.dao.BinFileMapper;
import socket.gsm.admin.dao.OtaStatusMapper;
import socket.gsm.admin.utils.CrcUtil;
import socket.gsm.admin.vo.BinFileVo;

@Service
public class BinFileService {
	
	private static final Logger log = Logger.getLogger(BinFileService.class);
	
	@Resource
	BinFileMapper binFileMapper;
	
	@Resource
	OtaStatusMapper otaStatusMapper;
	
	
	public int delete(Integer id){
		BinFile binFile = new BinFile();
		binFile.setId(id);
		binFile.setIsDelete(1);
		return binFileMapper.updateByPrimaryKeySelective(binFile);
	}
	
	/**
	 * 固件录入
	 * @param binFile
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public int otaInsert(BinFile binFile,String path) throws Exception{
		binFile.setCreateTime(new Date());
		byte[] bin = getOTAFileBytes(path);
		int code = CrcUtil.crc_16_CCITT_False(bin, bin.length);
		binFile.setCrc(String.valueOf(code));
		binFile.setBinData(bin);
		binFile.setStatus(1);
		binFile.setIsDelete(0);
		return binFileMapper.insert(binFile);
	}
	
	/**
	 * spring mvc文件上传
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public List<String> upload(HttpServletRequest request) throws IllegalStateException, IOException { 
		List<String> list = new ArrayList<String>();
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                    	log.info("上传的文件名"+myFileName);  
                        //重命名上传后的文件名  
                        String fileName = file.getOriginalFilename();  
                        //定义上传路径  
                        String dir = request.getSession().getServletContext().getRealPath("upload");
                        File dirFile = new File(dir);
                        if(!dirFile.exists()){
                        	dirFile.mkdirs();
                        }
                        String path = dir + File.separator + fileName;  
                        File localFile = new File(path);
                        file.transferTo(localFile);
                        list.add(path);
                    }  
                }  
            }  
        }
        return list;
    }
	
	/**
	 * 读取二进制文件
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public byte[] getOTAFileBytes(String fileName) throws Exception {
		List<Byte> list = new ArrayList<Byte>();
		File file = new File(fileName);
		InputStream in = null;
		in = new FileInputStream(file);
		int tempbyte;
		while ((tempbyte = in.read()) != -1) {
			list.add((byte) tempbyte);
		}
		in.close();

		byte binByte[] = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			binByte[i] = list.get(i);
		}
		return binByte;
	}

	public OpenPage queryAll(BinFile binFile, Integer pageNum, Integer pageSize) throws IllegalAccessException, InvocationTargetException {
		PageHelper.startPage(pageNum, pageSize);

	    List<BinFile> list = binFileMapper.queryAll();
	    calcCountByStatusList(list);
	    Page p = ((Page) list);
	    return OpenPage.buildPage(p);
	}
	
	/**
	 * 统计这个估计版本各个状态的数据
	 * 
	 * #  0：安装失败
	 * #  1：安装成功
	 * #  2：单包crc错误
	 * #  3：整包crc错误
	 * #  4：下载完成
	 * #  5：解析出来的包id不是请求id
	 * #  6：易客不需要升级
	 * @param list
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private void calcCountByStatusList(List<BinFile> list) throws IllegalAccessException, InvocationTargetException {
		for (BinFile vo : list) {
			OtaStatus record = new OtaStatus();
			record.setNewHardwareVer(vo.getNewHardwareVer());
			record.setNewSoftwareVer(vo.getNewSoftwareVer());
			record.setOldHardwareVer(vo.getOldHardwareVer());
			record.setOldSoftwareVer(vo.getOldSoftwareVer());
			record.setBinId(vo.getId());
			//升级设备数(下载完成)
			record.setStatus("4");
			Integer downLoadSucc = otaStatusMapper.countByStatus(record);
			//安装成功
			record.setStatus("1");
			Integer installSucc = otaStatusMapper.countByStatus(record);
			//安装失败
			record.setStatus("0");
			Integer installFail = otaStatusMapper.countByStatus(record);
			vo.setDownLoadSucc(downLoadSucc);
			vo.setInstallSucc(installSucc);
			vo.setInstallFail(installFail);
		}
	}

	public int editStatusPassTest(Integer id) {
		//测试通过
		int status = 1;
		BinFile binFile = new BinFile();
		binFile.setId(id);
		binFile.setUpdateTime(new Date());
		binFile.setStatus(status);
		return binFileMapper.updateByPrimaryKeySelective(binFile);
	}

	public int editWhileListMac(Integer id, String whiteListMac) {
		BinFile binFile = new BinFile();
		binFile.setId(id);
		binFile.setUpdateTime(new Date());
		binFile.setWhiteListMac(whiteListMac);
		return binFileMapper.updateByPrimaryKeySelective(binFile);
	}

	public BinFile getById(Integer id) {
		BinFile selectByPrimaryKey = binFileMapper.selectByPrimaryKey(id);
		selectByPrimaryKey.setBinData(null);
		return selectByPrimaryKey;
	}
	
}
