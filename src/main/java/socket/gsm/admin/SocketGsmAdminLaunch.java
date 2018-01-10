package socket.gsm.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:TODO
 * @author:fangyunhe
 * @time:2018年1月4日 下午5:43:40
 */

@Slf4j
@EnableCircuitBreaker //断路器支持、容错
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "socket.**")
@MapperScan("socket.gsm.admin.dao")
public class SocketGsmAdminLaunch implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(SocketGsmAdminLaunch.class, args);
	}
    
    @Override
	public void run(String... arg0) throws Exception {
		log.info("锁后台撸起来");
	}
}
