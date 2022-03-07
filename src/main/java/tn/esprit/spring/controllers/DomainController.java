
package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*import java.util.ArrayList;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IDomainService;
import tn.esprit.spring.services.IUserService;*/
import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.services.DomainServiceImp;
import tn.esprit.spring.services.IDomainService;


@RestController
 public class DomainController
	{
		@Autowired
			DomainServiceImp Domainservice;

		
				
		//Update

		//http://localhost:8089/UpdateDomain/
		@PutMapping("/UpdateDomain/{ID_Domain}/{DomainName}")
		@ResponseBody
			public void updateuser(@PathVariable ("ID_Domain") int ID_Domain,@PathVariable ("DomainName") String DomainName )
				{
	
			Domainservice.UpdateDomain(ID_Domain,DomainName );
	
				}
		
		
		//Delete 
		

		//http://localhost:8089/DeleteDomain/
		@DeleteMapping("/DeleteDomain/{ID_Domain}")
		@ResponseBody
		public void delete(@PathVariable("ID_Domain") int ID_Domain)
			{
			Domainservice.DeleteDomain(ID_Domain);

			}





//@Api(tags = "User management")


	//injection de service
		
		IDomainService DomainService;
		@Autowired

		
		
		//http://localhost:8089/retrieve-all-Domains
			@GetMapping("/retrieve-all-Domains")
			@ResponseBody
			public List<Domain> getDomains() {
			List<Domain> listDomains = Domainservice.retrieveAllDomains();
			return listDomains;
			}
		
		
		/*
		//AddDomain
		
		//http://localhost:8089/AjouterDomain
				@PostMapping("/AjouterDomain")
				@ResponseBody
					public String AjouterDomain(@ RequestBody Domain domain)
						{
						Domain d;
				
				if (TestVald(domain) == false ){
					Domainservice.addDomain(domain);
					return  "Domain sucessfully added";
				}
				return "Domain already exists !";}
				
					
						
						public boolean TestVald(Domain domain){
				Domain d = (Domain) DomainService.findByDomainUser(domain.getDomainName());
				if (domain == null ) 
					
				{	return false ;	}
				
				return true ;
						}
		*/
		
			//http://localhost:8089/SPRING/add-Domain
			@PostMapping("/add-Domain")
			@ResponseBody
			public String addDomain(@RequestBody Domain domain)
			{
				Domain d;
				
				if (TestVald(domain) == false ){
					Domainservice.addDomain(domain);
					return  "Domain sucessfully added";
				}
				return "Domain already exists !";
			}	
			
			
	
			public boolean TestVald(Domain domain){
				Domain d = (Domain) DomainService.findByDomainUser(domain.getDomainName());
				if (domain == null ) 
					
				{	return false ;	}
				
				return true ;
				
			}
			
	
						}

		
		
		
	
