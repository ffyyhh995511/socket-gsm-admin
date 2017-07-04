package socket.gsm.admin.dao;

import java.util.List;

import socket.gsm.admin.bean.OtaStatus;

public interface OtaStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OtaStatus record);

    int insertSelective(OtaStatus record);

    OtaStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OtaStatus record);

    int updateByPrimaryKey(OtaStatus record);

	List<OtaStatus> queryAll();
}