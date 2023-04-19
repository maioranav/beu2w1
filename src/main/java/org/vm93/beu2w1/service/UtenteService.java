package org.vm93.beu2w1.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.PrenotazioneDaoRepo;
import org.vm93.beu2w1.repo.UtenteDaoRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {
	
	@Autowired private UtenteDaoRepo repo;
	@Autowired private PrenotazioneDaoRepo prenotadao;

	@Autowired @Qualifier("UtenteFake") private ObjectProvider<Utente> fakeUserProvider;
	
	public void creaUtenteFake() {
		Utente u = fakeUserProvider.getObject();
		salvaUtente(u);
	}
	
	public Utente salvaUtente(Utente u) {
		if (!repo.existsByEmail(u.getEmail()) || !repo.existsByUsername(u.getUsername())) {
			repo.save(u);
			log.info("Utente " + u.getEmail() + " aggiunto al DB!!!");
			return u;
		} else {
			throw new EntityExistsException("Username or Email already exists!!");
		}
	}
	
	public String rimuoviUtente(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("ID not found!!");
		}
		List<Prenotazione> list = prenotadao.listByUsers(repo.findById(id).get());
		if (list.size() > 0) {
			list.forEach(el -> prenotadao.delete(el));
		}
		repo.deleteById(id);
		log.info("Utente rimosso dal DB!!!");
		return "User deleted!!";
	}
	
	public Utente aggiornaUtente(Utente u) {
		if (!repo.existsById(u.getId())) {
			throw new EntityNotFoundException("UserID doesnt exists!!");
		}
		repo.save(u);
		log.info("User " + u.getEmail() + " has been updated!");
		return u;
	}

	public Utente findByID(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("User.ID not found!");
		}
		return repo.findById(id).get();	
	}
	
	public List<Utente> findAll() {
		return (List<Utente>) repo.findAll();	
	}
	
	
}
