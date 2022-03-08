package tn.esprit.spring.services;


import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.TripRepository;
import tn.esprit.spring.repositories.UserRepository;


@Service
public class TripServiceImpl implements TripService{

	@Autowired
	TripRepository Triprepo;
	@Autowired
	UserRepository userRepository;
	

	
	
	@Override
	public void AddTrip(Trip trip) 
		{
			Triprepo.save(trip);
		}

	@Override
	public void UpdateTrip(int idTrip, String duree, String tripobject, String destination) 
		{
			Trip trip = Triprepo.findById(idTrip).orElse(null);
			trip.setDestination(destination);
			trip.setDuree(duree);
			trip.setTripobject(tripobject);
			Triprepo.save(trip);
			
		}

	@Override
	public void DeleteTrip(int idTrip) 
		{
			Triprepo.deleteById(idTrip);	
		}
	

	@Override
	public List<Trip> getAllTrips() 
		{
			return (List<Trip>) Triprepo.findAll();
		}
	
	
	
	///////// PDF
	@Override
	public List<Trip> findAll() 
		{
			return(List<Trip>) Triprepo.findAll();
		}

	
	
	///////// ALgorithme de matching
	@Override
	public HashSet <User> findTripPartner(Long User_Id, Integer idTrip) 
		{
			User u = userRepository.findById (User_Id).orElse(null);
			Trip trip1=Triprepo.findById(idTrip).orElse(null);
				
			HashSet <Trip> trip = Triprepo.findTripPartner(u.getDomain().getDomainName(), trip1.getDate(),trip1.getDestination());
			HashSet <User> partners =new HashSet<User>();
			for (Trip i:trip)
					{
						partners.addAll(i.getUsers());
					}
			partners.remove(u);
			return partners;
		}

	////////////////Recherche par destination
	@Override
	public List<Trip> FindTripByDestination(String destination) 
		{
			return Triprepo.FindTripByDestination(destination);
		}

	
	//////////////// Affectation trip to user 
	@Override
	public void AddTripToUser(Integer idTrip, Long User_Id) 
		{
			Trip t =Triprepo.findById(idTrip).orElse(null);
			User u = userRepository.findById(User_Id).orElse(null);
			t.getUsers().add(u);
			Triprepo.save(t);
		}
	
	
	//////////////// Recherche avancée 
	@Override
	public List<Trip> GetTrip(String destination) 
		{
			return Triprepo.GetTrip(destination);
		}

	
	//////////////// Statistiques 
	@Override
	public List<Object[]> statistic() {
		return Triprepo.countTotalTypeByYear();
	}

	
	

	

}
