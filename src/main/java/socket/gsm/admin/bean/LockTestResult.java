package socket.gsm.admin.bean;

import java.util.Date;

public class LockTestResult {
    private String id;

    private Integer scanBluetooth;

    private Integer connectBluetooth;

    private Integer openLock;

    private Integer allowLock;

    private Integer closeLock;

    private String test2gRssi;

    private String testGpsRssi;

    private Integer testDialup;

    private String lockVersion;

    private String lockPower;

    private String mcuVersion;

    private Date createDate;

    private String macAddress;

    private String uid;

    private String simCardInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getScanBluetooth() {
        return scanBluetooth;
    }

    public void setScanBluetooth(Integer scanBluetooth) {
        this.scanBluetooth = scanBluetooth;
    }

    public Integer getConnectBluetooth() {
        return connectBluetooth;
    }

    public void setConnectBluetooth(Integer connectBluetooth) {
        this.connectBluetooth = connectBluetooth;
    }

    public Integer getOpenLock() {
        return openLock;
    }

    public void setOpenLock(Integer openLock) {
        this.openLock = openLock;
    }

    public Integer getAllowLock() {
        return allowLock;
    }

    public void setAllowLock(Integer allowLock) {
        this.allowLock = allowLock;
    }

    public Integer getCloseLock() {
        return closeLock;
    }

    public void setCloseLock(Integer closeLock) {
        this.closeLock = closeLock;
    }

    public String getTest2gRssi() {
        return test2gRssi;
    }

    public void setTest2gRssi(String test2gRssi) {
        this.test2gRssi = test2gRssi == null ? null : test2gRssi.trim();
    }

    public String getTestGpsRssi() {
        return testGpsRssi;
    }

    public void setTestGpsRssi(String testGpsRssi) {
        this.testGpsRssi = testGpsRssi == null ? null : testGpsRssi.trim();
    }

    public Integer getTestDialup() {
        return testDialup;
    }

    public void setTestDialup(Integer testDialup) {
        this.testDialup = testDialup;
    }

    public String getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(String lockVersion) {
        this.lockVersion = lockVersion == null ? null : lockVersion.trim();
    }

    public String getLockPower() {
        return lockPower;
    }

    public void setLockPower(String lockPower) {
        this.lockPower = lockPower == null ? null : lockPower.trim();
    }

    public String getMcuVersion() {
        return mcuVersion;
    }

    public void setMcuVersion(String mcuVersion) {
        this.mcuVersion = mcuVersion == null ? null : mcuVersion.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getSimCardInfo() {
        return simCardInfo;
    }

    public void setSimCardInfo(String simCardInfo) {
        this.simCardInfo = simCardInfo == null ? null : simCardInfo.trim();
    }
}