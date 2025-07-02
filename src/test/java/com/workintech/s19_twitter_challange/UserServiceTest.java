package com.workintech.s19_twitter_challange;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.Role;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.UserAlreadyRegisteredException;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.mapper.UserMapper;
import com.workintech.s19_twitter_challange.repository.RoleRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import com.workintech.s19_twitter_challange.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Yeni kullanıcı başarıyla kaydedilmeli")
    void testRegisterSuccess() {
        when(userRepository.findByUserName("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(roleRepository.findByAuthority("USER")).thenReturn(Optional.of(new Role(1L, "USER")));
        when(roleRepository.findByAuthority("ADMIN")).thenReturn(Optional.of(new Role(2L, "ADMIN")));

        User savedUser = new User();
        savedUser.setUsername("newuser");
        savedUser.setPassword("encodedPass");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.register("newuser", "pass", true);

        assertNotNull(result);
        assertEquals("newuser", result.getUsername());
        assertEquals("encodedPass", result.getPassword());
    }

    @Test
    @DisplayName("Kullanıcı adı alınmışsa hata fırlatmalı")
    void testRegisterUsernameTaken() {
        when(userRepository.findByUserName("takenuser"))
                .thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyRegisteredException.class,
                () -> userService.register("takenuser", "pass", false));
    }

    @Test
    @DisplayName("Kullanıcıları listelemeli")
    void testGetUsers() {
        User user = new User();
        user.setUsername("test");
        List<User> users = List.of(user);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toResponseDto(any(User.class))).thenReturn(new UserResponseDto(user.getId(),"test", Set.of()));

        List<UserResponseDto> result = userService.getUsers();

        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getUsername());
    }

    @Test
    @DisplayName("ID'ye göre kullanıcıyı döndürmeli")
    void testGetUserByIdSuccess() {
        User user = new User();
        user.setUsername("test");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toResponseDto(user)).thenReturn(new UserResponseDto(user.getId(),"test", Set.of()));

        UserResponseDto result = userService.getUserById(1);
        assertEquals("test", result.getUsername());
    }

    @Test
    @DisplayName("ID ile kullanıcı bulunamazsa hata")
    void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1));
    }

    @Test
    @DisplayName("Kullanıcı güncellenmeli")
    void testUpdateUserSuccess() {
        UserRequestDto dto = new UserRequestDto("updated", "newpass", false);
        User updated = new User();
        updated.setUsername("updated");
        updated.setPassword("newpass");

        User foundUser = new User();

        when(userMapper.toEntity(dto)).thenReturn(updated);
        when(userRepository.findById(1L)).thenReturn(Optional.of(foundUser));
        when(userRepository.save(any(User.class))).thenReturn(foundUser);
        when(userMapper.toResponseDto(foundUser)).thenReturn(new UserResponseDto(foundUser.getId(),"updated", Set.of()));

        UserResponseDto result = userService.update(1L, dto);
        assertEquals("updated", result.getUsername());
    }

    @Test
    @DisplayName("Kullanıcı silinebilmeli")
    void testDeleteUser() {
        User user = new User();
        user.setUsername("toDelete");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toResponseDto(user)).thenReturn(new UserResponseDto(user.getId(),"toDelete", Set.of()));

        UserResponseDto result = userService.delete(1L);
        verify(userRepository).delete(user);
        assertEquals("toDelete", result.getUsername());
    }

    @Test
    @DisplayName("loadUserByUsername doğru çalışmalı")
    void testLoadUserByUsernameSuccess() {
        User user = new User();
        user.setUsername("loadme");
        when(userRepository.findByUserName("loadme")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("loadme");
        assertEquals("loadme", userDetails.getUsername());
    }

    @Test
    @DisplayName("loadUserByUsername kullanıcı yoksa hata")
    void testLoadUserByUsernameNotFound() {
        when(userRepository.findByUserName("nouser"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nouser"));
    }
}
