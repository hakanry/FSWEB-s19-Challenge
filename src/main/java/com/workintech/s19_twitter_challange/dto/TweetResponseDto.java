package com.workintech.s19_twitter_challange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TweetResponseDto {

    private String tweetText;
    private String user;
    private List<CommentResponseDto> comments;
    private List<LikeResponseDto> likes;
    private List<ReTweetResponseDto> reTweets;
}
