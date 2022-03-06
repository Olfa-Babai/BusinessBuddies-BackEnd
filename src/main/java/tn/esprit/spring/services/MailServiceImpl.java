package tn.esprit.spring.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class MailServiceImpl {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public void MailingServiceImpl(JavaMailSender javaMailSender) {

		this.javaMailSender = javaMailSender;

	}
	
	public void Sendd(String toEmail,String Subject,String Body) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot.
		 * This JavaMailSender extends the MailSender Interface which contains
		 * send() function. SimpleMailMessage Object is required because send()
		 * function uses object of SimpleMailMessage as a Parameter
		 */

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toEmail);
		mail.setFrom("businessbuddies005@gmail.com");
		mail.setSubject(Subject);
		mail.setText(Body);

		
		javaMailSender.send(mail);
	}


}
