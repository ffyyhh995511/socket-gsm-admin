package socket.gsm.admin.controller;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import socket.gsm.admin.commons.LoginUser;
/**
 * 用户
 * @author fangyunhe
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
    public Object login(String username,String password){
		if(LoginUser.checkUser(username, password)){
			String value = (String) getSession().getAttribute("user");
			if(StringUtils.isNoneBlank(value) && "admin_admin".equals(value)){
				return responseSuccess("已登录",null); 
			}
			getSession().setAttribute("user", username+"_"+password);
			Cookie cookie = new Cookie("username", username);
			cookie.setDomain("OTA");
			getResponse().addCookie(cookie);
			return responseSuccess("登录成功",username); 
		}else{
			return responseFail("登陆失败");
		}
    }
	
	@ResponseBody
	@RequestMapping(value="/logout",method=RequestMethod.GET)
    public Object logout(){
		getSession().removeAttribute("user");
		return responseSuccess("退出成功",null);  
	}
	
	
}
