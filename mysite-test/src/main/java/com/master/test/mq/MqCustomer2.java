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
@RabbitListener(queues="itheima" )
public class MqCustomer2 {

    @RabbitHandler
    public void showMessage(String message){ System.out.println("itheima接收到消息："+message); }
}
