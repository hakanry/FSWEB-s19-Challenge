package com.workintech.s19_twitter_challange.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "tweet_text")
    @Size(max = 1200)
    private String tweetText;

    @ManyToOne
    @JoinColumn(name = "tuser_id")
    private User user;

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER)
    private List<Like> likes;

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER)
    private List<ReTweet> reTweets;
}

