package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Followage;

@Repository
public interface FollowageRepository extends CrudRepository<Followage,Integer> {
	/*
	@Query(value = "SELECT theme+MAX(f.rating) FROM Followage f GROUP BY f.theme")
	public List<String> searchMaxRatedTheme(); */
	
}