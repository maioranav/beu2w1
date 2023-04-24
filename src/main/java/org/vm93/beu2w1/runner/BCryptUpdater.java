package org.vm93.beu2w1.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.service.UtenteService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BCryptUpdater implements ApplicationRunner {
	
	@Autowired UtenteService utentedao;
	@Autowired @Qualifier("passwordEncoder") PasswordEncoder passencoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		aggionraCodifica();

	}
	

	public void aggionraCodifica() {
		List<Utente> lista = utentedao.findAll();
		lista.forEach(el -> {
			if (el.getPassword().length() < 12) {
				String pass = el.getPassword();
				el.setPassword(passencoder.encode(pass));
				utentedao.aggiornaUtente(el);
				log.info("Sicurezza password aggiornata per " + el.getEmail());
			}
		});
	}

}
