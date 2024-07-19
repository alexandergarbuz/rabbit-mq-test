package com.garbuz.messaging.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garbuz.messaging.config.RabbitMQExchangeConfig;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(final String message) {
        rabbitTemplate.convertAndSend(RabbitMQExchangeConfig.DIRECT_EXCHANGE_NAME, RabbitMQExchangeConfig.ROUTING_KEY, message);
    }
}
