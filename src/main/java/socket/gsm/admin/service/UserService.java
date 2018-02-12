package socket.gsm.admin.service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import socket.gsm.admin.config.BusinessConfig;
import socket.gsm.admin.controller.BaseController;
import socket.gsm.admin.dto.WebUserDto;
import socket.gsm.admin.response.ResponseEnum;
import socket.gsm.admin.service.cloud.WebUserService;

/**
 * @author fangyunhe
 * @date 2017年9月7日 下午1:43:47
 * 
 */
@Slf4j
@Service
public class UserService {
	
	@Value("${domain}")
	String domain;
	
	@Resource
	WebUserService webUserService;

	public Object webLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		Object upLogin = webUserService.upLogin(username, password,BusinessConfig.getUserAppId(),BusinessConfig.getUserAppSecret());
		String jsonString = JSON.toJSONString(upLogin);
		WebUserDto userDto = JSON.parseObject(jsonString, WebUserDto.class);
		if(userDto.getCode() == (ResponseEnum.STATUS001.getCode())) {
			log.info("token={}",userDto.getData().getToken());
			String[] items = domain.split("#");
			for (String item : items) {
				Cookie tokenCookie = new Cookie("token", userDto.getData().getToken());
				Cookie usernameCookie = new Cookie("username", userDto.getData().getUsername());
				Cookie uidCookie = new Cookie("uid", String.valueOf(userDto.getData().getUid()));
				tokenCookie.setDomain(item);
				usernameCookie.setDomain(item);
				uidCookie.setDomain(item);
				//一天的过期时间
				tokenCookie.setMaxAge(3600 * 24);
				tokenCookie.setPath("/");
				response.addCookie(tokenCookie);
				usernameCookie.setMaxAge(3600 * 24);
				usernameCookie.setPath("/");
				response.addCookie(usernameCookie);
				uidCookie.setMaxAge(3600 * 24);
				uidCookie.setPath("/");
				response.addCookie(uidCookie);
			}
			return BaseController.responseSuccess("登陆成功",null);
		}
		return BaseController.responseFail("账号密码错误");
	}
	
	public Object webLogout(Long uid) {
		webUserService.uplogout(uid, BusinessConfig.getUserAppId(),BusinessConfig.getUserAppSecret());
		return BaseController.responseSuccess("成功退出",null);
	}
}
