package com.workintech.s19_twitter_challange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Setter
public class CommentResponseDto {

    private String commentText;
    private String user;
    private String tweet;

}
