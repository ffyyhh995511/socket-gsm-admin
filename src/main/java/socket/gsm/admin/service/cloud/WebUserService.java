package socket.gsm.admin.service.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import socket.gsm.admin.dto.AuthorizationDto;
import socket.gsm.admin.response.Response;
import socket.gsm.admin.service.fallback.WebUserServiceFallback;

/**
 * @author fangyunhe
 * @date 2017年9月7日 下午1:43:47
 * 
 */
@FeignClient(name = "CLOUD-SERVICE-USER", fallback = WebUserServiceFallback.class)
public interface WebUserService {
    
	@RequestMapping(value="/web/user/up/login",method=RequestMethod.POST)
	Object upLogin(@RequestParam("username") String username, @RequestParam("password") String password);
	
}
