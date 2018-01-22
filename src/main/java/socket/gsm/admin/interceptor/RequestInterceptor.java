package socket.gsm.admin.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import socket.gsm.admin.config.BusinessConfig;
import socket.gsm.admin.dto.WebUserDto;
import socket.gsm.admin.response.ResponseEnum;
import socket.gsm.admin.service.cloud.WebUserService;

/**
 * 登陆拦截
 * 
 * @author fangyunhe
 *
 */
public class RequestInterceptor implements HandlerInterceptor {
	
	@Resource
	WebUserService webUserService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
	
	public boolean checkToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		if(StringUtils.isBlank(token)) {
			return false;
		}
		
		Object checkToken = webUserService.checkToken(token, BusinessConfig.getUserAppId(),BusinessConfig.getUserAppSecret());
		String jsonString = JSON.toJSONString(checkToken);
		WebUserDto parseObject = JSON.parseObject(jsonString, WebUserDto.class);
		if(parseObject.getCode() == (ResponseEnum.STATUS001.getCode())) {
			return true;
		}
		return false;
			
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		if (checkToken(request)) {
			return true;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "001");
			map.put("msg", "校验不通过，请重新登录");
			// 将实体对象转换为JSON Object转换
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

}
