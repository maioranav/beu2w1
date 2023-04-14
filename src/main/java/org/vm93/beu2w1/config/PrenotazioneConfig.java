package org.vm93.beu2w1.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;


@Configuration
public class PrenotazioneConfig {
		
		@Bean("Prenotazione")
		@Scope("prototype")
		public Prenotazione prenotazione(Postazione post, Utente u, LocalDate data) {
			Prenotazione p = new Prenotazione();
			p.setData(data);
			p.setPostazione(post);
			p.setUtente(u);
			return p;
		}

	
}
