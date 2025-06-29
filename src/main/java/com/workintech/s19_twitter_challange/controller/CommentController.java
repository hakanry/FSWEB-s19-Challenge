package com.workintech.s19_twitter_challange.controller;

import com.workintech.s19_twitter_challange.dto.CommentRequestDto;
import com.workintech.s19_twitter_challange.dto.CommentResponseDto;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.service.CommentService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto save(@AuthenticationPrincipal User user, @Positive @PathVariable long tweetId, @Validated @RequestBody CommentRequestDto comment){

            long userId = user.getId();
            return commentService.save(userId,tweetId,comment);
    }

    @PatchMapping("/{tweetId}/{commentId}")
    public CommentResponseDto update(@AuthenticationPrincipal User user,@Positive @PathVariable("tweetId") long tweetId,
                          @Positive @PathVariable("commentId") long commentId,@Validated @RequestBody CommentRequestDto comment){

            long userId = user.getId();
            return commentService.update(userId,tweetId,commentId,comment);

    }
    @DeleteMapping("/{tweetId}/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentResponseDto delete(@AuthenticationPrincipal User user,@Positive @PathVariable("tweetId") long tweetId,@Positive @PathVariable("commentId") long commentId){

            long userId = user.getId();
            return  commentService.delete(userId,tweetId,commentId);

    }
}
