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
	private Double openLockSuccTime;
	
	/**
	 * 开锁失败次数
	 */
	private Double openLockFailTime;
	
	/**
	 * 发送结束计费次数
	 */
	private Double requestEndRideFeeTime;
	
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

	public Double getOpenLockSuccTime() {
		return openLockSuccTime;
	}

	public void setOpenLockSuccTime(Double openLockSuccTime) {
		this.openLockSuccTime = openLockSuccTime;
	}

	public Double getOpenLockFailTime() {
		return openLockFailTime;
	}

	public void setOpenLockFailTime(Double openLockFailTime) {
		this.openLockFailTime = openLockFailTime;
	}

	public Double getRequestEndRideFeeTime() {
		return requestEndRideFeeTime;
	}

	public void setRequestEndRideFeeTime(Double requestEndRideFeeTime) {
		this.requestEndRideFeeTime = requestEndRideFeeTime;
	}

	public Double getRequestEndRideFeeRadio() {
		return requestEndRideFeeRadio;
	}

	public void setRequestEndRideFeeRadio(Double requestEndRideFeeRadio) {
		this.requestEndRideFeeRadio = requestEndRideFeeRadio;
	}
	
}
