package tn.esprit.spring.services;




import java.util.List;

import tn.esprit.spring.entities.User;

public interface IUserService {
	public User saveUser(User User);

	void userpdf(long User_Id);
	
	public List<User> search(String keyword);
	
    public List<User> FindUserBydomain(String domain);
    public void updateuser(Long User_Id, String UserFirstName, String UserName, String email, String Address,Integer PhoneNumber, String password);

	List<User> listEmployee();
	
	
}
