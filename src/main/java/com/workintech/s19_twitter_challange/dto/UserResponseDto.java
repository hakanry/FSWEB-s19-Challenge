package com.workintech.s19_twitter_challange.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserResponseDto {
    private long id;
    private String username;
    @JsonIgnore
    private Set<TweetResponseDto> tweets;
}
