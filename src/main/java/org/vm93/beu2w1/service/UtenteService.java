package org.vm93.beu2w1.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.UtenteDaoRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {
	
	@Autowired private UtenteDaoRepo repo;

	@Autowired @Qualifier("UtenteFake") private ObjectProvider<Utente> fakeUserProvider;
	
	public void creaUtenteFake() {
		Utente u = fakeUserProvider.getObject();
		salvaUtente(u);
	}
	
	public void salvaUtente(Utente u) {
		repo.save(u);
		log.info("Utente " + u.getEmail() + " aggiunto al DB!!!");
	}
	
	public void rimuoviUtente(Long id) {
		repo.delete(findByID(id));
		log.info("Utente rimosso dal DB!!!");
	}
	
	public void aggiornaUtente(Utente u) {
		repo.save(u);
		log.info("Utente " + u.getEmail() + " aggiornato sul DB!!!");
	}

	public Utente findByID(Long id) {
		return repo.findById(id).get();	
	}
	
	public List<Utente> findAll() {
		return (List<Utente>) repo.findAll();	
	}
	
	
}
