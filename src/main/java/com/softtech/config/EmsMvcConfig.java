package com.softtech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EmsMvcConfig implements WebMvcConfigurer
{
	@Override
	 public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(new EmsInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","static/public/**","static/main/**","static/index/**");
    }
}
