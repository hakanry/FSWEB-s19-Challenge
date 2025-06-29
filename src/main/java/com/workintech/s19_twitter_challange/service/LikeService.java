package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.LikeResponseDto;

public interface LikeService {

    LikeResponseDto likedTweet(long userId, long tweetId);
    LikeResponseDto unLikedTweet(long userId,long tweetId);
}
