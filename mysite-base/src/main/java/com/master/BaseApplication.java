package com.master;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class BaseApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(BaseApplication.class, args);
        System.out.println( "Hello World!" );
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
