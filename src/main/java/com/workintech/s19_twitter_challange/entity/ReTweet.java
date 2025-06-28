package com.workintech.s19_twitter_challange.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "retweet")
public class ReTweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tweet_id")
    @JsonBackReference("tweet-retweet")
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tuser_id")
    @JsonBackReference("user-retweet")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReTweet reTweet = (ReTweet) o;
        return id == reTweet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
