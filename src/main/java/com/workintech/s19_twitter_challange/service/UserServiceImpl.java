package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.mapper.UserMapper;
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
    private UserMapper userMapper;
    @Override
    public List<UserResponseDto> getUsers(){
        return userRepository.findAll().stream().map(user -> userMapper.toResponseDto(user)).toList();
    }
    @Override
    public UserResponseDto getUserById(long id){
        return userMapper.toResponseDto(userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id + "'li USER bulunamadı!")));
    }
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto){
        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
        return userMapper.toResponseDto(userRepository.save(user));
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
