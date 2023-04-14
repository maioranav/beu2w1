package org.vm93.beu2w1.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Edificio;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.EdificioDaoRepo;
import org.vm93.beu2w1.repo.PostazioneDaoRepo;
import org.vm93.beu2w1.repo.UtenteDaoRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EdificioService {
	
	@Autowired private EdificioDaoRepo repo;

	@Autowired @Qualifier("EdificioFake") private ObjectProvider<Edificio> fakeEdificioProvider;
	
	public void creaEdificioFake() {
		Edificio u = fakeEdificioProvider.getObject();
		salvaPostazione(u);
	}
	
	public void salvaPostazione(Edificio e) {
		repo.save(e);
		log.info("Edificio " + e.getNome() + " aggiunto al DB!!!");
	}
	
	public void rimuoviUtente(Long id) {
		repo.delete(findByID(id));
		log.info("Edificio rimosso dal DB!!!");
	}
	
	public void aggiornaPostazione(Edificio e) {
		repo.save(e);
		log.info("Postazione " + e.getNome() + " aggiornato sul DB!!!");
	}

	public Edificio findByID(Long id) {
		return repo.findById(id).get();	
	}
	
	public List<Edificio> findAll() {
		return (List<Edificio>) repo.findAll();	
	}
	
	public Edificio edificioRandom() {
		return repo.edificioRandom();
	}
	
	
}
