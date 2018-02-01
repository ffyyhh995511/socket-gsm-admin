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
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.service.LockEndRideService;
import socket.gsm.admin.vo.LockEndRideVo;
import socket.gsm.admin.vo.PowerVo;

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
	
	/**
	 * 锁电量区间
	 * @param startDate
	 * @param endStart
	 * @param macs
	 * @return
	 */
	@RequestMapping(value="/lockPower",method=RequestMethod.GET)
    public Object lockPower(String startDate,String endStart,String macs,String power){
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
			PowerVo queryRangePower = lockEndRideService.queryRangePower(start, end, macArray);
        	return responseSuccess("锁电量统计成功", queryRangePower);
        } catch (Exception e) {
			logger.error("锁电量统计失败",e);
			return responseFail("锁电量统计失败");
		}
    }
	
	/**
	 * 结束计费payload details
	 * @param startDate
	 * @param endStart
	 * @param macs
	 * @return
	 */
	@RequestMapping(value="/payloadDetails",method=RequestMethod.GET)
    public Object payloadDetails(String startDate,String endStart,String macs,String power,Integer pageNum,Integer pageSize){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
		Date end = null;
		String[] macArray = null;
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 :pageSize; 
		pageSize = pageSize > 500 ? 500 : pageSize;
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
			OpenPage queryLoadPayLoad = lockEndRideService.queryLoadPayLoad(start, end, macArray,pageNum, pageSize);
        	return responseSuccess("结束计费payload details成功", queryLoadPayLoad);
        } catch (Exception e) {
			logger.error("结束计费payload details失败",e);
			return responseFail("结束计费payload details失败");
		}
    }
	
	/**
	 * 每把锁的最新开锁记录信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/lastTimeCloseInfo",method=RequestMethod.GET)
    public Object lastTimeCloseInfo(Integer pageNum,Integer pageSize){
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 :pageSize;
		pageSize = pageSize > 500 ? 500 : pageSize;
		try {
			OpenPage lastTimeCloseInfo = lockEndRideService.lastTimeCloseInfo(pageNum, pageSize);
        	return responseSuccess("每把锁最新开锁记录信息成功", lastTimeCloseInfo);
        } catch (Exception e) {
			logger.error("每把锁最新开锁记录信息失败",e);
			return responseFail("每把锁最新开锁记录信息失败");
		}
    }
	
	/**
	 * 每把锁开锁历史记录
	 * @param pageNum
	 * @param pageSize
	 * @param mac
	 * @return
	 */
	@RequestMapping(value="/closeLockHisInfo",method=RequestMethod.GET)
    public Object closeLockHisInfo(Integer pageNum,Integer pageSize,String mac){
		if(StringUtils.isBlank(mac)) {
			return responseFail("mac地址不能为空");
		}
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 :pageSize;
		pageSize = pageSize > 500 ? 500 : pageSize;
		try {
			OpenPage page = lockEndRideService.closeLockHisInfo(pageNum, pageSize,mac);
        	return responseSuccess("每把锁开锁历史记录成功", page);
        } catch (Exception e) {
			logger.error("每把锁开锁历史记录失败",e);
			return responseFail("每把锁开锁历史记录失败");
		}
    }
	
}
