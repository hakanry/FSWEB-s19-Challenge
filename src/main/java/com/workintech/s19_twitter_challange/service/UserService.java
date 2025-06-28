package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.User;

import java.util.List;

public interface UserService {
    User register(String username, String password,Boolean isAdmin);
    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(long id);
    UserResponseDto update(long userId,UserRequestDto user);
    UserResponseDto delete(long id);
}
