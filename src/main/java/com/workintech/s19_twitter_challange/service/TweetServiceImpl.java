package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.TweetNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotOwnerThisTweetException;
import com.workintech.s19_twitter_challange.repository.TweetRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService{

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public Tweet findById(long id){
        return tweetRepository.findById(id).orElseThrow(()-> new TweetNotFoundException(id+"'li TWEET bulunamadı!"));
    }
    @Override
    public Set<Tweet> findByUserId(long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId+"'li USER bulunamadı!"));

        return user.getTweets();
    }
    @Override
    public Tweet save(long userId,Tweet tweet){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId+"'li USER bulunamadı!"));
        tweet.setUser(user);
        user.getTweets().add(tweet);
        return tweetRepository.save(tweet);
    }
    @Override
    public Tweet update(long id,Tweet tweet){
        Tweet foundTweet = findById(id);

        if(tweet.getTweetText()!=null){
            foundTweet.setTweetText(tweet.getTweetText());
        }
        return tweetRepository.save(foundTweet);
    }
    @Override
    public Tweet delete(long userId,long tweetId){
        Tweet foundTweet = findById(tweetId);
        if(foundTweet.getUser().getId() != userId){
            throw new UserNotOwnerThisTweetException(userId+"li kullanıcı "+tweetId+"'li tweet sahibi değildir!");
        }else{
            tweetRepository.delete(foundTweet);
            return foundTweet;
        }
    }
}
