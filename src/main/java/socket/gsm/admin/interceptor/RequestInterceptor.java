package socket.gsm.admin.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 登陆拦截
 * 
 * @author fangyunhe
 *
 */
public class RequestInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
	
	public boolean checkToken(HttpServletRequest request) {
		String headerToken = request.getHeader("token");
		String token = (String) request.getSession().getAttribute("token");
		if(StringUtils.isBlank(headerToken)) {
			return false;
		}
		if(headerToken.equals(token)) {
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
			map.put("status", false);
			map.put("code", "001");
			map.put("msg", "用户没有登录");
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
