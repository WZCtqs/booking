package com.zih.booking;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApplication.class)
public class BookingApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void getJsMsg() throws IOException {

        ConnectionFactory factory = rabbitTemplate.getConnectionFactory();

        Connection connection = factory.createConnection();

        Channel channel = connection.createChannel(false);

        GetResponse response = channel.basicGet("business_quoter_queue_js2",false);

        System.out.println(response);

//        channel.basicAck(response .getEnvelope().getDeliveryTag() ,false);

        String message = new String(response.getBody(), "UTF-8");

        System.out.println(message);
    }
}
