package tn.esprit.spring;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.spring.services.EmailServiceInvitationImpl;


@EnableSwagger2
@SpringBootApplication
public class BusinessBuddiesApplication {
	@Autowired
	private EmailServiceInvitationImpl service;

	public static void main(String[] args) {
		SpringApplication.run(BusinessBuddiesApplication.class, args);

	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {

		service.sendEmailWithAttachment("mohamedleith.majdoub@esprit.tn",
			    "This is Email Body with Attachment...",
				"This email has attachment",
				"C:\\Users\\user16\\Pictures\\m.jpg");
		
		//service.sendSimpleEmail("mohamedleith.majdoub@esprit.tn",
		//		"This is Email Body with Attachment...",
		//		"This email has attachment");
	}

}
