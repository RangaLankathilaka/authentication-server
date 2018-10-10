package com.osanda.oauth2.authenticationserver;

import Utils.RoleUtil;
import com.osanda.oauth2.authenticationserver.models.Role;
import com.osanda.oauth2.authenticationserver.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServerApplicationTests {


    private @Inject
    RoleRepository roleRepository;

    @Test
    public void contextLoads() {
    }


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


    }

}
