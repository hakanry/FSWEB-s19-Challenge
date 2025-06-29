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
@RequestMapping("/dislike")
@AllArgsConstructor
public class DislikeController {

    private LikeService likeService;

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LikeResponseDto like(@AuthenticationPrincipal User user, @Positive @PathVariable("tweetId") long tweetId){
        if (user == null) {
            throw new UserNotLoginAlreadyException("Giriş yapmalısınız!");
        }else{
            long userId = user.getId();
            return likeService.unLikedTweet(userId,tweetId);
        }
    }
}
