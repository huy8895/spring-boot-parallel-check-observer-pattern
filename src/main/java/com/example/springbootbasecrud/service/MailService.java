package com.example.springbootbasecrud.service;

import jakarta.mail.MessagingException;

public interface MailService {
	void triggerMail() throws MessagingException;
}
