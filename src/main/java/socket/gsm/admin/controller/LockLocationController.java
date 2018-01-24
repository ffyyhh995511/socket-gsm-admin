package socket.gsm.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import socket.gsm.admin.bean.LockLocation;
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.service.LockLocationService;
import socket.gsm.admin.vo.LocationSummarizeVo;
/***
 * 定位相关
 * @author fangyunhe
 *
 * 2017年8月31日 下午2:14:33
 */
@RestController
@RequestMapping(value="/location")
public class LockLocationController extends BaseController{
	
	@Resource
	LockLocationService LockLocationService;
	
	/**
	 * GPS定位情况明细
	 * @param otaStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryLockLocationDetail",method=RequestMethod.GET)
    public Object queryLockLocationDetail(String startDate,String endStart,String macs,Integer pageNum,Integer pageSize){  
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 :pageSize; 
		pageSize = pageSize > 500 ? 500 : pageSize;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			
			OpenPage queryInMacWithDate = LockLocationService.queryInMacWithDate(start, end, macArray,pageNum, pageSize);
        	return responseSuccess("GPS定位情况明细成功", queryInMacWithDate);
        } catch (Exception e) {
			logger.error("GPS定位情况明细失败",e);
			return responseFail("GPS定位情况明细失败");
		}
    }
	
	/**
	 * GPS定位情况汇总
	 * @param startDate
	 * @param endStart
	 * @param macs
	 * @return
	 */
	@RequestMapping(value="/LockLocationSummarize",method=RequestMethod.GET)
	public Object LockLocationSummarize(String startDate,String endStart,String macs){
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
			List<LocationSummarizeVo> lockLocationSummarize = LockLocationService.LockLocationSummarize(start, end, macArray);
			return responseSuccess("GPS定位情况汇总成功", lockLocationSummarize);
		} catch (Exception e) {
			logger.error("GPS定位情况汇总失败",e);
			return responseFail("GPS定位情况汇总失败");
		}
	}
	
	/**
	 * 基站定位情况汇总
	 * @param startDate
	 * @param endStart
	 * @param macs
	 * @return
	 */
	@RequestMapping(value="/lbsLocationSummarize",method=RequestMethod.GET)
	public Object lbsLocationSummarize(String startDate,String endStart,String macs){
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
			List<LocationSummarizeVo> lockLocationSummarize = LockLocationService.LockLocationSummarize(start, end, macArray);
			return responseSuccess("GPS定位情况汇总成功", lockLocationSummarize);
		} catch (Exception e) {
			logger.error("GPS定位情况汇总失败",e);
			return responseFail("GPS定位情况汇总失败");
		}
	}
	
	/**
	 * 所有定位情况汇总
	 * @param startDate
	 * @param endStart
	 * @param macs
	 * @return
	 */
	@RequestMapping(value="/allLocationSummarize",method=RequestMethod.GET)
	public Object allLocationSummarize(String startDate,String endStart,String macs){
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
			List<LocationSummarizeVo> lockLocationSummarize = LockLocationService.allkLocationSummarize(start, end, macArray);
			return responseSuccess("所有定位情况汇总成功", lockLocationSummarize);
		} catch (Exception e) {
			logger.error("所有定位情况汇总失败",e);
			return responseFail("所有定位情况汇总失败");
		}
	}
	
	
	
}
