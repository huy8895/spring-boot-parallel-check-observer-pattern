package com.example.springbootbasecrud.service.impl;

import com.example.springbootbasecrud.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;


/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
	
	
	private final JavaMailSender javaMailSender;
	
	private final MessageSource messageSource;
	
	
	
	@Async
	public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
		log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
		          isMultipart, isHtml, to, subject, content);
		
		// Prepare message using a Spring helper
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
			message.setTo(to);
			message.setFrom("jHipsterProperties.getMail().getFrom()");
			message.setSubject(subject);
			message.setText(content, isHtml);
			javaMailSender.send(mimeMessage);
			log.debug("Sent email to User '{}'", to);
		}  catch (MailException | MessagingException e) {
			log.warn("Email could not be sent to user '{}'", to, e);
		}
	}
	
	@Override
	@Async
	public void triggerMail() throws MessagingException {
		sendSimpleEmail("huy8895@gmail.com",
		                "This is email body",
		                "This is email subject");
		
	}
	
	
	
	public void sendSimpleEmail(String toEmail,
	                            String subject,
	                            String body
	) {
		log.info("start sendSimpleEmail: ");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("thongbao.truyenmeme@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		javaMailSender.send(message);
		
		log.info("Mail Send...");
	}
	
}
