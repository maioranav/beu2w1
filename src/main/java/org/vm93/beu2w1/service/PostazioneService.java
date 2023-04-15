package org.vm93.beu2w1.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Edificio;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.PostazioneType;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.PostazioneDaoRepo;
import org.vm93.beu2w1.repo.UtenteDaoRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostazioneService {
	
	@Autowired private PostazioneDaoRepo postrepo;
	@Autowired private EdificioService edificiodao;

	@Autowired @Qualifier("PostazioneFake") private ObjectProvider<Postazione> fakePostazioneProvider;
	
	public void creaPostazioneFake() {
		Postazione u = fakePostazioneProvider.getObject();
		Edificio e = edificiodao.edificioRandom();
		u.setEdificio(e);
		salvaPostazione(u);
	}
	
	public void salvaPostazione(Postazione p) {
		postrepo.save(p);
		log.info("Postazione " + p.getDescription() + " aggiunta al DB!!!");
	}
	
	public void rimuoviUtente(Long id) {
		postrepo.delete(findByID(id));
		log.info("Postazione rimosso dal DB!!!");
	}
	
	public void aggiornaPostazione(Postazione p) {
		postrepo.save(p);
		log.info("Postazione " + p.getDescription() + " aggiornato sul DB!!!");
	}

	public Postazione findByID(Long id) {
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
