package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.TweetRequestDto;
import com.workintech.s19_twitter_challange.dto.TweetResponseDto;

import java.util.Set;

public interface TweetService {
    Set<TweetResponseDto> findAll();
    TweetResponseDto findById(long id);
    Set<TweetResponseDto> findByUserId(long userId);
    TweetResponseDto save(long userId, TweetRequestDto tweet);
    TweetResponseDto update(long userId,long tweetId,TweetRequestDto tweet);
    TweetResponseDto delete(long userId,long tweetId);
}
