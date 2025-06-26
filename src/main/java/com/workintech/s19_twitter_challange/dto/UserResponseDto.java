package com.workintech.s19_twitter_challange.dto;

import com.workintech.s19_twitter_challange.entity.Tweet;
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
    private String username;
    private Set<Tweet> tweets;
}
