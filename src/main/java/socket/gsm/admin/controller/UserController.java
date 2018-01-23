package socket.gsm.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import socket.gsm.admin.commons.LoginUser;
import socket.gsm.admin.dto.AuthorizationDto;
import socket.gsm.admin.dto.WebUserDto;
import socket.gsm.admin.response.Response;
import socket.gsm.admin.service.UserService;

/**
 * 用户
 * 
 * @author fangyunhe
 *
 */
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Resource
	UserService webUserService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Object login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		if (!StringUtils.isNoneBlank(username, password)) {
			return responseFail("账号密码为空");
		}
		try {
			boolean result = webUserService.webLogin(username, password, request, response);
			if (result) {
				return responseSuccess("登录成功", username);
			}
		} catch (Exception e) {
			log.error("登陆报错", e);
		}
		return responseFail("登陆失败");
	}

	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public Object logout(HttpServletRequest request) {
		request.getSession().removeAttribute("token");
		return responseSuccess("退出成功", null);
	}

}
