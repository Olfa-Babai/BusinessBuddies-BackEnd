package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {

}
