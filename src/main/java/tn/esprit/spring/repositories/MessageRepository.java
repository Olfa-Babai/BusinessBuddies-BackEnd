package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

}
