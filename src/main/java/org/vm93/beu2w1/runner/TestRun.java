package org.vm93.beu2w1.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.vm93.beu2w1.service.EdificioService;
import org.vm93.beu2w1.service.PostazioneService;
import org.vm93.beu2w1.service.UtenteService;

@Component
public class TestRun implements ApplicationRunner {
	
	@Autowired UtenteService utentedao;
	@Autowired EdificioService edificiodao;
	@Autowired PostazioneService postazdao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
			/*utentedao.creaUtenteFake();	
			edificiodao.creaEdificioFake();
			postazdao.creaPostazioneFake();*/
	}

}
