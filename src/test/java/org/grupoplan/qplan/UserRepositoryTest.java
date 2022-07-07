package org.grupoplan.qplan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.grupoplan.qplan.entity.User;
import org.grupoplan.qplan.repository.UserRepository;
import org.grupoplan.qplan.entity.Authority;

import org.grupoplan.qplan.repository.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("admin@admin.com");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

        user.setPassword(passwordEncoder.encode("Admin"));
        user.setEstadoActivo(true);


        final Authority authority = authorityRepository.findByAuthority("ROLE_ADMIN");
        List<Authority> roles = new ArrayList<>();
        roles.add(authority);
        user.setAuthority(roles);

        User savedUser = userRepository.save(user);

        User foundUser = entityManager.find(User.class, savedUser.getId());

        assert foundUser.getEmail() == savedUser.getEmail();

        //TODO: buscar assert
    }
}
