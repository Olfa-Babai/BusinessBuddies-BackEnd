package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Profession;


@Repository 
public interface ProfessionRepository extends CrudRepository <Profession, Integer> {

}
