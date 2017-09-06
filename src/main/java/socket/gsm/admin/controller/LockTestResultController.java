package socket.gsm.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import socket.gsm.admin.bean.LockLocation;
import socket.gsm.admin.bean.LockTestResult;
import socket.gsm.admin.service.LockTestResultService;

/**
 * @author fangyunhe
 * @date 2017年9月5日 下午5:06:36
 * 
 */
@RestController
@RequestMapping(value="/lockTestResult")
public class LockTestResultController extends BaseController{
	@Resource
	LockTestResultService lockTestResultService;
	
	/**
	 * 锁测工具结果情况明细
	 * @param otaStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryDetail",method=RequestMethod.GET)
    public Object queryDetail(String startDate,String endStart,String macs){  
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date start = null;
		Date end = null;
		String[] macArray = null;
		try {
			if(StringUtils.isNoneBlank(startDate)){
				start = sdf.parse(startDate);
			}
			if(StringUtils.isNoneBlank(endStart)){
				end = sdf.parse(endStart);
			}
			if(StringUtils.isNoneBlank(macs)){
				macArray = macs.split(",");
			}
			
			List<LockTestResult> queryInMacWithDate = lockTestResultService.queryInMacWithDate(start, end, macArray);
        	return responseSuccess("锁测工具结果明细成功", queryInMacWithDate);
        } catch (Exception e) {
			logger.error("锁测工具结果明细失败",e);
			logger.error("错误参数"+getParameterMap());
			return responseFail("锁测工具结果明细失败");
		}
    }
	
}
