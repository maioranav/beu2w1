package org.vm93.beu2w1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vm93.beu2w1.model.Postazione;

@Repository
public interface PostazioneDaoRepo extends CrudRepository<Postazione, Long> {
	
	@Query(value = "SELECT p FROM Postazione p INNER JOIN p.edificio e WHERE LOWER(e.citta) LIKE LOWER('%' || :citta || '%')")
	public List<Postazione> listByCitta(String citta);
}
