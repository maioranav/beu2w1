package org.vm93.beu2w1.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.beu2w1.model.Edificio;
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.model.PostazioneType;

import com.github.javafaker.Faker;

@Configuration
public class PostazioneConfig {

	@Bean("Postazione")
	@Scope("prototype")
	public Postazione paramsPostazione(String description, PostazioneType type, Integer maxp, Edificio edificio) {
		return new Postazione(description, type, maxp, edificio);
	}

	@Bean("PostazioneFake")
	@Scope("prototype")
	public Postazione fakePostazione() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Postazione p = new Postazione();
		Integer rand = fake.number().numberBetween(1, 100);
		p.setMaxp(rand);
		if (rand < 10) {
			p.setType(PostazioneType.Privato);
		} else if (rand < 50) {
			p.setType(PostazioneType.Openspace);
		} else {
			p.setType(PostazioneType.SalaRiunioni);
		}
		p.setDescription(fake.harryPotter().character());
		return p;
	}

}
