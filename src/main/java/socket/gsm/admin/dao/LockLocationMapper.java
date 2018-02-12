package socket.gsm.admin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import socket.gsm.admin.bean.LockLocation;

public interface LockLocationMapper {
    int deleteByPrimaryKey(String id);

    int insert(LockLocation record);

    int insertSelective(LockLocation record);

    LockLocation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LockLocation record);

    int updateByPrimaryKey(LockLocation record);
    
    List<LockLocation> queryInMacWithDate(@Param("start")Date start,@Param("end")Date end,@Param("macs")String macs[]);

	List<LockLocation> LatestLocationInformation(@Param("macs")String macs[]);

	List<LockLocation> getHisLocatInfoByMac(@Param("mac_address")String mac_address);
}