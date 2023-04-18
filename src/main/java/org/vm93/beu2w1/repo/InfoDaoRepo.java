package org.vm93.beu2w1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vm93.beu2w1.model.Info;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;

@Repository
public interface InfoDaoRepo extends CrudRepository<Info, Long> {
	
	@Query(value = "SELECT i FROM Info i WHERE LOWER(i.lang) = LOWER(:initials)")
	public List<Info> listByInitials(String initials);

}
