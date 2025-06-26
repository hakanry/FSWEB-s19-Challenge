package com.workintech.s19_twitter_challange.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotOwnerThisCommentException extends TwitterException{
    public UserNotOwnerThisCommentException(String message) {
        super(message,HttpStatus.BAD_REQUEST);
    }
}
