package tn.esprit.spring.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Trip;
@Repository
	public interface TripRepository extends JpaRepository <Trip, Integer> 
		{
	
			//////////////Algorithme de matching
			@Query("Select t FROM Trip t join t.users bs where bs.domain.DomainName = :DomainName2 AND t.date = :date2 AND t.destination = :destination2 ")
			List <Trip> findTripPartner(@Param("DomainName2") String DomainName2 , @Param("date2") Date date2 , @Param("destination2") String destination2);
				
			
			//////////////Recherche par destination
			@Query("SELECT f FROM Trip f  where f.destination = :destination")
		    public List<Trip> FindTripByDestination(@Param("destination") String destination);
		
		}
