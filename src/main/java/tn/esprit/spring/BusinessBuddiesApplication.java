package tn.esprit.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.IEmailServiceCommunication;

@SpringBootApplication
public class BusinessBuddiesApplication {

	@Autowired
	private IEmailServiceCommunication emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BusinessBuddiesApplication.class, args);
	}
	

}
