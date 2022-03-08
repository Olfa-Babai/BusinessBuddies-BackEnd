package tn.esprit.spring.repositories;

import java.util.Date;
import java.util.HashSet;
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
			HashSet <Trip> findTripPartner(@Param("DomainName2") String DomainName2 , @Param("date2") Date date2 , @Param("destination2") String destination2);
				
			
			//////////////Recherche par destination
			@Query("SELECT f FROM Trip f  where f.destination = :destination")
		    public List<Trip> FindTripByDestination(@Param("destination") String destination);
			
			//////////////Recherche avancée 
			@Query("SELECT f FROM Trip f  where f.destination like %:destination% ")
			List<Trip> GetTrip(@Param ("destination") String destination);
			Trip findByDestination(String destination);
			
			////////////// Statistiques 
			@Query("SELECT u.domain.DomainName , t.destination FROM Trip t JOIN t.users u GROUP BY u.domain.DomainName ORDER BY u.domain.DomainName DESC")
			List<Object[]> countTotalTypeByYear();
		
		}
