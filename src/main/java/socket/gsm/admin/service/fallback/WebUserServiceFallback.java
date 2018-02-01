package socket.gsm.admin.service.fallback;

import org.springframework.stereotype.Component;

import socket.gsm.admin.response.Response;
import socket.gsm.admin.service.cloud.WebUserService;

@Component
public class WebUserServiceFallback implements WebUserService{

	@Override
	public Object upLogin(String username, String password, String appId, String appSecret) {
		return Response.interiorErrorResponse();
	}

	@Override
	public Object checkToken(String ticket, String appId, String appSecret) {
		return Response.interiorErrorResponse();
	}

	@Override
	public Object uplogout(Long uid, String appId, String appSecret) {
		return Response.interiorErrorResponse();
	}

}
