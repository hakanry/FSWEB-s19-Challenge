package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.CommentRequestDto;
import com.workintech.s19_twitter_challange.dto.CommentResponseDto;
import com.workintech.s19_twitter_challange.entity.Comment;

public interface CommentService {

    CommentResponseDto save(long userId, long tweetId, CommentRequestDto comment);
    CommentResponseDto update(long userId,long tweetId,long commentId,CommentRequestDto comment);
    CommentResponseDto delete(long userId,long tweetId,long commentId);

}
