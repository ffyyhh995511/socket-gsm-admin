package socket.gsm.admin.vo;

import socket.gsm.admin.bean.BinFile;

/**
 * @author fangyunhe
 * @date 2017年9月27日 下午7:29:43
 * 
 */
public class BinFileVo extends BinFile{
	private Integer downLoadSucc;
	
	private Integer installSucc;
	
	private Integer installFail;
	
	private String statusMsg;

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
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
	
	
}
