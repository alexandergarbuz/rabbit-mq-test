package com.garbuz.messaging.service;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import javax.net.ssl.SSLContext;

public class RabbitMQConnect {
    private final static String QUEUE_NAME = "test";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://b-7408ccbd-b398-4225-ade3-491f2c763efe.mq.us-east-2.amazonaws.com:5671");
        factory.setUsername("user");
        factory.setPassword("password");

        // Set up SSL context for secure connection
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, new java.security.SecureRandom());
        factory.useSslProtocol(sslContext);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello, RabbitMQ!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
