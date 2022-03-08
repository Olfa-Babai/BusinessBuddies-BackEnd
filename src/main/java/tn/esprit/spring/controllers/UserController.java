package tn.esprit.spring.controllers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;

import java.util.List;



import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.entities.User;

import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.IUserService;
import tn.esprit.spring.services.UserService;

@RestController
@RequestMapping("/user")

public class UserController {

	@Autowired
	IUserService userService;
	
	@Autowired
	UserRepository repo;
	

	@PostMapping("/registration")
	@ResponseBody
	public String createNewUser( @RequestBody User user) {
	String msg="";
	User userExists = repo.findByEmail(user.getEmail()).orElse(null);
	if (userExists != null) 
	{msg="There is already a user registered with the user name provided";} 
	else
	{
	userService.saveUser(user);
	msg="OK"; 
	}
	return msg; 
		}

	
//Update infos

//http://localhost:8089/user/updateuser/
				@PutMapping("/updateuser/{User_Id}/{username}/{email}/{Address}/{password}/{PhoneNumber}")
				@ResponseBody
				public void updateuser(@PathVariable ("User_Id") Long User_Id,@PathVariable ("username") String username,@PathVariable ("email") String email,@PathVariable ("Address") String Address,@PathVariable ("password") String password,@PathVariable ("PhoneNumber") Integer PhoneNumber){
					
				userService.updateuser(User_Id, username , Address, email, PhoneNumber, password);
					
				}

				
				//DELETE 
				//http://localhost:8089/user/deleteuser	
				@DeleteMapping("/deleteuser/{user_Id}")
				@ResponseBody
				public void delete(@PathVariable("user_Id")Long user_Id)
					{
					
						userService.delete(user_Id);}
//Filtre
   @GetMapping("/search/{keyword}")
	public List<User> search(@PathVariable("keyword") String keyword) {
       return  this.userService.search (keyword); 
    } 

//Recherche
	@GetMapping("/FindUserBydomain/{domainName}")
	@ResponseBody
	public List<User> FindUserBydomain(@PathVariable String domainName) 
	{
		return  this.userService.FindUserBydomain(domainName);
	}


	//URL : //http://localhost:8089/SPRING/user/afficherPDF/{user_Id}
	@GetMapping(value = "/afficherPDF/{user_Id}")
	@ResponseBody
	public void userpdf (@PathVariable("user_Id") int id) {
		userService.userpdf(id);
	}
	 //Consulter liste des employees
	
	@GetMapping(value = "/list_employees")
	@ResponseBody
	public List<User> listEmployee(){
		return userService.listEmployee();
	}
	

	
}
