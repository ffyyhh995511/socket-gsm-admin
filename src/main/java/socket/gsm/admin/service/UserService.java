package socket.gsm.admin.service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import feign.Request;
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

	public boolean webLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		Object upLogin = webUserService.upLogin(username, password);
		String jsonString = JSON.toJSONString(upLogin);
		System.out.println(jsonString);
		WebUserDto parseObject = JSON.parseObject(jsonString, WebUserDto.class);
		if(parseObject.getCode() == (ResponseEnum.STATUS001.getCode())) {
			Cookie cookie = new Cookie("token", parseObject.getData().getToken());
			cookie.setDomain("OTA");
			//一天的过期时间
			cookie.setMaxAge(3600 * 24);
			response.addCookie(cookie);
			//设置会话
			HttpSession session = request.getSession();
			//一天的过期时间
			session.setMaxInactiveInterval(3600 * 24);
			session.setAttribute("token", parseObject.getData().getToken());
			return true;
		}
		return false;
	}
}
