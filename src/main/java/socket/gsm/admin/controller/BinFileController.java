package socket.gsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import socket.gsm.admin.bean.BinFile;
import socket.gsm.admin.bean.OtaStatus;
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.service.BinFileService;

/**
 * 固件后台管理操作
 * @author fangyunhe
 *
 */
@Controller
@RequestMapping(value="/bin")
public class BinFileController extends BaseController{
	
	@Resource
	BinFileService binFileService;
	
	/**
	 * 固件消息上传
	 * @param request
	 * @param binFile
	 * @param matching aa||bb##cc||dd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload",method=RequestMethod.POST)
    public Object upload(HttpServletRequest request,BinFile binFile){
        try {
        	List<String> pathList = binFileService.upload(request);
        	if(pathList.size() != 1){
        		return responseParamFail("固件文件不能为空或上传文件不能多个"); 
        	}
        	int rs = binFileService.otaInsert(binFile,pathList.get(0));
        	if(rs > 0){
        		return responseSuccess("固件信息录入成功", null);
        	}else{
        		return responseSuccess("固件信息部分录入成功", null);
        	}
        	
		} catch (Exception e) {
			logger.error("固件上传失败",e);
			responseFail("固件上传失败");
		}
        return responseSuccess();
    }  
	
	
	/**
	 * 分页查询
	 * @param otaStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryAll",method=RequestMethod.GET)
    public Object queryAll(BinFile binFile,Integer pageNum,Integer pageSize){  
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 :pageSize; 
		pageSize = pageSize > 500 ? 500 : pageSize;
        try {
        	OpenPage page = binFileService.queryAll(binFile, pageNum, pageSize);
        	return responseSuccess("成功", page);
        } catch (Exception e) {
			logger.error("查询失败",e);
			return responseFail("查询失败");
		}
    }
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.GET)
    public Object delete(Integer id){
		if(id == null){
			return responseParamFail("参数不合法");
		}
        try {
        	int res = binFileService.delete(id);
        	if(res > 0){
        		return responseSuccess("删除成功",null);
        	}
        } catch (Exception e) {
			logger.error("删除失败",e);
		}
        return responseFail("删除失败");
    }
	
}
