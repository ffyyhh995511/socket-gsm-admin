package socket.gsm.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import socket.gsm.admin.bean.LockEndRide;
import socket.gsm.admin.dao.LockEndRideMapper;
import socket.gsm.admin.vo.LockEndRideVo;

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

	public List<LockEndRideVo> lockEndRideReqSuccRadio(Date start, Date end, String[] macArray) {
		List<LockEndRide> queryInMacWithDateList = lockEndRideMapper.queryInMacWithDate(start,end,macArray);
		Map<String, List<LockEndRide>> groupByMac = groupByMac(queryInMacWithDateList);
		List<LockEndRideVo> calcEndRideFeeTime = calcEndRideFeeTime(groupByMac);
		return calcEndRideFeeTime;
	}
	
	/**
	 * 安mac分组
	 * @param queryInMacWithDateList
	 * @return
	 */
	public Map<String,List<LockEndRide>> groupByMac(List<LockEndRide> queryInMacWithDateList){
		Map<String,List<LockEndRide>> map = new HashMap<String,List<LockEndRide>>();
		if(CollectionUtils.isNotEmpty(queryInMacWithDateList)){
			for (LockEndRide lockEndRide : queryInMacWithDateList) {
				if(map.get(lockEndRide.getMac()) == null){
					List<LockEndRide> itemList = new ArrayList<LockEndRide>();
					itemList.add(lockEndRide);
					map.put(lockEndRide.getMac(),itemList);
				}else{
					List<LockEndRide> list = map.get(lockEndRide.getMac());
					list.add(lockEndRide);
				}
			}
		}
		return map;
	}
	
	/**
	 * 结束计费发送请求成功率统计
	 * @param map
	 * @return
	 */
	public List<LockEndRideVo> calcEndRideFeeTime(Map<String,List<LockEndRide>> map){
		List<LockEndRideVo> list = new ArrayList<LockEndRideVo>();
		for(Map.Entry<String,List<LockEndRide>> itemMap : map.entrySet()){
			LockEndRideVo vo = new LockEndRideVo();
			vo.setMac(itemMap.getKey());
			if(CollectionUtils.isNotEmpty(itemMap.getValue())){
				vo.setRequestEndRideFeeTime(itemMap.getValue().size());
			}else{
				vo.setRequestEndRideFeeTime(0);
			}
			int openLockSuccTime = 0;
			List<LockEndRide> value = itemMap.getValue();
			if(CollectionUtils.isNotEmpty(value)){
				for (LockEndRide lockEndRide : value) {
					String payload = lockEndRide.getPayload();
					Map<String,String> parseObject = JSON.parseObject(payload, Map.class);
					String obt = parseObject.get("OBT");
					String[] split = obt.split("#");
					String openLockTime = split[0];
					//锁梁打开时间小于50毫秒算成功
					if(Integer.parseInt(openLockTime) <= 5){
						openLockSuccTime++;
					}
				}
			}
			vo.setOpenLockSuccTime(openLockSuccTime);
			vo.setOpenLockFailTime(vo.getRequestEndRideFeeTime()-vo.getOpenLockSuccTime());
			vo.setRequestEndRideFeeRadio((double) (vo.getOpenLockSuccTime()/vo.getRequestEndRideFeeTime()));
			list.add(vo);
		}
		return list;
	}
	
	
}
