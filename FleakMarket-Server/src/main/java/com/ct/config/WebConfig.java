package com.ct.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

/**
 * @className: WebMvcConfigurationSupport
 * @Description: 静态资源配置类
 * @author: ct
 * @date: 2022/2/20 11:25
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    //绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/")
                .addResourceLocations("file:" + uploadPath + File.separator);
    }

    /**
     * 后端跨域配置
     * @param registry
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}