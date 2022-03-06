package tn.esprit.spring.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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



//Filtre
   @GetMapping("/search/{keyword}")
	public List<User> search(@PathVariable("keyword") String keyword) {
       return  this.userService.search (keyword); 
    } 

//Recherche
	@GetMapping("/FindUserBydomain/{domain}")
	@ResponseBody
	public List<User> FindUserBydomain(@PathVariable String domain) 
	{
		return  this.userService.FindUserBydomain(domain);
	}


//URL : //http://localhost:8089/SpringMVC/user/afficherPDF/{user_Id}
@GetMapping(value = "/afficherPDF/{user_Id}")
public void userpdf (@PathVariable("user_Id") int id) {
	   
	userService.userpdf(id);
}
	
}
