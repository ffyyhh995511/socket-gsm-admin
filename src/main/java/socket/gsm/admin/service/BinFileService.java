package socket.gsm.admin.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import socket.gsm.admin.utils.CrcUtil;

@Service
public class BinFileService {
	
	private static final Logger log = Logger.getLogger(BinFileService.class);
	
	@Resource
	BinFileMapper binFileMapper;
	
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
                        String path = request.getSession().getServletContext().getRealPath("upload") + File.separator + fileName;  
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

	public OpenPage queryAll(BinFile binFile, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);

	    List<BinFile> list = binFileMapper.queryAll();
	    Page p = ((Page) list);
	    
	    return OpenPage.buildPage(p);
	}
}
