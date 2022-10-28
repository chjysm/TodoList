package com.api.wishList.Utill;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }
    
    /**
     * 유효성 확인 인터셉터 추가
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new HttpInterceptor() )
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/**.html")
                .excludePathPatterns("/error")
                .excludePathPatterns("/index")
                .excludePathPatterns("/joinPage")
                .excludePathPatterns("/member/login")
                .excludePathPatterns("/member/join"); // 해당 경로는 인터셉터가 가로채지 않는다.
    }
}
