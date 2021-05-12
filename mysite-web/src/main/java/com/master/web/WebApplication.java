package com.master.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Classname WebApplication
 * @Description 前台网关
 * @Date 2021/5/12 1:37
 * @Created by Zhao.J
 */

@EnableZuulProxy
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
