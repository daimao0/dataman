package com.daimao.common.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author daimao
 * @date 2022/8/19 22:02
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DatasourceAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/$datasource/getConnectionToken",
                        "/swagger-resources/**","/swagger-ui/**", "/v3/**", "/error",
                        "/**/*.ico",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.html");
    }

    /**
     * @param registry 拦截器注册表
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
