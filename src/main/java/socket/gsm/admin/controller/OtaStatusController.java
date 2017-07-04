package socket.gsm.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import socket.gsm.admin.bean.OtaStatus;
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.service.OtaStatusService;
/**
 * 固件安装结果上报
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
    public Object queryAll(OtaStatus otaStatus,Integer pageNum,Integer pageSize){  
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 :pageSize; 
		pageSize = pageSize > 500 ? 500 : pageSize;
        try {
        	OpenPage page = otaStatusService.queryAll(otaStatus, pageNum, pageSize);
        	return responseSuccess("成功", page);
        } catch (Exception e) {
			logger.error("查询失败",e);
			return responseFail("查询失败");
		}
    }
	
}
