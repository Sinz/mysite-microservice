package com.master.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import common.util.JwtUtil;

/**
 * @Classname ManagerApplication
 * @Description 后台网关
 * @Date 2021/5/12 1:33
 * @Created by Zhao.J
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }

    @Bean
    public JwtUtil jwtUtil(){ return new JwtUtil(); }
}
