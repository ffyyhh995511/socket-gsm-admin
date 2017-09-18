package socket.gsm.admin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import socket.gsm.admin.bean.LockEndRide;
import socket.gsm.admin.dao.LockEndRideMapper;
import socket.gsm.admin.vo.LockEndRideVo;
import socket.gsm.admin.vo.PowerVo;

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
	 * 各个区间电量统计
	 * @param start
	 * @param end
	 * @param macArray
	 * @return
	 */
	public PowerVo queryRangePower(Date start, Date end, String[] macArray) {
		List<LockEndRide> queryInMacWithDateList = lockEndRideMapper.queryInMacWithDate(start,end,macArray);
		Map<String, List<LockEndRide>> groupByMac = groupByMac(queryInMacWithDateList);
		sortByCreateDate(groupByMac);
		PowerVo analyzePower = analyzePower(groupByMac);
		return analyzePower;
	}
	
	/**
	 * 
	 * 根据mac，统计以下区间的电量情况：
	 * 总数：100
	 * 95%-100%：80
	 * 90%-95%：10
	 * 80%-90%：5
	 * 低于80%：5
	 * @param groupByMac
	 */
	private PowerVo analyzePower(Map<String, List<LockEndRide>> groupByMac) {
		int power95 = 0;
		int power90 = 0;
		int power80 = 0;
		int powerOther = 0;
		int total = 0;
		for(Map.Entry<String, List<LockEndRide>> map : groupByMac.entrySet()){
			List<LockEndRide> value = map.getValue();
			if(CollectionUtils.isNotEmpty(value)){
				LockEndRide lockEndRide = value.get(0);
				String payload = lockEndRide.getPayload();
				Map<String,String> parseObject = JSON.parseObject(payload, Map.class);
				String bat = parseObject.get("BAT");
				int parseInt = Integer.parseInt(bat);
				if(parseInt >= 95){
					power95++;
				}else if (95 > parseInt &&parseInt >= 90) {
					
				}else if (90 > parseInt &&parseInt >= 80) {
					power80++;
				}else{
					powerOther++;
				}
			}
		}
		total = groupByMac.entrySet().size();
		PowerVo powerVo = new PowerVo();
		powerVo.setPower80(power80);
		powerVo.setPower90(power90);
		powerVo.setPower95(power95);
		powerVo.setPowerOther(powerOther);
		powerVo.setTotal(total);
		return powerVo;
	}

	/**
	 * 对集合进行时间排序
	 * @param groupByMac
	 */
	private void sortByCreateDate(Map<String, List<LockEndRide>> groupByMac) {
		for (Map.Entry<String, List<LockEndRide>> map : groupByMac.entrySet()) {
			List<LockEndRide> list = map.getValue();
			Collections.sort(list, new Comparator<LockEndRide>() {
				public int compare(LockEndRide o1, LockEndRide o2) {
					if (o1.getCreateDate().getTime() > o2.getCreateDate().getTime()) {
						return 1;
					} else if (o1.getCreateDate().getTime() < o2.getCreateDate().getTime()) {
						return -1;
					} else {
						return 0;
					}
				}
			});
		}
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
		//开锁请求次数 总数
		double requestEndRideFeeTimeTotal = 0;
		//开锁成功次数 总数
		double requestEndRideSuccTimeTotal = 0;
		for(Map.Entry<String,List<LockEndRide>> itemMap : map.entrySet()){
			LockEndRideVo vo = new LockEndRideVo();
			vo.setMac(itemMap.getKey());
			if(CollectionUtils.isNotEmpty(itemMap.getValue())){
				vo.setRequestEndRideFeeTime((double) itemMap.getValue().size());
			}else{
				vo.setRequestEndRideFeeTime((double) 0);
			}
			requestEndRideFeeTimeTotal += vo.getRequestEndRideFeeTime();
			double openLockSuccTime = 0;
			List<LockEndRide> value = itemMap.getValue();
			if(CollectionUtils.isNotEmpty(value)){
				for (LockEndRide lockEndRide : value) {
					String payload = lockEndRide.getPayload();
					Map<String,String> parseObject = JSON.parseObject(payload, Map.class);
					if(parseObject != null && StringUtils.isNoneBlank(parseObject.get("OBT"))){
						String obt = parseObject.get("OBT");
						String[] split = obt.split("#");
						String openLockTime = split[0];
						//锁梁打开时间小于50毫秒算成功
						if(Integer.parseInt(openLockTime) <= 5){
							openLockSuccTime++;
						}
					}
				}
			}
			vo.setOpenLockSuccTime(openLockSuccTime);
			vo.setOpenLockFailTime(vo.getRequestEndRideFeeTime()-vo.getOpenLockSuccTime());
			vo.setRequestEndRideFeeRadio((double) (vo.getOpenLockSuccTime()/vo.getRequestEndRideFeeTime()));
			requestEndRideSuccTimeTotal += vo.getOpenLockSuccTime();
			list.add(vo);
		}
		//合并
		LockEndRideVo total = new LockEndRideVo();
		total.setMac("总数");
		total.setRequestEndRideFeeTime(requestEndRideFeeTimeTotal);
		total.setOpenLockSuccTime(requestEndRideSuccTimeTotal);
		total.setOpenLockFailTime(requestEndRideFeeTimeTotal - requestEndRideSuccTimeTotal);
		total.setRequestEndRideFeeRadio(requestEndRideSuccTimeTotal / requestEndRideFeeTimeTotal);
		list.add(total);
		return list;
	}

	public List<LockEndRide> queryLoadPayLoad(Date start, Date end, String[] macArray) {
		return lockEndRideMapper.queryInMacWithDate(start,end,macArray);
	}
	
	
}
