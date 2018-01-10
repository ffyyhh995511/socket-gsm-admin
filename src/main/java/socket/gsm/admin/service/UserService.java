package socket.gsm.admin.service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import socket.gsm.admin.dto.WebUserDto;
import socket.gsm.admin.response.ResponseEnum;
import socket.gsm.admin.service.cloud.WebUserService;

/**
 * @author fangyunhe
 * @date 2017年9月7日 下午1:43:47
 * 
 */
@Service
public class UserService {

	@Resource
	WebUserService webUserService;

	public boolean webLogin(String username, String password, HttpServletResponse response) {
		Object upLogin = webUserService.upLogin(username, password);
		String jsonString = JSON.toJSONString(upLogin);
		System.out.println(jsonString);
		WebUserDto parseObject = JSON.parseObject(jsonString, WebUserDto.class);
		if(parseObject.getCode() == (ResponseEnum.STATUS001.getCode())) {
			Cookie cookie = new Cookie("token", parseObject.getData().getToken());
			cookie.setDomain("OTA");
			response.addCookie(cookie);
			return true;
		}
		return false;
	}
}
