package com.dengzebin.netdisk.untils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author DengZebing
 * @Description TODO
 * @Date 2023/5/31 10:39
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestDetail());
    }

    @Bean
    public RequestDetail requestDetail() {
        return new RequestDetail();
    }
}
