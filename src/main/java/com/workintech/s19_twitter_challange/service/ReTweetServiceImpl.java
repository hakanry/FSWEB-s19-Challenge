package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.ReTweetResponseDto;
import com.workintech.s19_twitter_challange.entity.ReTweet;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.TweetNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotReTweetThisTweetAlreadyException;
import com.workintech.s19_twitter_challange.mapper.ReTweetMapper;
import com.workintech.s19_twitter_challange.repository.ReTweetRepository;
import com.workintech.s19_twitter_challange.repository.TweetRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReTweetServiceImpl implements ReTweetService{


    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private ReTweetRepository reTweetRepository;
    private ReTweetMapper reTweetMapper;
    @Override
    public ReTweetResponseDto reTweet(long userId, long tweetId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId+"li USER bulunamadı!"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()-> new TweetNotFoundException(tweetId+"'li TWEET bulunamadı!"));

        ReTweet reTweeted = new ReTweet();
        reTweeted.setUser(user);
        reTweeted.setTweet(tweet);
        user.getReTweets().add(reTweeted);
        tweet.getReTweets().add(reTweeted);

        return reTweetMapper.toResponseDto(reTweetRepository.save(reTweeted));
    }

    @Override
    public ReTweetResponseDto unReTweet(long userId, long tweetId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId + "li USER bulunamadı!"));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + "'li TWEET bulunamadı!"));

        ReTweet reTweeted = reTweetRepository.findByUserIdAndTweetId(userId, tweetId)
                .orElseThrow(() -> new UserNotReTweetThisTweetAlreadyException(
                        userId + "li USER " + tweetId + "li TWEET'i zaten reTweetlememiş!"
                ));

        user.getReTweets().remove(reTweeted);
        tweet.getReTweets().remove(reTweeted);
        reTweetRepository.delete(reTweeted);

        return reTweetMapper.toResponseDto(reTweeted);
    }
}
