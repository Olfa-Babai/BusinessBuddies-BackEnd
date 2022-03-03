package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Like;

@Repository
public interface LikeRepository extends CrudRepository<Like,Integer> {

	@Query("SELECT Count(l.liker) , l.post FROM Like l GROUP BY l.post")
	public List<Object[]> mostLikedPost();
}
