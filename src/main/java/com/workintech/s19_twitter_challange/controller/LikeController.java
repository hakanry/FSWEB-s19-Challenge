package com.workintech.s19_twitter_challange.controller;


import com.workintech.s19_twitter_challange.dto.LikeResponseDto;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.exceptions.UserNotLoginAlreadyException;
import com.workintech.s19_twitter_challange.service.LikeService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@AllArgsConstructor
public class LikeController {

    private LikeService likeService;


    @PostMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto like(@AuthenticationPrincipal User user, @Positive @PathVariable("tweetId") long tweetId){
        if (user == null) {
            throw new UserNotLoginAlreadyException("Giriş yapmalısınız!");
        }else{
            long userId = user.getId();
            return likeService.likedTweet(userId,tweetId);
        }
    }


}
