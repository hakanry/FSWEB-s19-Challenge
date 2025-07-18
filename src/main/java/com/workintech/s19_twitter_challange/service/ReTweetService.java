package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.ReTweetResponseDto;

public interface ReTweetService {

    ReTweetResponseDto reTweet(long userId, long tweetId);
    ReTweetResponseDto unReTweet(long userId, long tweetId);
}
