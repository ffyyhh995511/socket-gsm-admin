package socket.gsm.admin.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import socket.gsm.admin.bean.LockLocation;
import socket.gsm.admin.dao.LockLocationMapper;

/**
 * 
 * @author fangyunhe
 *
 * 2017年8月31日 上午11:47:42
 */

@Service
public class LockLocationService {
	
	@Resource
	LockLocationMapper locationMapper;
	
	public List<LockLocation> queryInMacWithDate(Date start,Date end,String macs[]){
		return locationMapper.queryInMacWithDate(start, end, macs);
	} 
}
