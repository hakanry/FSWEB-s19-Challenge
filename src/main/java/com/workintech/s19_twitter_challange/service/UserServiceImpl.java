package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.Role;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.UserAlreadyRegisteredException;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.mapper.UserMapper;
import com.workintech.s19_twitter_challange.repository.RoleRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService , UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public User register(String username, String password,Boolean isAdmin) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if(userOptional.isPresent()){
            throw new UserAlreadyRegisteredException("Bu kullanıcı adı daha önce alınmış. USERNAME: "+username);
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
    @Override
    public List<UserResponseDto> getUsers(){
        return userRepository.findAll().stream().map(user -> userMapper.toResponseDto(user)).toList();
    }
    @Override
    public UserResponseDto getUserById(long id){
        return userMapper.toResponseDto(userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id + "'li USER bulunamadı!")));
    }

    @Override
    public UserResponseDto update(long userId,UserRequestDto userRequestDto){
        User user = userMapper.toEntity(userRequestDto);
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId + "'li USER bulunamadı!"));

        if(user.getUsername()!= null){
            foundUser.setUsername(user.getUsername());
        }
        if(user.getPassword() != null){
            foundUser.setPassword(user.getPassword());
        }
        userRepository.save(foundUser);
        return userMapper.toResponseDto(foundUser);
    }
    @Override
    public UserResponseDto delete(long id){
        User foundUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id + "'li USER bulunamadı!"));
        userRepository.delete(foundUser);
        return userMapper.toResponseDto(foundUser);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("USER BULUNAMADI!"));
    }
}
