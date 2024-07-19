package com.garbuz.messaging.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.garbuz.messaging.config.RabbitMQExchangeConfig;

//@Component
//public class RabbitMQReceiver {
//
//    @RabbitListener(queues = RabbitMQExchangeConfig.QUEUE_NAME)
//    public String receiveMessage(String message) {
//    	return message;
//    }
//}