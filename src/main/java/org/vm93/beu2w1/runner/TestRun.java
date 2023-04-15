package org.vm93.beu2w1.runner;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.PostazioneType;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.repo.PostazioneDaoRepo;
import org.vm93.beu2w1.service.EdificioService;
import org.vm93.beu2w1.service.PostazioneService;
import org.vm93.beu2w1.service.PrenotazioneService;
import org.vm93.beu2w1.service.UtenteService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestRun implements ApplicationRunner {

	@Autowired
	UtenteService utentedao;
	@Autowired
	EdificioService edificiodao;
	@Autowired
	PostazioneService postazdao;
	@Autowired
	PrenotazioneService prenotazionedao;

	public static Scanner scan = new Scanner(System.in);
	Utente user = null;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/*
		 * utentedao.creaUtenteFake(); edificiodao.creaEdificioFake();
		 * postazdao.creaPostazioneFake();
		 */
		log.info("*** APP STARTED ***");
		System.out.println("\n\tBenvenuto nel sistema GestionePrenotazioni!");
		System.out.print("\tScegli il tuo id Utente:");
		Long uid = scan.nextLong();
		try {
			user = utentedao.findByID(uid);
		} catch (Exception e) {
			log.error("L'utente selezionato non esiste");
			System.exit(1);
		}
		while (true) {
			menuPrincipale();
		}

	}

	public void menuPrincipale() {
		System.out.println("\n\tCiao " + user.getFullname() + "! Cosa vuoi fare?");
		System.out.println("\t 1 - ELENCO PRENOTAZIONI A TUO NOME");
		System.out.println("\t 2 - CERCA POSTAZIONE PER CITTA");
		System.out.println("\t 3 - CERCA POSTAZIONE PER TIPOLOGIA");
		System.out.println("\t 4 - INSERISCI NUOVA PRENOTAZIONE");
		System.out.println("\t 0 - ESCI DALL'APPLICAZIONE");
		int scelta = scan.nextInt();
		switch (scelta) {
		case 1:
			List<Prenotazione> prenota = prenotazionedao.findByUser(user);
			if (prenota.size() > 0) {				
				prenota.forEach(el -> System.out.println(el));
			} else {
				log.warn("Non esistono prenotazioni a tuo nome!!");
			}
			break;
		case 2:
			scan.nextLine();
			System.out.print("\n\t INSERISCI CITTA PER LA CONSULTAZIONE: ");
			String citta = scan.nextLine();
			List<Postazione> listacitta = postazdao.findCity(citta);
			if (listacitta.size() > 0) {				
				listacitta.forEach(el -> System.out.println(el));
			} else {
				log.warn("Non esistono postazioni per questo posto!!");
			}
			break;
		case 3:
			PostazioneType tipo = null;
			System.out.println("\n\t INSERISCI LA TIPOLOGIA PER LA CONSULTAZIONE: ");
			System.out.println("1 - PRIVATO  |  2 - OPENSPACE  |  3 - SALARIUNIONI");
			int sceltatype = scan.nextInt();
			scan.nextLine();
			switch (sceltatype) {
			case 1:
				tipo = PostazioneType.Privato;
			case 2: 
				tipo = PostazioneType.Openspace;
			case 3: 
				tipo = PostazioneType.SalaRiunioni;
			}
			List<Postazione> lista = postazdao.findType(tipo);
			if (lista.size() > 0) {				
				lista.forEach(el -> System.out.println(el));
			} else {
				log.warn("Non esistono postazioni di questo tipo!!");
			}
			break;
		case 4:
			log.info("Metodo scelto: nuova prenotazione!");
			System.out.print("\n\tInserisci l'ID della postazione da prenotare");
			Long postid = scan.nextLong();
			Postazione post = null;
			try {
				post = postazdao.findByID(postid);
			} catch (Exception e) {
				log.error("Postazione inesistente!!!");
				break;
			}
			System.out.print("\n\tInserisci la data della prenotazione");
			LocalDate data = genData();
			prenotazionedao.salvaPrenotazione(new Prenotazione(user, post, data));
			scan.nextLine();
			break;
		case 0:
			log.warn("Richiesta Chiusura dall'utente!");
			log.info("2023 Copyright Vincenzo Maiorana");
			System.exit(0);
			break;
		default:
			log.error("Scelta non valida!!!");
			break;
		}
	}

	public static LocalDate genData() {
		System.out.print("\n Inserisci Giorno (DD):");
		int giorno = scan.nextInt();
		System.out.print("\n Inserisci Mese (MM):");
		int mese = scan.nextInt();
		System.out.print("\n Inserisci Anno (YYYY):");
		int anno = scan.nextInt();
		return LocalDate.of(anno, mese, giorno);
	}

}
