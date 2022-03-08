package tn.esprit.spring.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> 
  {
	
	public Optional<User> findByUsername(String UserName);
	public Optional<User> findByEmail(String email);
	
	//Filtre 
	
	@Query("SELECT f FROM User f WHERE f.email LIKE %?1%" //to search
            + " OR f.username LIKE %?1%"
            + " OR f.domain LIKE %?1%")
           
    public List<User> search(String keyword);
    
	
	//Recherche
   @Query("SELECT f FROM User f  where f.domain = :domain")
    public List<User> FindUserBydomain(@Param("domain") String domain);
   
  
  }

