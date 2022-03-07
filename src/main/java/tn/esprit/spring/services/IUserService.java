package tn.esprit.spring.services;




import java.util.List;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.entities.User;

public interface IUserService {
	public User saveUser(User User);

	void userpdf(long User_Id);
	
	public List<User> search(String keyword);
	
    public List<User> FindUserBydomain(String domain);
    

	List<User> listEmployee();



	void updateuser(Long User_Id, String username, String email, String Address, Integer PhoneNumber, String password);

	
	
	
}
