package com.workintech.s19_twitter_challange.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class UserAlreadyRegisteredException extends TwitterException {
    public UserAlreadyRegisteredException(String message) {
        super(message,HttpStatus.CONFLICT);
    }
}
