package socket.gsm.admin.bean;

import java.util.Date;

public class LockEndRide {
    private String id;

    private String mac;

    private Date createDate;

    private Date updateDate;

    private String result;
    
    private String payload;
    
    /**
     * 开锁时间
     */
    private Date openLockTime;
    
    /**
     * 关锁时间
     */
    private Date closeLockTime;
    
    /**
     * 发送结束计费时间
     */
    private Date sendRequestTime;
    
    /**
     * 锁梁打开时长（单位毫秒）
     */
    private Integer openPeriod;
    
    /**
     * 关锁定位
     */
    private String location;
    
    private String lbsLocation;
    
    private Integer bat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Date getOpenLockTime() {
		return openLockTime;
	}

	public void setOpenLockTime(Date openLockTime) {
		this.openLockTime = openLockTime;
	}

	public Date getCloseLockTime() {
		return closeLockTime;
	}

	public void setCloseLockTime(Date closeLockTime) {
		this.closeLockTime = closeLockTime;
	}

	public Date getSendRequestTime() {
		return sendRequestTime;
	}

	public void setSendRequestTime(Date sendRequestTime) {
		this.sendRequestTime = sendRequestTime;
	}

	public Integer getOpenPeriod() {
		return openPeriod;
	}

	public void setOpenPeriod(Integer openPeriod) {
		this.openPeriod = openPeriod;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLbsLocation() {
		return lbsLocation;
	}

	public void setLbsLocation(String lbsLocation) {
		this.lbsLocation = lbsLocation;
	}

	public Integer getBat() {
		return bat;
	}

	public void setBat(Integer bat) {
		this.bat = bat;
	}
    
    
}