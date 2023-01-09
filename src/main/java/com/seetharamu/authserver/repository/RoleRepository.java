package com.seetharamu.authserver.repository;

import com.seetharamu.authserver.model.ERole;
import com.seetharamu.authserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);

}
