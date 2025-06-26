package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.entity.Tweet;

import java.util.Set;

public interface TweetService {

    Tweet findById(long id);
    Set<Tweet> findByUserId(long userId);
    Tweet save(long userId,Tweet tweet);
    Tweet update(long id,Tweet tweet);
    Tweet delete(long userId,long tweetId);
}
