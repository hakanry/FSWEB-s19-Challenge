package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.entity.Like;
import com.workintech.s19_twitter_challange.entity.ReTweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReTweetRepository extends JpaRepository<ReTweet,Long> {
    @Query("SELECT r FROM ReTweet r WHERE r.user.id = :userId AND r.tweet.id = :tweetId")
    Optional<ReTweet> findByUserIdAndTweetId(long userId, long tweetId);
}
