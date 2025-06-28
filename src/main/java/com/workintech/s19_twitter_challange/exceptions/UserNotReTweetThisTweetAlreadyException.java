package com.workintech.s19_twitter_challange.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotReTweetThisTweetAlreadyException extends TwitterException {
    public UserNotReTweetThisTweetAlreadyException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
