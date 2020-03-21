package com.example.demo;

import com.example.demo.controller.Producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private MockMvc mvc;

    @Autowired
    Producer producer;
    @Test
    public void qmtest() throws Exception {
        String name="1000";
        Message msg = new Message("TopicTest", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 发送消息到一个Broker
        SendResult sendResult = producer.producer.send(msg);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
    }
}
