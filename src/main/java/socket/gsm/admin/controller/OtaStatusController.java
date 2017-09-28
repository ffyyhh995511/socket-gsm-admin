package socket.gsm.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import socket.gsm.admin.bean.OtaStatus;
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.service.OtaStatusService;
import socket.gsm.admin.vo.OtaVo;
/**
 *  固件安装结果上报
 * 
 *  0：安装失败
 *	1：安装成功
 *	2：单包crc错误
 *	3：整包crc错误
 *	4：下载完成
 *	5：解析出来的包id不是请求id
 *	6：易客不需要升级
 * 
 * @author fangyunhe
 *
 */
@Controller
@RequestMapping(value="/otaStatus")
public class OtaStatusController extends BaseController{
	@Resource
	OtaStatusService otaStatusService;
	
	/**
	 * 分页查询
	 * @param otaStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryAll",method=RequestMethod.GET)
    public Object queryAll(OtaStatus otaStatus,Integer pageNum,Integer pageSize,String startTime,String endTime,String status,String macs){  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 :pageSize; 
		pageSize = pageSize > 500 ? 500 : pageSize;
		Date start = null;
		Date end = null;
		String[] macArray = null;
        try {
        	if(StringUtils.isNoneBlank(startTime)){
				start = sdf.parse(startTime +" 00:00:00");
			}
			if(StringUtils.isNoneBlank(endTime)){
				end = sdf.parse(endTime + " 23:59:59");
			}
			if(StringUtils.isNoneBlank(macs)){
				macArray = macs.split(",");
			}
			if("-1".equals(status)){
				status = null;
			}
        	OpenPage page = otaStatusService.queryAll(otaStatus, pageNum, pageSize,start,end,status,macArray);
        	return responseSuccess("成功", page);
        } catch (Exception e) {
			logger.error("查询失败",e);
			return responseFail("查询失败");
		}
    }
	
	/**
	 * 统计下载固件、安装固件成功率
	 * @param otaStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/statisDownloadAndInstall",method=RequestMethod.GET)
    public Object statisDownloadAndInstall(String startDate,String endStart,String macs,Integer newHardwareVer,Integer newSoftwareVer){  
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date start = null;
		Date end = null;
		String[] macArray = null;
		try {
			if(StringUtils.isNoneBlank(startDate)){
				start = sdf.parse(startDate +" 00:00:00");
			}
			if(StringUtils.isNoneBlank(endStart)){
				end = sdf.parse(endStart + " 23:59:59");
			}
			if(StringUtils.isNoneBlank(macs)){
				macArray = macs.split(",");
			}
        	OtaVo vo = otaStatusService.statisDownloadAndInstall(start, end, macArray, newHardwareVer, newSoftwareVer);
        	return responseSuccess("统计下载固件、安装固件成功率成功", vo);
        } catch (Exception e) {
        	logger.error("错误参数"+getParameterMap());
			logger.error("统计下载固件、安装固件成功率失败",e);
			return responseFail("统计下载固件、安装固件成功率失败");
		}
    }
	
	
	
}
