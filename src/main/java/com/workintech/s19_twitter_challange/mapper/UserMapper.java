package com.workintech.s19_twitter_challange.mapper;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    private TweetMapper tweetMapper;

    public User toEntity(UserRequestDto userRequestDto){
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    public UserResponseDto toResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setTweets(user.getTweets().stream().map(tweetMapper::toResponseDto).collect(Collectors.toSet()));
        return userResponseDto;
    }
}
