package org.vm93.beu2w1.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;

public interface PrenotazioneDaoRepo extends CrudRepository<Prenotazione, Long> {

	
	@Query(value = "SELECT p FROM Prenotazione p INNER JOIN p.utente u WHERE u = :utente")
	public List<Prenotazione> listByUsers(Utente utente);
	
	@Query(value = "SELECT p FROM Prenotazione p INNER JOIN p.utente u WHERE u = :utente AND p.data = :data")
	public List<Prenotazione> listByUsersAndDate(Utente utente, LocalDate data);
	
	@Query(value = "SELECT pr FROM Prenotazione pr INNER JOIN pr.postazione po WHERE po = :post AND pr.data = :data")
	public List<Prenotazione> listByPostAndDate(Postazione post, LocalDate data);
	
}
