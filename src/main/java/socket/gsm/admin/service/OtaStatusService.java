package socket.gsm.admin.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * ota上报相关操作
 * @author fangyunhe
 *
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import socket.gsm.admin.bean.OtaStatus;
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.dao.OtaStatusMapper;
import socket.gsm.admin.vo.OtaVo;
@Service
public class OtaStatusService {
	
	@Resource
	OtaStatusMapper otaStatusMapper;
	
	public OpenPage queryAll(OtaStatus otaStatus,Integer pageNum,Integer pageSize){
		
	    PageHelper.startPage(pageNum, pageSize);

	    List<OtaStatus> list = otaStatusMapper.queryAll();
	    Page p = ((Page) list);
	    
	    return OpenPage.buildPage(p);
	}
	
	
	/**
	 *0：安装失败
	 *1：安装成功
	 *2：单包crc错误
	 *3：整包crc错误
	 *4：下载完成
	 *5：解析出来的包id不是请求id
	 *6：易客不需要升级
	 */
	public OtaVo statisDownloadAndInstall(Date startDate,Date endStart,String macs[],Integer newHardwareVer,Integer newSoftwareVer) {
		OtaVo vo = new OtaVo();
		//安装失败
		String [] installFail = {"0"};
		List<OtaStatus> installFailList = otaStatusMapper.statisDownloadAndInstall(startDate, endStart, macs, newHardwareVer, newSoftwareVer,installFail);
		//安装失败
		String [] installSucc = {"1"};
		List<OtaStatus> installSuccList = otaStatusMapper.statisDownloadAndInstall(startDate, endStart, macs, newHardwareVer, newSoftwareVer,installSucc);
		//下载失败
		String [] downloadFail = {"2","3","5"};
		List<OtaStatus> downloadFailList = otaStatusMapper.statisDownloadAndInstall(startDate, endStart, macs, newHardwareVer, newSoftwareVer,downloadFail);
		//下载成功
		String [] downloadSucc = {"4"};
		List<OtaStatus> downloadSuccList = otaStatusMapper.statisDownloadAndInstall(startDate, endStart, macs, newHardwareVer, newSoftwareVer,downloadSucc);
		vo.setInstallFail(installFailList.size());
		vo.setInstallSucc(installSuccList.size());
		vo.setDownloadFail(downloadFailList.size());
		vo.setDownloadSucc(downloadSuccList.size());
		return vo;
	}
}
