package org.vm93.beu2w1.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.beu2w1.model.Edificio;

import com.github.javafaker.Faker;

@Configuration
public class EdificioConfig {

	@Bean("Edificio")
	@Scope("prototype")
	public Edificio paramsPostazione(String nome, String indirizzo, String citta) {
		return new Edificio(nome, indirizzo, citta);
	}
	
	@Bean("EdificioFake")
	@Scope("prototype")
	public Edificio fakeEdificio() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Edificio e = new Edificio();
		e.setCitta(fake.address().city());
		e.setIndirizzo(fake.address().streetAddress());
		e.setNome(fake.backToTheFuture().character());
		return e;
	}

}
