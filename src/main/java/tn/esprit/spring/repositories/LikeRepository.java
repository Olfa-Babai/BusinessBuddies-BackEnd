package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Like;

@Repository
public interface LikeRepository extends CrudRepository<Like,Integer> {

}
