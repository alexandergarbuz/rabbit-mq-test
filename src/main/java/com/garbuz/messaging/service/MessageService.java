package com.garbuz.messaging.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.garbuz.messaging.model.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Service
public class MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);
	
	public static final String QUEUE_NAME = "MessageServiceTest";
	
	public Message send(final Message messageToSend) throws IOException, TimeoutException {
		LOG.info("Sending {}", messageToSend);
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
			Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.basicPublish("", QUEUE_NAME, null, messageToSend.getText().getBytes());
		}
		messageToSend.setSent(true);
		return messageToSend;
	}
	
	public Message readNext() throws IOException, TimeoutException {
		LOG.info("Reading next message");
		Message nextMessage = new Message();
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    
	    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        nextMessage.setText("Message #" + System.currentTimeMillis() + ":" + message);
	        LOG.info(" [x] Received '" + message + "'");
	    };
	    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
		nextMessage.setSent(false);
		return nextMessage;
	}
}
