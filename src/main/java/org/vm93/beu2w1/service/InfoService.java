package org.vm93.beu2w1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vm93.beu2w1.model.Info;
import org.vm93.beu2w1.repo.InfoDaoRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InfoService {
	
	@Autowired private InfoDaoRepo repo;
	
	public void salvaInfo(Info i) {
		repo.save(i);
		log.info("Risposta endpoint info aggiunta al DB!!!");
	}
	
	public List<Info> findByInitials(String ini) {
		return (List<Info>) repo.listByInitials(ini);	
	}

}
