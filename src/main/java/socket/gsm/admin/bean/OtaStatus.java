package socket.gsm.admin.bean;

import java.util.Date;

public class OtaStatus {
    private Integer id;

    private String macAddress;

    private Integer newHardwareVer;

    private Integer newSoftwareVer;

    private Integer oldHardwareVer;

    private Integer oldSoftwareVer;

    private String status;

    private Date createTime;
    
    private String statusMsg;
    
    private Integer binId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public Integer getBinId() {
		return binId;
	}

	public void setBinId(Integer binId) {
		this.binId = binId;
	}
    
}