package org.vm93.beu2w1.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.PrenotazioneDaoRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrenotazioneService {

	@Autowired private PrenotazioneDaoRepo repo;
	
	
	public void salvaPrenotazione(Prenotazione p) {
		List<Prenotazione> listaperuser = findByUserDate(p.getUtente(), p.getData());
		List<Prenotazione> listperpost = listByPostDate(p.getPostazione(), p.getData());
		if (listaperuser.size() > 0) {
			log.error("-> ESISTE GIA UNA PRENOTAZIONE PER LA DATA RICHIESTA!!!!");
		} else if (listperpost.size() > 0 ) {
			log.error("-> LA POSTAZIONE E' GIA OCCUPATA NELLA DATA RICHIESTA!!!!");			
		} else {
		repo.save(p);
		log.info("Prenotazione aggiunta al DB!!!");
		}
	}
	
	public Prenotazione findByID(Long id) {
		return repo.findById(id).get();	
	}
	
	public List<Prenotazione> findByUser(Utente u) {
		return (List<Prenotazione>) repo.listByUsers(u);	
	}
	
	public List<Prenotazione> findByUserDate(Utente u, LocalDate data) {
		return (List<Prenotazione>) repo.listByUsersAndDate(u, data);	
	}
	public List<Prenotazione> listByPostDate(Postazione post, LocalDate data) {
		return (List<Prenotazione>) repo.listByPostAndDate(post, data);	
	}
	
	public List<Prenotazione> findAll() {
		return (List<Prenotazione>) repo.findAll();	
	}
	
}
