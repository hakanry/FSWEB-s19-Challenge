package com.workintech.s19_twitter_challange.mapper;

import com.workintech.s19_twitter_challange.dto.CommentRequestDto;
import com.workintech.s19_twitter_challange.dto.CommentResponseDto;
import com.workintech.s19_twitter_challange.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentRequestDto commentRequestDto){
        Comment comment = new Comment();
        comment.setCommentText(commentRequestDto.getCommentText());
        return comment;
    }
    public CommentResponseDto toResponseDto(Comment comment){
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setCommentText(comment.getCommentText());
        commentResponseDto.setUser(comment.getUser().getUsername());
        commentResponseDto.setTweet(comment.getTweet().getTweetText());

        return commentResponseDto;
    }
}
