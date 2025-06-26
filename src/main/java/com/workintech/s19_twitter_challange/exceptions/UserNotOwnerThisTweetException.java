package com.workintech.s19_twitter_challange.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotOwnerThisTweetException extends TwitterException{
    public UserNotOwnerThisTweetException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
