package com.master.test.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname MqTest
 * @Description TODO
 * @Date 2021/5/7 14:16
 * @Created by Zhao.J
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes= TestApplication.class)
public class MqProductor1 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend("itcast","我要红包");
        System.out.println("itcast 消息已发出！");
    }

    @Test
    public void testSendFanout(){
        rabbitTemplate.convertAndSend("chuanzhi","","我要红包");
        System.out.println("chuanzhi 消息已发出！");
    }

}
