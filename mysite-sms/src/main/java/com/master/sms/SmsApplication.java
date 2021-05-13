package com.master.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import common.util.IdWorker;

/**
 * @Classname SmsApplication
 * @Description TODO
 * @Date 2021/5/8 14:47
 * @Created by Zhao.J
 */

@SpringBootApplication
@EnableEurekaClient
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }


    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

}
