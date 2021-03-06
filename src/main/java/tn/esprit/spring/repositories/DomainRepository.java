
package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Domain;


@Repository 
public interface DomainRepository extends CrudRepository <Domain, Integer> {
   //Algo
	@Query("select d.DomainName from Domain d where d.DomainName = :DomainName")
	  String findByDomainName(@Param("DomainName") String DomainName);
	
//STAT 
	@Query("SELECT c.DomainName, COUNT(c.DomainName) FROM Domain AS c GROUP BY c.DomainName ORDER BY c.DomainName DESC")
	List<Object[]> countTotalTypeByYear();
} 

