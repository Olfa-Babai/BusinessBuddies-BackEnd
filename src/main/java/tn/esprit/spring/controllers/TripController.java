package tn.esprit.spring.controllers;
import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.GeneratePdfReport;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.TripService;

@RestController
public class TripController {

	@Autowired
	TripService tripservice;
	
	//http://localhost:8089/SPRING/AddTrip
			@PostMapping("/AddTrip")
			@ResponseBody
			public void AddTrip (@Valid @RequestBody  Trip trip)
				{				
					tripservice.AddTrip(trip);
					
				}
				
	//http://localhost:8089/SPRING/UpdateTrip/
			@PutMapping("/UpdateTrip/{idTrip}/{destination}/{duree}/{tripobject}")
			@ResponseBody
			public void UpdateTrip (@PathVariable ("idTrip") int idTrip,@PathVariable("destination") String destination,@PathVariable ("duree") String duree,@PathVariable ("tripobject") String tripobject)
				{
					tripservice.UpdateTrip(idTrip, duree, tripobject, destination);
				}
							
	//http://localhost:8089/SPRING/DeleteTrip
			@DeleteMapping("/DeleteTrip/{idTrip}")
			@ResponseBody
			public void delete(@PathVariable("idTrip")int idTrip)
				{
				
					tripservice.DeleteTrip(idTrip);
				}	
			
	//http://localhost:8089/SPRING/AfficherTrips
	
			@GetMapping("/AfficherTrips")
			@ResponseBody
			public List<Trip> getAllOffers()
				{
					return tripservice.getAllTrips();
				}
			
			
	///////////////PDF		
			
	//http://localhost:8089/SPRING/pdfreporttrip
			@RequestMapping(value = "/pdfreporttrip", method = RequestMethod.GET,
		            produces = MediaType.APPLICATION_PDF_VALUE)
		    public ResponseEntity<InputStreamResource> citiesReport() {

		        List<Trip> cities = (List<Trip>) tripservice.findAll();

		        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(cities);

		        HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=TripList.pdf");

		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
		    }
			
			
///////////////Algorithme de matching
			
	//http://localhost:8089/SPRING/findTripPartner
		@GetMapping("/findTripPartner/{User_Id}/{idTrip}")
		@ResponseBody
		public HashSet<User>findTravelPartner (@PathVariable("User_Id") Long User_Id , @PathVariable("idTrip") Integer idTrip)
			{
				return tripservice.findTripPartner(User_Id, idTrip);
			}
		
///////////////Recherche par destination
	
	//http://localhost:8089/SPRING/FindTripByDestination
		@GetMapping("/FindTripByDestination/{destination}")
		@ResponseBody
		public List<Trip> FindTripByDestination(@PathVariable String destination) 
			{
				return  tripservice.FindTripByDestination(destination);
			}
		
/////////////// Affecter Trip to user
	//http://localhost:8089/SPRING/AddTripToUser
		@PutMapping("/AddTripToUser/{idTrip}/{User_Id}")
		@ResponseBody
		public void AddTripToUser (@PathVariable("idTrip") int idTrip , @PathVariable ("User_Id") Long User_Id)
			{
				tripservice.AddTripToUser(idTrip, User_Id);
			}
		
/////////////// Recherche avancée 		
	//http://localhost:8089/SPRING/GetByDestination
		@GetMapping("/GetByDestination/{destination}")
		@ResponseBody
		public List <Trip> GetTrip (@PathVariable ("destination") String destination)
			{
				return tripservice.GetTrip(destination);
			}


/////////////// Statistiques 
	//http://localhost:8089/SPRING/getdestinationAndDomainnameStat
		@GetMapping("/getdestinationAndDomainnameStat")
	    public  List<Object[]> statistic () {
			return tripservice.statistic();
		}
}

