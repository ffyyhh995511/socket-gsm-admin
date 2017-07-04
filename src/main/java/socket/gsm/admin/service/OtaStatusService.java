package socket.gsm.admin.service;

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
}
