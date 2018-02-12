package socket.gsm.admin.bean;

import java.util.Date;

public class BinFile {
    private Integer id;

    private Integer newHardwareVer;

    private Integer newSoftwareVer;

    private Integer oldHardwareVer;

    private Integer oldSoftwareVer;

    private String mark;

    private Date createTime;

    private String title;

    private Date updateTime;

    private String crc;

    private Integer status;

    private Double installInterval;

    private Double upgradeInterval;

    private String whiteListMac;

    private byte[] binData;
    
    
    private Integer downLoadSucc;
	
	private Integer installSucc;
	
	private Integer installFail;
	
	private Integer isDelete;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewHardwareVer() {
        return newHardwareVer;
    }

    public void setNewHardwareVer(Integer newHardwareVer) {
        this.newHardwareVer = newHardwareVer;
    }

    public Integer getNewSoftwareVer() {
        return newSoftwareVer;
    }

    public void setNewSoftwareVer(Integer newSoftwareVer) {
        this.newSoftwareVer = newSoftwareVer;
    }

    public Integer getOldHardwareVer() {
        return oldHardwareVer;
    }

    public void setOldHardwareVer(Integer oldHardwareVer) {
        this.oldHardwareVer = oldHardwareVer;
    }

    public Integer getOldSoftwareVer() {
        return oldSoftwareVer;
    }

    public void setOldSoftwareVer(Integer oldSoftwareVer) {
        this.oldSoftwareVer = oldSoftwareVer;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc == null ? null : crc.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getInstallInterval() {
        return installInterval;
    }

    public void setInstallInterval(Double installInterval) {
        this.installInterval = installInterval;
    }

    public Double getUpgradeInterval() {
        return upgradeInterval;
    }

    public void setUpgradeInterval(Double upgradeInterval) {
        this.upgradeInterval = upgradeInterval;
    }

    public String getWhiteListMac() {
        return whiteListMac;
    }

    public void setWhiteListMac(String whiteListMac) {
        this.whiteListMac = whiteListMac == null ? null : whiteListMac.trim();
    }

    public byte[] getBinData() {
        return binData;
    }

    public void setBinData(byte[] binData) {
        this.binData = binData;
    }

	public Integer getDownLoadSucc() {
		return downLoadSucc;
	}

	public void setDownLoadSucc(Integer downLoadSucc) {
		this.downLoadSucc = downLoadSucc;
	}

	public Integer getInstallSucc() {
		return installSucc;
	}

	public void setInstallSucc(Integer installSucc) {
		this.installSucc = installSucc;
	}

	public Integer getInstallFail() {
		return installFail;
	}

	public void setInstallFail(Integer installFail) {
		this.installFail = installFail;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
    
    
}