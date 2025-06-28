package com.workintech.s19_twitter_challange.controller;

import com.workintech.s19_twitter_challange.dto.TweetRequestDto;
import com.workintech.s19_twitter_challange.dto.TweetResponseDto;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.UserNotLoginAlreadyException;
import com.workintech.s19_twitter_challange.service.TweetService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RestController
@RequestMapping("/tweet")
@AllArgsConstructor
public class TweetController {

    private TweetService tweetService;

    @GetMapping("/{id}")
    public TweetResponseDto findById(@Positive @PathVariable long id){
        return tweetService.findById(id);

    }
    @GetMapping("/user/{userId}")
    public Set<TweetResponseDto> findByUserId(@Positive @PathVariable long userId){
        return tweetService.findByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto save(@AuthenticationPrincipal User user, @Validated @RequestBody TweetRequestDto tweet){
        if (user == null) {
            throw new UserNotLoginAlreadyException("Giriş yapmalısınız!");
        }else{
            long userId = user.getId();
            return tweetService.save(userId,tweet);
        }
    }

    @PatchMapping("/{id}")
    public TweetResponseDto update(@AuthenticationPrincipal User user,@Positive @PathVariable long tweetId,@Validated @RequestBody TweetRequestDto tweet){
        if (user == null) {
            throw new UserNotLoginAlreadyException("Giriş yapmalısınız!");
        }else{
            long userId = user.getId();
            return  tweetService.update(userId,tweetId,tweet);
        }
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TweetResponseDto delete(@AuthenticationPrincipal User user,@Positive @PathVariable("tweetId") long tweetId){
        if (user == null) {
            throw new UserNotLoginAlreadyException("Giriş yapmalısınız!");
        }else{
            long userId = user.getId();
            return  tweetService.delete(userId,tweetId);
        }
    }

}
