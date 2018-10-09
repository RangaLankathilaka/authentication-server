package com.osanda.oauth2.authenticationserver.repositories;

import com.osanda.oauth2.authenticationserver.models.Submodule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface SubModuleRepository extends JpaRepository<Submodule, Long> {
}
