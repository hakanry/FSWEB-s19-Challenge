package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.entity.Comment;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.CommentNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.TweetNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotOwnerThisCommentException;
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

    @Override
    public Comment save(long userId,long tweetId,Comment comment){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId + "'li USER bulunamadı!"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()-> new TweetNotFoundException(tweetId+"'li TWEET bulunamadı!"));
        comment.setUser(user);
        comment.setTweet(tweet);
        user.getComments().add(comment);
        tweet.getComments().add(comment);

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(long userId,long tweetId,Comment comment){
        Comment foundComment = commentRepository.findById(comment.getId()).orElseThrow(()-> new CommentNotFoundException(comment.getId()+"'li COMMENT bulunamadı!"));

        if(foundComment.getUser().getId() != userId && foundComment.getTweet().getId() != tweetId){
            throw new UserNotOwnerThisCommentException(userId+"'li USER bu COMMENT'in sahibi değildir!");
        }else{
            if(comment.getCommentText() != null)
                foundComment.setCommentText(comment.getCommentText());
            return commentRepository.save(foundComment);
        }
    }

    @Override
    public Comment delete(long userId,long tweetId,Comment comment){
        Comment foundComment = commentRepository.findById(comment.getId()).orElseThrow(()-> new CommentNotFoundException(comment.getId()+"'li COMMENT bulunamadı!"));
        if(foundComment.getUser().getId() != userId && foundComment.getTweet().getId() != tweetId){
            throw new UserNotOwnerThisCommentException(userId+"'li USER bu COMMENT'in sahibi değildir!");
        }else{
            commentRepository.deleteById(foundComment.getId());
            return foundComment;
        }
    }
}
