package org.vm93.beu2w1.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.PostazioneDaoRepo;
import org.vm93.beu2w1.repo.PrenotazioneDaoRepo;
import org.vm93.beu2w1.repo.UtenteDaoRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrenotazioneService {

	@Autowired
	private PrenotazioneDaoRepo repo;
	@Autowired
	private PostazioneDaoRepo postrepo;
	@Autowired
	private UtenteDaoRepo userrepo;

	public Prenotazione salvaPrenotazione(Prenotazione p) {

		if (!userrepo.existsById(p.getUtente().getId())) {
			throw new EntityNotFoundException("Utente doesnt exists!!");
		}
		Utente u = userrepo.findById(p.getUtente().getId()).get();

		if (!postrepo.existsById(p.getPostazione().getId())) {
			throw new EntityNotFoundException("Postazione doesnt exists!!");
		}
		Postazione post = postrepo.findById(p.getPostazione().getId()).get();

		List<Prenotazione> listaperuser = findByUserDate(u, p.getData());
		List<Prenotazione> listperpost = listByPostDate(post, p.getData());

		if (listaperuser.size() > 0) {

			log.error("-> ESISTE GIA UNA PRENOTAZIONE PER LA DATA RICHIESTA!!!!");
			throw new EntityExistsException("Data Occupata per l'utente");

		} else if (listperpost.size() > 0) {

			log.error("-> LA POSTAZIONE E' GIA OCCUPATA NELLA DATA RICHIESTA!!!!");
			throw new EntityExistsException("Data Occupata per la postazione");

		} else {

			p.setPostazione(post);
			p.setUtente(u);
			repo.save(p);
			log.info("Prenotazione aggiunta al DB!!!");
			return p;
		}
	}

	public Prenotazione aggiornaPrenotazione(Prenotazione p) {
		if (!repo.existsById(p.getId())) {
			throw new EntityNotFoundException("Prenotazione doesnt exists!!");
		}

		if (!userrepo.existsById(p.getUtente().getId())) {
			throw new EntityNotFoundException("Utente doesnt exists!!");
		}
		Utente u = userrepo.findById(p.getUtente().getId()).get();

		if (!postrepo.existsById(p.getPostazione().getId())) {
			throw new EntityNotFoundException("Postazione doesnt exists!!");
		}
		Postazione post = postrepo.findById(p.getPostazione().getId()).get();
		Prenotazione oldPrenota = repo.findById(p.getId()).get();
		if (!oldPrenota.getData().isEqual(p.getData())) {

			List<Prenotazione> listaperuser = findByUserDate(u, p.getData());
			List<Prenotazione> listperpost = listByPostDate(post, p.getData());

			if (listaperuser.size() > 0) {

				log.error("-> ESISTE GIA UNA PRENOTAZIONE PER LA DATA RICHIESTA!!!!");
				throw new EntityExistsException("Data Occupata per l'utente");

			} else if (listperpost.size() > 0) {

				log.error("-> LA POSTAZIONE E' GIA OCCUPATA NELLA DATA RICHIESTA!!!!");
				throw new EntityExistsException("Data Occupata per la postazione");

			}
		}

		p.setPostazione(post);
		p.setUtente(u);
		repo.save(p);
		log.info("Prenotazione aggiunta al DB!!!");
		return p;

	}

	public String rimuoviPrenotazione(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("ID not found!!");
		}
		repo.delete(findByID(id));
		log.info("Prenotazione rimosso dal DB!!!");
		return "Prenotazione removed!";
	}

	public Prenotazione findByID(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("ID not found!");
		}
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
