package com.osanda.oauth2.authenticationserver;

import Utils.RoleUtil;
import com.osanda.oauth2.authenticationserver.models.Role;
import com.osanda.oauth2.authenticationserver.models.User;
import com.osanda.oauth2.authenticationserver.repositories.RoleRepository;
import com.osanda.oauth2.authenticationserver.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServerApplicationTests {


    private @Inject
    RoleRepository roleRepository;

    private @Inject
    UserRepository userRepository;

    @Test
    public void roleTest() {

        List<Role> roleList = new ArrayList<>();

        Role user = roleRepository.findFirsByName(RoleUtil.USER);

        if (user == null) {
            user = new Role();
            user.setName(RoleUtil.USER);
            roleList.add(user);
            log.info("New role created : " + user.getName());
        }

        Role admin = roleRepository.findFirsByName(RoleUtil.ADMIN);

        if (admin == null) {
            admin = new Role();
            admin.setName(RoleUtil.ADMIN);
            roleList.add(admin);
            log.info("New role created : " + admin.getName());
        }


        if (roleList != null && roleList.size() > 0) {
            try {
                log.info("Saving new roles : " + roleList.size());
                roleRepository.saveAll(roleList);
                log.info("New roles saving complete.");
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
            }
        }
    }// roleTest()


    @Test
    public void userCreation() {


        if (roleRepository.findAll().size() < 0) {
            this.roleTest();
        }

        User admin = userRepository.findFirstByName("admin");

        Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.findFirsByName(RoleUtil.USER));
        roles.add(roleRepository.findFirsByName(RoleUtil.ADMIN));

        if (admin == null) {
            admin = new User();
            admin.setName("admin");
            admin.setPassword("password");
            admin.setActive(true);
            admin.setRoles(roles);
            admin.setEmail("osanda1989@gmail.com");

            log.info("admin user not found creating new user with username admin and default password");
        }


        try {

            userRepository.save(admin);
            log.info("user admin saved.");

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }

    }
}
