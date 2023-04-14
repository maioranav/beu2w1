package org.vm93.beu2w1.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vm93.beu2w1.model.Edificio;

@Repository
public interface EdificioDaoRepo extends CrudRepository<Edificio, Long> {

}
