package org.vm93.beu2w1.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Edificio;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.repo.EdificioDaoRepo;
import org.vm93.beu2w1.repo.PostazioneDaoRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EdificioService {
	
	@Autowired private EdificioDaoRepo repo;
	@Autowired private PostazioneDaoRepo postrepo;
	@Autowired private PostazioneService postdao;

	@Autowired @Qualifier("EdificioFake") private ObjectProvider<Edificio> fakeEdificioProvider;
	
	public void creaEdificioFake() {
		Edificio e = fakeEdificioProvider.getObject();
		salvaPostazione(e);
	}
	
	public Edificio salvaPostazione(Edificio e) {
		if (!repo.existsByIndirizzo(e.getIndirizzo())) {
			repo.save(e);
			log.info("Edificio aggiunto al DB!!!");
			return e;
		} else {
			throw new EntityExistsException("Address already exists!!");
		}
	}
	
	public String rimuoviUtente(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("ID not found!!");
		}
		List<Postazione> list = postrepo.findByEdificio(repo.findById(id).get());
		if(list.size() > 0) {
			list.forEach(el -> postdao.rimuoviUtente(el.getId()));
		}
		repo.deleteById(id);
		log.info("Edificio rimosso dal DB!!!");
		return "Edificio rimosso!";
	}
	
	public Edificio aggiornaPostazione(Edificio e) {
		if(!repo.existsById(e.getId())) {
			throw new EntityExistsException("ID not found!!");
		}
		repo.save(e);
		log.info("Edificio aggiornato sul DB!!!");
		return e;
	}

	public Edificio findByID(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("User.ID not found!");
		}
		return repo.findById(id).get();	
	}
	
	public List<Edificio> findAll() {
		return (List<Edificio>) repo.findAll();	
	}
	
	public Edificio edificioRandom() {
		return repo.edificioRandom();
	}
	
	
}
