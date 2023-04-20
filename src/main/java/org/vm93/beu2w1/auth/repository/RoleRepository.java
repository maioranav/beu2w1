package org.vm93.beu2w1.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vm93.beu2w1.auth.entity.ERole;
import org.vm93.beu2w1.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
