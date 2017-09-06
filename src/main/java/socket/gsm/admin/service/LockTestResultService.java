package socket.gsm.admin.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import socket.gsm.admin.bean.LockTestResult;
import socket.gsm.admin.dao.LockTestResultMapper;

/**
 * @author fangyunhe
 * @date 2017年9月6日 上午9:56:00
 * 
 */
@Service
public class LockTestResultService {
	@Resource
	LockTestResultMapper lockTestResultMapper;

	public List<LockTestResult> queryInMacWithDate(Date start, Date end, String[] macArray) {
		return lockTestResultMapper.queryInMacWithDate(start,end,macArray);
	}

}
