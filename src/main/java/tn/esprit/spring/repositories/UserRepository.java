package tn.esprit.spring.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> 
  {
	Optional<User> findByEmail(String email);
  }

