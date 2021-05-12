package com.master.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Classname ConfigApplication
 * @Description TODO
 * @Date 2021/5/12 4:31
 * @Created by Zhao.J
 */
@EnableConfigServer //开启配置服务
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }
}
