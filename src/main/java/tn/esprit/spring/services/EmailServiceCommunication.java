package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceCommunication implements IEmailServiceCommunication{

	private JavaMailSender javaMailSender;
	
	@Autowired
	public void MailingServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void SendMail(String toEmail,String Subject,String Body) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toEmail);
		mail.setFrom("businessbuddies005@gmail.com");
		mail.setSubject(Subject);
		mail.setText(Body);
		javaMailSender.send(mail);
	}

}