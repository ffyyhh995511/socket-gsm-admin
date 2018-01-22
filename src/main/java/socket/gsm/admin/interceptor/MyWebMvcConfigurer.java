package socket.gsm.admin.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	@Bean
	public RequestInterceptor getLoginInterceptor() {
	   return new RequestInterceptor();
	}
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/**");
        super.addInterceptors(registry);
    }
}