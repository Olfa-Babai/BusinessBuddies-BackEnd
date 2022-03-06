
package tn.esprit.spring.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.User;

public interface IDomainService
	{
	
	    public void addDomain(@RequestBody Domain c);
		public void UpdateDomain (int ID_Domain,String DomainName);
		public void DeleteDomain (int ID_Domain);
		Domain retrieveDomain(int ID_Domain);
		List<User> findByDomainUser(String DomainName);
	}