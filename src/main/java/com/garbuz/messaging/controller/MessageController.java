package com.garbuz.messaging.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garbuz.messaging.model.Message;
import com.garbuz.messaging.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	private Logger LOG = LoggerFactory.getLogger(MessageController.class);
	
	private MessageService messageService;
	
	public MessageController(final MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<Message> send(@RequestBody final Message messageToSend) throws IOException, TimeoutException {
		LOG.info("Sending {} ", messageToSend);
		messageService.send(messageToSend);
		return new ResponseEntity<>(messageToSend, HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<Message> read() throws IOException, TimeoutException {
		Message nextMessage = messageService.readNext();
		LOG.info("Reading {} ", nextMessage);
		
		return new ResponseEntity<>(nextMessage, HttpStatus.OK);
	}
}
