package com.osanda.oauth2.authenticationserver.repositories;

import com.osanda.oauth2.authenticationserver.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
