package socket.gsm.admin.bean;

import java.util.Date;

public class LockLocation {
    private String id;

    private String macAddress;

    private String location;

    private Date createDate;

    private String metadata;

    private String lbs;

    private String lbsLocation;

    private Integer gpsTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata == null ? null : metadata.trim();
    }

    public String getLbs() {
        return lbs;
    }

    public void setLbs(String lbs) {
        this.lbs = lbs == null ? null : lbs.trim();
    }

    public String getLbsLocation() {
        return lbsLocation;
    }

    public void setLbsLocation(String lbsLocation) {
        this.lbsLocation = lbsLocation == null ? null : lbsLocation.trim();
    }

    public Integer getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Integer gpsTime) {
        this.gpsTime = gpsTime;
    }
}