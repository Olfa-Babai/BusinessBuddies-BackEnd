package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.*;

public interface IComplaint extends CrudRepository<Complaint, Integer>{

}
