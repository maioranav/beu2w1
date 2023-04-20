package org.vm93.beu2w1.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.vm93.beu2w1.model.Utente;

@Repository
public interface UtenteDaoRepo extends PagingAndSortingRepository<Utente, Long>, CrudRepository<Utente, Long> {

	public Utente findByEmail(String email);
	
	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
    Optional<Utente> findByUsernameOrEmail(String username, String email);

	
	
}
