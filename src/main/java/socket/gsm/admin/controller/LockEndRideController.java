package socket.gsm.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import socket.gsm.admin.bean.LockEndRide;
import socket.gsm.admin.bean.LockLocation;
import socket.gsm.admin.service.LockEndRideService;
import socket.gsm.admin.vo.LockEndRideVo;

/**
 * @author fangyunhe
 * @date 2017年9月6日 下午5:55:01
 * 
 */
@RestController
@RequestMapping(value="/end")
public class LockEndRideController extends BaseController{
	
	@Resource
	LockEndRideService lockEndRideService; 
	
	/**
	 * 结束计费情况明细
	 * @param otaStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryDetail",method=RequestMethod.GET)
    public Object queryDetail(String startDate,String endStart,String macs){  
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
			List<LockEndRide> queryInMacWithDate = lockEndRideService.queryInMacWithDate(start, end, macArray);
        	return responseSuccess("结束计费情况明细成功", queryInMacWithDate);
        } catch (Exception e) {
			logger.error("结束计费情况明细失败",e);
			return responseFail("结束计费情况明细失败");
		}
    }
	
	/**
	 * 结束计费发送请求成功率统计
	 * @param startDate
	 * @param endStart
	 * @param macs
	 * @return
	 */
	@RequestMapping(value="/lockEndRideReqSuccRadio",method=RequestMethod.GET)
    public Object lockEndRideReqSuccRadio(String startDate,String endStart,String macs){  
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
			List<LockEndRideVo> lockEndRideReqSuccRadio = lockEndRideService.lockEndRideReqSuccRadio(start, end, macArray);
        	return responseSuccess("结束计费发送请求成功率统计成功", lockEndRideReqSuccRadio);
        } catch (Exception e) {
			logger.error("结束计费发送请求成功率统计失败",e);
			return responseFail("结束计费发送请求成功率统计失败");
		}
    }
	
}
