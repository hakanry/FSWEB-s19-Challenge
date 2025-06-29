package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.S19TwitterChallangeApplication;
import com.workintech.s19_twitter_challange.entity.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = S19TwitterChallangeApplication.class)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;


    private Role role;
    @BeforeEach
    void setUp() {
        role = new Role();
        role.setAuthority("TEST_ROLE");
        roleRepository.save(role);
    }

    @AfterEach
    void tearDown() {

        roleRepository.deleteById(role.getId());
    }

    @DisplayName("roleRepository.findByAuthority Role göre veriyi bulabiliyor mu?")
    @Test
    void findByAuthority() {
        Optional<Role> found = roleRepository.findByAuthority("TEST_ROLE");

        assertTrue(found.isPresent(), "Role bulunamadı!");
        assertEquals("TEST_ROLE", found.get().getAuthority());
    }
}
