package com.yechrom.cloud.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CheckTokenInterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CheckTokenInterceptor checkTokenInterceptor(){
        return new CheckTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(checkTokenInterceptor());
    }
}
