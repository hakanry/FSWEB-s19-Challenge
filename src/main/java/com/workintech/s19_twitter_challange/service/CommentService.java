package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.entity.Comment;

public interface CommentService {

    Comment save(long userId, long tweetId, Comment comment);
    Comment update(long userId,long tweetId,Comment comment);
    Comment delete(long userId,long tweetId,Comment comment);

}
