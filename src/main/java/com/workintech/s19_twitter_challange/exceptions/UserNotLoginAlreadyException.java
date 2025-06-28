package com.workintech.s19_twitter_challange.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotLoginAlreadyException extends TwitterException{
    public UserNotLoginAlreadyException(String message) {
        super(message,HttpStatus.UNAUTHORIZED);
    }
}
