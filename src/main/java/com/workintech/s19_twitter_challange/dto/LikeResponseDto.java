package com.workintech.s19_twitter_challange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LikeResponseDto {

    private String tweet;
    private String user;
}
