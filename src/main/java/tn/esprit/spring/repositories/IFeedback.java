package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.*;

public interface IFeedback extends CrudRepository<Feedback, Integer>{

}
