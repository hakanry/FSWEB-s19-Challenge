package com.workintech.s19_twitter_challange.mapper;

import com.workintech.s19_twitter_challange.dto.LikeResponseDto;
import com.workintech.s19_twitter_challange.entity.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public LikeResponseDto toResponseDto(Like like){
        LikeResponseDto likeResponseDto = new LikeResponseDto();
        likeResponseDto.setTweet(like.getTweet().getTweetText());
        likeResponseDto.setUser(like.getUser().getUsername());
        return likeResponseDto;
    }
}
