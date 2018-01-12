/**
 * 
 */
package socket.gsm.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 业务上的配置
 * @author:fangyunhe
 * @time:2018年1月9日 下午3:40:31
 */

@Component
@ConfigurationProperties(prefix="")
@PropertySource("classpath:config-${spring.profiles.active}/common-config.properties")
public class BusinessConfig {
	private static String userAppId;
	
	private static String userAppSecret;
	
	public static String env;
	
	public static String getUserAppId() {
		return userAppId;
	}
	public static void setUserAppId(String userAppId) {
		BusinessConfig.userAppId = userAppId;
	}
	public static String getUserAppSecret() {
		return userAppSecret;
	}
	public static void setUserAppSecret(String userAppSecret) {
		BusinessConfig.userAppSecret = userAppSecret;
	}
	public static String getEnv() {
		return env;
	}
	public static void setEnv(String env) {
		BusinessConfig.env = env;
	}
	
	
}
