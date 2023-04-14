package org.vm93.beu2w1.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.beu2w1.model.Utente;

import com.github.javafaker.Faker;

@Configuration
public class UtenteConfig {
	
	@Bean("Utente")
	@Scope("prototype")
	public Utente paramsUser(String fullname, String email, String username) {
		return new Utente(fullname, email, username);
	}
	
	@Bean("UtenteFake")
	@Scope("prototype")
	public Utente fakeUser() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		String nome = fake.name().firstName();
		String cognome = fake.name().lastName();
		Utente u = new Utente();
		u.setFullname(nome + " " + cognome);
		u.setUsername(nome.toLowerCase().charAt(0) + "." + cognome.toLowerCase());
		u.setEmail(nome.toLowerCase() + "." + cognome.toLowerCase() + "@esempio.it");
		return u;
	}

}
