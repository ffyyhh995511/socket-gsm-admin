package socket.gsm.admin.dto;

public class WebUserDto {
	private Integer code;

	private String msg;
	
	private AuthorizationDto data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AuthorizationDto getData() {
		return data;
	}

	public void setData(AuthorizationDto data) {
		this.data = data;
	}
	
	
}
