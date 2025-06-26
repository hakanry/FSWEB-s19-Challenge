package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService , UserService{

    private UserRepository userRepository;

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @Override
    public User getUserById(long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id + "'li USER bulunamadı!"));
    }
    @Override
    public User save(User user){
        return userRepository.save(user);
    }
    @Override
    public User update(long userId,User user){
        User foundUser = getUserById(userId);

        if(user.getUsername()!= null){
            foundUser.setUsername(user.getUsername());
        }
        if(user.getPassword() != null){
            foundUser.setPassword(user.getPassword());
        }
        return userRepository.save(foundUser);
    }
    @Override
    public User delete(long id){
        User foundUser = getUserById(id);
        userRepository.delete(foundUser);
        return foundUser;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("USER BULUNAMADI!"));
    }
}
