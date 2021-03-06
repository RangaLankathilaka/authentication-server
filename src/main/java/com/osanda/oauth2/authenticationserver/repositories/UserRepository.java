package com.osanda.oauth2.authenticationserver.repositories;

import com.osanda.oauth2.authenticationserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

}
