package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.CommentRequestDto;
import com.workintech.s19_twitter_challange.dto.CommentResponseDto;
import com.workintech.s19_twitter_challange.entity.Comment;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.*;
import com.workintech.s19_twitter_challange.mapper.CommentMapper;
import com.workintech.s19_twitter_challange.repository.CommentRepository;
import com.workintech.s19_twitter_challange.repository.TweetRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponseDto save(long userId, long tweetId, CommentRequestDto commentRequestDto){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId + "'li USER bulunamadı!"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()-> new TweetNotFoundException(tweetId+"'li TWEET bulunamadı!"));
        Comment comment = commentMapper.toEntity(commentRequestDto);
        comment.setUser(user);
        comment.setTweet(tweet);
        user.getComments().add(comment);
        tweet.getComments().add(comment);
        commentRepository.save(comment);
        return commentMapper.toResponseDto(comment);
    }

    @Override
    public CommentResponseDto update(long userId,long tweetId,long commentId,CommentRequestDto commentRequestDto){
        Comment foundComment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException(commentId+"'li COMMENT bulunamadı!"));

        if(foundComment.getUser().getId() != userId || foundComment.getTweet().getId() != tweetId){
            throw new UserNotOwnerThisCommentException(userId+"'li USER bu COMMENT'in sahibi değildir!");
        }else{
            Comment comment = commentMapper.toEntity(commentRequestDto);
            if(comment.getCommentText() != null)
                foundComment.setCommentText(comment.getCommentText());
            commentRepository.save(foundComment);
            return commentMapper.toResponseDto(comment);
        }
    }

    @Override
    public CommentResponseDto delete(long userId, long tweetId, long commentId) {
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId + "'li COMMENT bulunamadı!"));

        // Belirtilen tweet'e mi ait?
        if (foundComment.getTweet().getId() != tweetId) {
            throw new CommentNotFoundException("Bu COMMENT, belirtilen tweet'e ait değil!");
        }

        boolean isCommentOwner = foundComment.getUser().getId() == userId;
        boolean isTweetOwner = foundComment.getTweet().getUser().getId() == userId;

        // Ne comment sahibi ne de tweet sahibi → biri biriyle hata fırlat
        if (!isCommentOwner && !isTweetOwner) {
            throw new UserNotOwnerThisCommentException(
                    userId + " bu yorumun sahibi değildir ve bu tweet’in de sahibi değildir.");
        }

        // Sadece tweet sahibi değilse
        if (!isTweetOwner && isCommentOwner) {
            throw new UserNotOwnerThisTweetException(
                    userId + " bu yorumun atıldığı tweet’in sahibi değildir!");
        }

        // Sadece yorum sahibi değilse
        if (!isCommentOwner && isTweetOwner) {
            throw new UserNotOwnerThisCommentException(
                    userId + " bu yorumun sahibi değildir!");
        }

        commentRepository.deleteById(foundComment.getId());
        return commentMapper.toResponseDto(foundComment);
    }
}
