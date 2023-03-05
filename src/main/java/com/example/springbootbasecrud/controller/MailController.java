package com.example.springbootbasecrud.controller;

import com.example.springbootbasecrud.service.MailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/api/mails")
@RequiredArgsConstructor
public class MailController {
	private final MailService service;
	
	@PostMapping
	private void send() throws MessagingException {
		service.triggerMail();
	}
}