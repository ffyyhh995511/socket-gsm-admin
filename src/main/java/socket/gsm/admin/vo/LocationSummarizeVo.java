package socket.gsm.admin.vo;

public class LocationSummarizeVo {
	
	private String macAddress;
	
	private Double macLocationTime;
	
	private Double macLocationSucc;
	
	private Double macLocationFail;
	
	private Double macLocationSuccRadio;

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Double getMacLocationTime() {
		return macLocationTime;
	}

	public void setMacLocationTime(Double macLocationTime) {
		this.macLocationTime = macLocationTime;
	}

	public Double getMacLocationSucc() {
		return macLocationSucc;
	}

	public void setMacLocationSucc(Double macLocationSucc) {
		this.macLocationSucc = macLocationSucc;
	}

	public Double getMacLocationFail() {
		return macLocationFail;
	}

	public void setMacLocationFail(Double macLocationFail) {
		this.macLocationFail = macLocationFail;
	}

	public Double getMacLocationSuccRadio() {
		return macLocationSuccRadio;
	}

	public void setMacLocationSuccRadio(Double macLocationSuccRadio) {
		this.macLocationSuccRadio = macLocationSuccRadio;
	}
	
	
}
