package org.vm93.beu2w1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vm93.beu2w1.repo.UtenteDaoRepo;

@SpringBootTest
public class UtenteTest {

	@Autowired UtenteDaoRepo utenterepo;
	
	@Test
	void contextLoad() {
		assertNotNull(utenterepo);
	}
	
}
