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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import socket.gsm.admin.bean.LockLocation;
import socket.gsm.admin.commons.OpenPage;
import socket.gsm.admin.dao.LockLocationMapper;
import socket.gsm.admin.vo.LocationSummarizeVo;

/**
 * 
 * @author fangyunhe
 *
 * 2017年8月31日 上午11:47:42
 */
@Service
@SuppressWarnings("rawtypes")
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
	public OpenPage queryInMacWithDate(Date start,Date end,String macs[],Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<LockLocation> list = locationMapper.queryInMacWithDate(start, end, macs);
		Page p = ((Page<LockLocation>) list);
	    return OpenPage.buildPage(p);
	}
	
	/**
	 * GPS定位情况汇总
	 * LBS定位情况汇总
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
		List<LocationSummarizeVo> listmacSummarize = listMacSummarize(map);
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
	 * GPS分组统计
	 * @param map
	 * @param list
	 */
	public List<LocationSummarizeVo> listMacSummarize(Map<String,List<LockLocation>> map){
		List<LocationSummarizeVo> list = new ArrayList<LocationSummarizeVo>();
		for(Map.Entry<String, List<LockLocation>> item: map.entrySet()){
			LocationSummarizeVo vo = new LocationSummarizeVo();
			vo.setMacAddress(item.getKey());
			vo.setMacLocationTime((double) item.getValue().size());
			vo.setLbsLocationTime((double) item.getValue().size());
			List<LockLocation> value = item.getValue();
			//gps定位次数
			int macSuccTime = 0;
			//lbs定位次数
			int lbsSuccTime = 0;
			for (LockLocation lockLocation : value) {
				if(!lockLocation.getLocation().equals("定位失败")){
					macSuccTime++;
				}
				//获取到基站坐标认为是成功
				if(StringUtils.isNoneBlank(lockLocation.getLbs())){
					lbsSuccTime++;
				}
			}
			//gps 相关操作
			vo.setMacLocationSucc((double) macSuccTime);
			vo.setMacLocationFail((double) (item.getValue().size()-macSuccTime));
			vo.setMacLocationSuccRadio((double) (vo.getMacLocationSucc()/vo.getMacLocationTime()));
			//lbs 相关操作
			vo.setLbsLocationSucc((double) lbsSuccTime);
			vo.setLbsLocationFail((double)(item.getValue().size()-lbsSuccTime));
			vo.setLbsLocationSuccRadio((double)(vo.getLbsLocationSucc()/vo.getLbsLocationTime()));
			list.add(vo);
		}
		return list;
	}
	
	/**
	 * 所有定位汇总
	 * @param start
	 * @param end
	 * @param macArray
	 * @return
	 */
	public List<LocationSummarizeVo> allkLocationSummarize(Date start, Date end, String[] macArray) {
		List<LocationSummarizeVo> list = new ArrayList<LocationSummarizeVo>();
		List<LockLocation> queryInMacWithDate = locationMapper.queryInMacWithDate(start, end, macArray);
		LocationSummarizeVo gpsVo = new LocationSummarizeVo();
		gpsVo.setLocationType("GPS");
		gpsVo.setLocationTotal((double) queryInMacWithDate.size());
		
		LocationSummarizeVo lbsVo = new LocationSummarizeVo();
		lbsVo.setLocationType("LBS");
		lbsVo.setLocationTotal((double) queryInMacWithDate.size());
		double gpsSucc = 0;
		double lbsSucc = 0;
		for (LockLocation lockLocation : queryInMacWithDate) {
			if(!lockLocation.getLocation().equals("定位失败")){
				gpsSucc++;
			}
			if(StringUtils.isNoneBlank(lockLocation.getLbs())){
				lbsSucc++;
			}
		}
		gpsVo.setLocationSucc(gpsSucc);
		gpsVo.setLocationFail(queryInMacWithDate.size() - gpsSucc);
		gpsVo.setLocationRadio(gpsVo.getLocationSucc()/gpsVo.getLocationTotal());
		
		lbsVo.setLocationSucc(lbsSucc);
		lbsVo.setLocationFail(queryInMacWithDate.size() - lbsSucc);
		lbsVo.setLocationRadio(lbsVo.getLocationSucc() / lbsVo.getLocationTotal());
		
		list.add(gpsVo);
		list.add(lbsVo);
		return list;
	}

	
	public OpenPage LatestLocationInformation(Integer pageNum, Integer pageSize, String mac) {
		PageHelper.startPage(pageNum, pageSize);
		List<LockLocation> list = locationMapper.LatestLocationInformation(mac);
		Page p = ((Page<LockLocation>) list);
	    return OpenPage.buildPage(p);
	}

	public OpenPage getHisLocatInfoByMac(Integer pageNum, Integer pageSize, String mac) {
		PageHelper.startPage(pageNum, pageSize);
		List<LockLocation> list = locationMapper.getHisLocatInfoByMac(mac);
		Page p = ((Page<LockLocation>) list);
	    return OpenPage.buildPage(p);
	}
	
}
