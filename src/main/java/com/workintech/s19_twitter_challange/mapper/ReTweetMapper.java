package com.workintech.s19_twitter_challange.mapper;

import com.workintech.s19_twitter_challange.dto.ReTweetResponseDto;
import com.workintech.s19_twitter_challange.entity.ReTweet;
import org.springframework.stereotype.Component;

@Component
public class ReTweetMapper {

    public ReTweetResponseDto toResponseDto(ReTweet reTweet){
        ReTweetResponseDto reTweetResponseDto = new ReTweetResponseDto();
        reTweetResponseDto.setTweet(reTweet.getTweet().getTweetText());
        reTweetResponseDto.setUser(reTweet.getUser().getUsername());
        return reTweetResponseDto;
    }
}

