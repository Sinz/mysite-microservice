package com.master.test.mq;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname MqCustomer1
 * @Description TODO
 * @Date 2021/5/7 14:22
 * @Created by Zhao.J
 */

@Component
@RabbitListener(queues="itcast" )
public class MqCustomer1 {

    @RabbitHandler
    public void showMessage(String message){ System.out.println("itcast接收到消息："+message); }
}
