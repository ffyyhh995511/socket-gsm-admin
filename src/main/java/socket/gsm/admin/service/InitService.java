package socket.gsm.admin.service;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import socket.gsm.admin.utils.PropertiesUtil;

/**
 * @author fangyunhe
 * @date 2017年9月7日 下午1:43:47
 * 
 */
@Service
public class InitService {
	private static final Logger log = Logger.getLogger(InitService.class); 
	
	@PostConstruct
	public void init(){
		log.info("服务运行环境："+PropertiesUtil.getProperties("environment.properties", "evn"));
	}
}
