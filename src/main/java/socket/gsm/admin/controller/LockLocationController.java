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
import socket.gsm.admin.service.LockLocationService;
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
	 * GPS定位明细
	 * @param otaStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryLockLocationDetail",method=RequestMethod.GET)
    public Object queryLockLocationDetail(String startDate,String endStart,String macs){  
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
			
			List<LockLocation> queryInMacWithDate = LockLocationService.queryInMacWithDate(start, end, macArray);
        	return responseSuccess("成功", queryInMacWithDate);
        } catch (Exception e) {
			logger.error("查询失败",e);
			return responseFail("查询失败");
		}
    }
	
	
}
