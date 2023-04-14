package org.vm93.beu2w1.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vm93.beu2w1.model.Utente;

@Repository
public interface UtenteDaoRepo extends CrudRepository<Utente, Long> {

}
