package socket.gsm.admin.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import socket.gsm.admin.dao.LockEndRideMapper;

/**
 * @author fangyunhe
 * @date 2017年9月6日 下午5:55:18
 * 
 */
@Service
public class LockEndRideService {
	@Resource
	LockEndRideMapper lockEndRideMapper;

	public List<socket.gsm.admin.bean.LockEndRide> queryInMacWithDate(Date start, Date end, String[] macArray) {
		return lockEndRideMapper.queryInMacWithDate(start,end,macArray);
	}
}
