package socket.gsm.admin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import socket.gsm.admin.bean.LockEndRide;

public interface LockEndRideMapper {
    int deleteByPrimaryKey(String id);

    int insert(LockEndRide record);

    int insertSelective(LockEndRide record);

    LockEndRide selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LockEndRide record);

    int updateByPrimaryKeyWithBLOBs(LockEndRide record);

    int updateByPrimaryKey(LockEndRide record);

	List<LockEndRide> queryInMacWithDate(@Param("start")Date start,@Param("end")Date end,@Param("macs")String macs[]);
}