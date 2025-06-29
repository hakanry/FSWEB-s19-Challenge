package com.workintech.s19_twitter_challange.controller;

import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.service.UserService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;


    @GetMapping
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@Positive @PathVariable long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponseDto delete(@Positive @PathVariable long id){
        return userService.delete(id);
    }
}
