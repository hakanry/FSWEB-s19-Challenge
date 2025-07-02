package com.workintech.s19_twitter_challange.controller;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.mapper.UserMapper;
import com.workintech.s19_twitter_challange.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RegisterController {

    private UserService userService;
    private UserMapper userMapper;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto){
        User newUSER = userService.register(userRequestDto.getUsername(),userRequestDto.getPassword(), userRequestDto.getIsAdmin());
        return userMapper.toResponseDto(newUSER);
    }

}
