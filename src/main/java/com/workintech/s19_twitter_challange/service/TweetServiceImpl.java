package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.TweetRequestDto;
import com.workintech.s19_twitter_challange.dto.TweetResponseDto;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.TweetNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotOwnerThisTweetException;
import com.workintech.s19_twitter_challange.mapper.TweetMapper;
import com.workintech.s19_twitter_challange.repository.TweetRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService{

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final TweetMapper tweetMapper;


    public Set<TweetResponseDto> findAll(){
        return tweetRepository.findAll().stream().map(tweetMapper::toResponseDto).collect(Collectors.toSet());
    }
    @Override
    public TweetResponseDto findById(long id){
        return tweetMapper.toResponseDto(tweetRepository.findById(id).orElseThrow(()-> new TweetNotFoundException(id+"'li TWEET bulunamadı!")));
    }
    @Override
    public Set<TweetResponseDto> findByUserId(long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId+"'li USER bulunamadı!"));

        return user.getTweets().stream().map(tweetMapper::toResponseDto).collect(Collectors.toSet());
    }
    @Override
    public TweetResponseDto save(long userId, TweetRequestDto tweetRequestDto){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId+"'li USER bulunamadı!"));
        Tweet tweet = tweetMapper.toEntity(tweetRequestDto);
        tweet.setUser(user);
        user.getTweets().add(tweet);
        tweetRepository.save(tweet);
        return tweetMapper.toResponseDto(tweet);
    }
    @Override
    public TweetResponseDto update(long userId,long tweetId,TweetRequestDto tweet){
        Tweet foundTweet = tweetRepository.findById(tweetId).orElseThrow(()-> new TweetNotFoundException(tweetId+"'li TWEET bulunamadı!"));

        if(foundTweet.getUser().getId() != userId) {
            throw new UserNotOwnerThisTweetException(userId + "li kullanıcı " + tweetId + "'li tweet sahibi değildir!");
        }else{
            if(tweet.getTweetText()!=null){
                foundTweet.setTweetText(tweet.getTweetText());
            }
            tweetRepository.save(foundTweet);
            return tweetMapper.toResponseDto(foundTweet);
        }
    }
    @Override
    public TweetResponseDto delete(long userId,long tweetId){
        Tweet foundTweet = tweetRepository.findById(tweetId).orElseThrow(()-> new TweetNotFoundException(tweetId+"'li TWEET bulunamadı!"));

        if(foundTweet.getUser().getId() != userId){
            throw new UserNotOwnerThisTweetException(userId+"li kullanıcı "+tweetId+"'li tweet sahibi değildir!");
        }else{
            tweetRepository.delete(foundTweet);
            return tweetMapper.toResponseDto(foundTweet);
        }
    }
}
