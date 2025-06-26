package com.workintech.s19_twitter_challange.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class UserNotFoundException extends TwitterException{
    public UserNotFoundException(String message) {
        super(message,HttpStatus.NOT_FOUND);
    }
}
