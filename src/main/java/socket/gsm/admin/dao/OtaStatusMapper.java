package socket.gsm.admin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import socket.gsm.admin.bean.OtaStatus;

public interface OtaStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OtaStatus record);

    int insertSelective(OtaStatus record);

    OtaStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OtaStatus record);

    int updateByPrimaryKey(OtaStatus record);

	List<OtaStatus> queryAll(@Param("startDate")Date startDate,@Param("endStart")Date endStart,@Param("status")String status,@Param("macs")String[] macs);
	
	List<OtaStatus> statisDownloadAndInstall(@Param("startDate")Date startDate, @Param("endStart")Date endStart, @Param("macs")String macs[], @Param("newHardwareVer")Integer newHardwareVer, @Param("newSoftwareVer")Integer newSoftwareVer, @Param("statuses")String statuses[]);

	Integer countByStatus(@Param("status")String status);

}