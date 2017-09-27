package socket.gsm.admin.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

import socket.gsm.admin.commons.LoginUser;
/**
 * 登陆拦截
 * @author fangyunhe
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String value = (String) request.getSession().getAttribute("user");
		if(LoginUser.checkSessionUser(value)){
			return true;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("code", "001");
			map.put("msg", "用户没有登录");
			//将实体对象转换为JSON Object转换  
		    JSONObject responseJSONObject = (JSONObject) JSONObject.toJSON(map);
		    response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");  
		    PrintWriter out = null;  
		    try {  
		        out = response.getWriter();  
		        out.append(responseJSONObject.toString());  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    } finally {  
		        if (out != null) {  
		            out.close();  
		        }  
		    }  
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
}
