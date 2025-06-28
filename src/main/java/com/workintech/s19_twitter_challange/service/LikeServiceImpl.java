package com.workintech.s19_twitter_challange.service;

import com.workintech.s19_twitter_challange.dto.LikeResponseDto;
import com.workintech.s19_twitter_challange.entity.Like;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.TweetNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotFoundException;
import com.workintech.s19_twitter_challange.exceptions.UserNotLikeThisTweetAlreadyException;
import com.workintech.s19_twitter_challange.mapper.LikeMapper;
import com.workintech.s19_twitter_challange.repository.LikeRepository;
import com.workintech.s19_twitter_challange.repository.TweetRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService{

    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private LikeRepository likeRepository;
    private LikeMapper likeMapper;
    @Override
    public LikeResponseDto likedTweet(long userId, long tweetId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId+"li USER bulunamadı!"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()-> new TweetNotFoundException(tweetId+"'li TWEET bulunamadı!"));

        Like liked = new Like();
        liked.setUser(user);
        liked.setTweet(tweet);
        user.getLikes().add(liked);
        tweet.getLikes().add(liked);

        likeRepository.save(liked);
        return likeMapper.toResponseDto(liked);

    }

    @Override
    public LikeResponseDto unLikedTweet(long userId, long tweetId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId+"li USER bulunamadı!"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()-> new TweetNotFoundException(tweetId+"'li TWEET bulunamadı!"));

        Like liked = likeRepository.findByUserIdAndTweetId(userId, tweetId)
                .orElseThrow(() -> new UserNotLikeThisTweetAlreadyException(
                        userId + "li USER " + tweetId + "li TWEET'i zaten beğenmemiş!"
                ));

        user.getLikes().remove(liked);
        tweet.getLikes().remove(liked);
        likeRepository.delete(liked);

        return likeMapper.toResponseDto(liked);

    }
}
