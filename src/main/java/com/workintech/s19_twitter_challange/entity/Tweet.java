package com.workintech.s19_twitter_challange.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    @NotBlank(message = "Tweet boş olmamalıdır!")
    @NotNull(message = "Tweet zorunludur!")
    @NotEmpty(message = "Tweet boşluk olmamalıdır!")
    @Size(max = 100,message = "Tweet 0-1200 karakter arası olmalıdır!")
    @Column(name = "tweet_text")
    private String tweetText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tuser_id")
    @JsonBackReference("user-tweet")
    private User user;

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference("tweet-comment")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference("tweet-like")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference("tweet-retweet")
    private List<ReTweet> reTweets = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return id == tweet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

