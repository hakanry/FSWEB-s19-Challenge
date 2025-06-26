package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(long id);
    UserResponseDto save(UserRequestDto user);
    UserResponseDto update(long userId,UserRequestDto user);
    UserResponseDto delete(long id);
}
