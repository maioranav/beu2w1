package org.vm93.beu2w1.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Edificio;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.PostazioneType;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.EdificioDaoRepo;
import org.vm93.beu2w1.repo.PostazioneDaoRepo;
import org.vm93.beu2w1.repo.PrenotazioneDaoRepo;
import org.vm93.beu2w1.repo.UtenteDaoRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostazioneService {
	
	@Autowired private PostazioneDaoRepo postrepo;
	@Autowired private EdificioDaoRepo edirepo;
	@Autowired private EdificioService edificiodao;
	@Autowired private PrenotazioneDaoRepo prenotarepo;

	@Autowired @Qualifier("PostazioneFake") private ObjectProvider<Postazione> fakePostazioneProvider;
	
	public void creaPostazioneFake() {
		Postazione u = fakePostazioneProvider.getObject();
		Edificio e = edificiodao.edificioRandom();
		u.setEdificio(e);
		salvaPostazione(u);
	}
	
	public Postazione salvaPostazione(Postazione p) {
		if (!edirepo.existsById(p.getEdificio().getId())) {
			throw new EntityNotFoundException("Edificio doesnt exists!!");			
		}
		Edificio edificio = edirepo.findById(p.getEdificio().getId()).get();
		p.setEdificio(edificio);
		postrepo.save(p);
		log.info("Postazione aggiunta al DB!!!");
		return p;
	}
	
	public String rimuoviUtente(Long id) {
		if(!postrepo.existsById(id)) {
			throw new EntityExistsException("ID not found!!");
		}
		List<Prenotazione> list = prenotarepo.findByPostazione(postrepo.findById(id).get());
		if(list.size() > 0) {
			list.forEach(el -> prenotarepo.delete(el));
		}
		postrepo.delete(findByID(id));
		log.info("Postazione rimosso dal DB!!!");
		return "Postazione removed!";
	}
	
	public Postazione aggiornaPostazione(Postazione p) {
		if (!postrepo.existsById(p.getId())) {
			throw new EntityNotFoundException("PostID doesnt exists!!");
		}
		if (!edirepo.existsById(p.getEdificio().getId())) {
			throw new EntityNotFoundException("Edificio doesnt exists!!");			
		}
		Edificio edificio = edirepo.findById(p.getEdificio().getId()).get();
		p.setEdificio(edificio);
		postrepo.save(p);
		log.info("Postazione " + p.getDescription() + " aggiornato sul DB!!!");
		return p;
	}

	public Postazione findByID(Long id) {
		if(!postrepo.existsById(id)) {
			throw new EntityNotFoundException("ID not found!");
		}
		return postrepo.findById(id).get();	
	}
	
	public List<Postazione> findAll() {
		return (List<Postazione>) postrepo.findAll();	
	}
	
	public List<Postazione> findCity(String citta) {
		return (List<Postazione>) postrepo.listByCitta(citta);	
	}
	
	public List<Postazione> findType(PostazioneType type) {
		return (List<Postazione>) postrepo.listByType(type);	
	}
	
	
}
