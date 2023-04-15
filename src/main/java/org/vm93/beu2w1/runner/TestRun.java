package org.vm93.beu2w1.runner;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.service.EdificioService;
import org.vm93.beu2w1.service.PostazioneService;
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
		int scelta = scan.nextInt();
		switch (scelta) {
		case 1:
			log.warn("Metodo da implementare!");
			break;
		case 2:
			log.warn("Metodo da implementare!");
			break;
		case 3:
			log.warn("Metodo da implementare!");
			break;
		case 4:
			log.warn("Metodo da implementare!");
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

}
