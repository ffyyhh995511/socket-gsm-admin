package socket.gsm.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import socket.gsm.admin.bean.LockLocation;
import socket.gsm.admin.dao.LockLocationMapper;
import socket.gsm.admin.vo.LocationSummarizeVo;

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
	
	/**
	 * GPS定位情况明细
	 * @param start
	 * @param end
	 * @param macs
	 * @return
	 */
	public List<LockLocation> queryInMacWithDate(Date start,Date end,String macs[]){
		return locationMapper.queryInMacWithDate(start, end, macs);
	}
	
	/**
	 * GPS定位情况汇总
	 * @param start
	 * @param end
	 * @param macs
	 */
	public List<LocationSummarizeVo> LockLocationSummarize(Date start, Date end, String[] macs) {
		List<LockLocation> queryInMacWithDate = locationMapper.queryInMacWithDate(start, end, macs);
		Map<String,List<LockLocation>> map = new HashMap<String,List<LockLocation>>();
		if(CollectionUtils.isNotEmpty(queryInMacWithDate)){
			macGroup(queryInMacWithDate, map);
		}
		List<LocationSummarizeVo> listmacSummarize = listmacSummarize(map);
		return listmacSummarize;
	}
	
	/**
	 * 按照mac地址分组
	 * @param queryInMacWithDate
	 * @param map
	 */
	public void macGroup(List<LockLocation> queryInMacWithDate,Map<String,List<LockLocation>> map){
		for (LockLocation lockLocation : queryInMacWithDate) {
			String macAddress = lockLocation.getMacAddress();
			if(map.get(macAddress) != null){
				List<LockLocation> list = map.get(macAddress);
				list.add(lockLocation);
			}else{
				List<LockLocation> list = new ArrayList<LockLocation>();
				list.add(lockLocation);
				map.put(macAddress, list);
			}
		}
	}
	
	/**
	 * 分组统计
	 * @param map
	 * @param list
	 */
	public List<LocationSummarizeVo> listmacSummarize(Map<String,List<LockLocation>> map){
		List<LocationSummarizeVo> list = new ArrayList<LocationSummarizeVo>();
		for(Map.Entry<String, List<LockLocation>> item: map.entrySet()){
			LocationSummarizeVo vo = new LocationSummarizeVo();
			vo.setMacAddress(item.getKey());
			vo.setMacLocationTime((double) item.getValue().size());
			List<LockLocation> value = item.getValue();
			int succTime = 0;
			for (LockLocation lockLocation : value) {
				if(!lockLocation.getLocation().equals("定位失败")){
					succTime++;
				}
			}
			vo.setMacLocationSucc((double) succTime);
			vo.setMacLocationFail((double) (item.getValue().size()-succTime));
			vo.setMacLocationSuccRadio((double) (vo.getMacLocationSucc()/vo.getMacLocationTime()));
			list.add(vo);
		}
		return list;
	}
}
