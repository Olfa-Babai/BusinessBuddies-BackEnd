package tn.esprit.spring.services;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.entities.User;

public interface TripService {
	public void AddTrip(Trip trip);
	public void UpdateTrip(int idTrip,String duree , String tripobject, String destination);
	public void DeleteTrip(int idTrip);
	public List <Trip> getAllTrips() ;
	public List <Trip> findAll();
	public HashSet <User> findTripPartner (Long User_Id , Integer idTrip);
	public List<Trip> FindTripByDestination (String destination);
	public void AddTripToUser (Integer idTrip,Long User_Id);
	public List<Trip> GetTrip(@Param ("destination") String destination);
	public  List <Object[]> statistic ();
}
