package com.osanda.oauth2.authenticationserver.repositories;

import com.osanda.oauth2.authenticationserver.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findFirsByName(String name);
}
