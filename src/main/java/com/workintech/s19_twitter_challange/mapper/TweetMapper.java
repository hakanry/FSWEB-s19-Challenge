package com.workintech.s19_twitter_challange.mapper;

import com.workintech.s19_twitter_challange.dto.TweetRequestDto;
import com.workintech.s19_twitter_challange.dto.TweetResponseDto;
import com.workintech.s19_twitter_challange.entity.Tweet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class TweetMapper {
    private LikeMapper likeMapper;
    private CommentMapper commentMapper;
    private ReTweetMapper reTweetMapper;
    public Tweet toEntity(TweetRequestDto tweetRequestDto){
        Tweet tweet = new Tweet();
        tweet.setTweetText(tweetRequestDto.getTweetText());
        return tweet;
    }
    public TweetResponseDto toResponseDto(Tweet tweet){
        TweetResponseDto tweetResponseDto = new TweetResponseDto();
        tweetResponseDto.setTweetText(tweet.getTweetText());
        tweetResponseDto.setUser(tweet.getUser().getUsername());
        tweetResponseDto.setReTweets(tweet.getReTweets().stream().map(reTweetMapper::toResponseDto).toList());
        tweetResponseDto.setLikes(tweet.getLikes().stream().map(likeMapper::toResponseDto).toList());
        tweetResponseDto.setComments(tweet.getComments().stream().map(commentMapper::toResponseDto).toList());
        return tweetResponseDto;
    }
}
