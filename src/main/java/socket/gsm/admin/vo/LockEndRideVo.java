package socket.gsm.admin.vo;
/**
 * @author fangyunhe
 * @date 2017年9月8日 下午4:55:45
 * 
 */
public class LockEndRideVo {
	/**
	 * mac地址
	 */
	private String mac;
	
	/**
	 * 开锁成功次数
	 */
	private Integer openLockSuccTime;
	
	/**
	 * 开锁失败次数
	 */
	private Integer openLockFailTime;
	
	/**
	 * 发送结束计费次数
	 */
	private Integer requestEndRideFeeTime;
	
	/**
	 * 开锁成功率
	 */
	private Double requestEndRideFeeRadio;
	

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getOpenLockSuccTime() {
		return openLockSuccTime;
	}

	public void setOpenLockSuccTime(Integer openLockSuccTime) {
		this.openLockSuccTime = openLockSuccTime;
	}

	public Integer getRequestEndRideFeeTime() {
		return requestEndRideFeeTime;
	}

	public void setRequestEndRideFeeTime(Integer requestEndRideFeeTime) {
		this.requestEndRideFeeTime = requestEndRideFeeTime;
	}

	public Integer getOpenLockFailTime() {
		return openLockFailTime;
	}

	public void setOpenLockFailTime(Integer openLockFailTime) {
		this.openLockFailTime = openLockFailTime;
	}

	public Double getRequestEndRideFeeRadio() {
		return requestEndRideFeeRadio;
	}

	public void setRequestEndRideFeeRadio(Double requestEndRideFeeRadio) {
		this.requestEndRideFeeRadio = requestEndRideFeeRadio;
	}
	
	
	
}
