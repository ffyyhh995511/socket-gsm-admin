package socket.gsm.admin.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import socket.gsm.admin.bean.LockTestResult;
import socket.gsm.admin.commons.OpenPage;
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

	public OpenPage queryInMacWithDate(Date start, Date end, String[] macArray,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<LockTestResult> list = lockTestResultMapper.queryInMacWithDate(start,end,macArray);
		Page<LockTestResult> p = ((Page<LockTestResult>) list);
	    return OpenPage.buildPage(p);
	}

}
