package com.workintech.s19_twitter_challange.controller;

import com.workintech.s19_twitter_challange.dto.ReTweetResponseDto;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.UserNotLoginAlreadyException;
import com.workintech.s19_twitter_challange.service.ReTweetService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
@AllArgsConstructor
public class ReTweetController {

    private ReTweetService reTweetService;

    @PostMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReTweetResponseDto retweet(@AuthenticationPrincipal User user, @Positive @PathVariable("tweetId") long tweetId){
        if (user == null) {
            throw new UserNotLoginAlreadyException("Giriş yapmalısınız!");
        }else{
            long userId = user.getId();
            return reTweetService.reTweet(userId,tweetId);
        }
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReTweetResponseDto unretweet(@AuthenticationPrincipal User user,@Positive @PathVariable("tweetId") long tweetId){
        if (user == null) {
            throw new UserNotLoginAlreadyException("Giriş yapmalısınız!");
        }else{
            long userId = user.getId();
            return reTweetService.unReTweet(userId,tweetId);
        }
    }
}
