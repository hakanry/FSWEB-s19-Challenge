package com.workintech.s19_twitter_challange;

import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.repository.UserRepository;
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
class UserRepositoryTest {

    private final UserRepository userRepository;
    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("usertest");
        user.setPassword("passwordtest");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteById(user.getId());
    }

    @DisplayName("userRepository.findByUserName Username'e göre veriyi bulabiliyor mu?")
    @Test
    void findByUserName() {

        Optional<User> result = userRepository.findByUserName(user.getUsername());
        assertTrue(result.isPresent());
        assertEquals(user.getUsername(),result.get().getUsername());
    }
}