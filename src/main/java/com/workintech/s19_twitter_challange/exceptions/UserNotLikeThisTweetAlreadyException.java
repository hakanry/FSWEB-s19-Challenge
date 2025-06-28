package com.workintech.s19_twitter_challange.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotLikeThisTweetAlreadyException extends TwitterException{
    public UserNotLikeThisTweetAlreadyException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
