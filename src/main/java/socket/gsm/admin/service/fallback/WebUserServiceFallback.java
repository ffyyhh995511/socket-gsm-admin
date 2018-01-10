package socket.gsm.admin.service.fallback;

import org.springframework.stereotype.Component;

import socket.gsm.admin.response.Response;
import socket.gsm.admin.service.cloud.WebUserService;

@Component
public class WebUserServiceFallback implements WebUserService{

	@Override
	public Object upLogin(String username, String password) {
		return Response.interiorErrorResponse();
	}

}
