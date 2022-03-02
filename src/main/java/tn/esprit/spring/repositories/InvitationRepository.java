package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Invitation;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Long>{

}
