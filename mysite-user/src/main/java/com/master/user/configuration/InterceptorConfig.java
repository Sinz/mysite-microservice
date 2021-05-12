package com.master.user.configuration;

import com.master.user.configuration.properties.NoAuthUrlProperties;
import com.master.user.intercepter.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname InterceptorConfig
 * @Description TODO 回答问题，发文章，发吐槽
 * @Date 2021/5/9 12:39
 * @Created by Zhao.J
 */

@EnableConfigurationProperties(NoAuthUrlProperties.class)
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Autowired
    private NoAuthUrlProperties noAuthUrlProperties;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        List swaggerList = new ArrayList();

        // 注册拦截器
        // 注册拦截器
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(new ArrayList<>(noAuthUrlProperties.getShouldSkipUrls())); // swagger
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //配置拦截器访问静态资源
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }


}
