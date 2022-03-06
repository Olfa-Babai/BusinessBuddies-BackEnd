package tn.esprit.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// @Configuration
public class ConfigurationComm {
/*
	@Bean
	public JavaMailSenderImpl javaMailSender(){
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setProtocol("smtp");
	    mailSender.setPort(25);	        
	    mailSender.setUsername("test.esprit.pidev@gmail.com");
	    mailSender.setPassword("pidev123456789");
		return mailSender;
	}
	
	@Bean
	  public SimpleMailMessage emailTemplate()
	  {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo("somebody@gmail.com");
	    message.setFrom("admin@gmail.com");
	      message.setText("FATAL - Application crash. Save your job !!");
	      return message;
	  }
	*/
}
