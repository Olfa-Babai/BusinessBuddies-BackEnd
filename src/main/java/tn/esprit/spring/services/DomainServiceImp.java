
package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.DomainRepository;
import tn.esprit.spring.repositories.UserRepository;
@Service
 public class DomainServiceImp implements IDomainService  {

	
	@Autowired
	DomainRepository Domrepo;

	
	@Autowired
	UserRepository userRepository;

public DomainRepository getDomrepo() {
		return Domrepo;
	}


	public void setDomrepo(DomainRepository domrepo) {
		Domrepo = domrepo;
	}


//Add 
public void addDomain(Domain Domain) 
		{
	Domrepo.save(Domain);
			
		}
//Update 
public void UpdateDomain(int ID_Domain, String DomainName) 
	{
	
	Domain domain = Domrepo.findById(ID_Domain).orElse(null);
	domain.setDomainName(DomainName);
	Domrepo.save(domain);
	}

	/*	//ADD

		@Override
		public void addDomain(Domain Domain){
			 Domrepo.save(Domain);
		}
		
		@Override
		public String GetDomain(String Domain){
			
			return Domrepo.findByDomainName(Domain);
			
		}*/
//Delete
public void DeleteDomain(int ID_Domain) 
	{
	
	Domrepo.deleteById(ID_Domain);
		
	}

@Override
public Domain retrieveDomain(int ID_Domain){
	Domain d = Domrepo.findById(ID_Domain).get();
	return d;
}

public List<Domain> retrieveAllDomains(){
	return (List<Domain>) Domrepo.findAll();
}

@Override
public List<User> findByDomainUser(String DomainName) {
	List<User> users=(List<User>) userRepository.findAll();
	List<User> newUsers=new ArrayList<User>();
	for(User d : users) {
		if(d.getDomain().getDomainName().equals(DomainName)){
			newUsers.add(d);
		}
	}
	return newUsers;
}


@Override
public String GetDomain(String Domain) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public List<Object[]> statistic() {
	
	return Domrepo.countTotalTypeByYear();
}

}

