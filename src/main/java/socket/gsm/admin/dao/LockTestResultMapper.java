package socket.gsm.admin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import socket.gsm.admin.bean.LockTestResult;

public interface LockTestResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(LockTestResult record);

    int insertSelective(LockTestResult record);

    LockTestResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LockTestResult record);

    int updateByPrimaryKey(LockTestResult record);
    
    List<LockTestResult> queryInMacWithDate(@Param("start")Date start,@Param("end")Date end,@Param("macs")String macs[]);
}