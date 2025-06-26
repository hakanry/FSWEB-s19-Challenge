package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.entity.Role;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.repository.RoleRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public User register(String username, String password,Boolean isAdmin) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if(userOptional.isPresent()){
            throw new RuntimeException("Bu kullanıcı adı daha önce alınmış. USERNAME: "+username);
        }

        String encodedPassword = passwordEncoder.encode(password);
        Set<Role> roles = new HashSet<>();

        if(isAdmin == true)
            addRoleAdmin(roles);
        addRoleUser(roles);

        User user = new User();
        user.setRoles(roles);
        user.setPassword(encodedPassword);
        user.setUsername(username);
        return userRepository.save(user);

    }

    private void addRoleAdmin(Set<Role> roleList){ // ADMIN ROLU VERİTABANINDA YOKSA OLUŞTUR VE VERILEN ROL LİSTESİNE EKLE VARSA SADECE ROL LİSTESİNE EKLE İŞLEMİ
        Optional<Role> findRoleAdmin = roleRepository.findByAuthority("ADMIN");
        if(!findRoleAdmin.isPresent()){
            Role adminRole = new Role();
            adminRole.setAuthority("ADMIN");
            roleList.add(roleRepository.save(adminRole));
        }
        else {
            roleList.add(findRoleAdmin.get());

        }
    }

    private void addRoleUser(Set<Role> roleList){ // USER ROLU VERİTABANINDA YOKSA OLUŞTUR VE VERILEN ROL LİSTESİNE EKLE VARSA SADECE ROL LİSTESİNE EKLE İŞLEMİ
        Optional<Role> findRoleUser = roleRepository.findByAuthority("USER");
        if(!findRoleUser.isPresent()){
            Role userRole = new Role();
            userRole.setAuthority("USER");
            roleList.add(roleRepository.save(userRole));
        }
        else {
            roleList.add(findRoleUser.get());

        }
    }
}
