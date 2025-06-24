package com.workintech.s19_twitter_challange.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "retweet")
public class ReTweet {

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
