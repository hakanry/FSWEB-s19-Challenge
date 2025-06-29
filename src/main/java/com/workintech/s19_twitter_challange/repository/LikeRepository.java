package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query("SELECT l FROM Like l WHERE l.user.id = :userId AND l.tweet.id = :tweetId")
    Optional<Like> findByUserIdAndTweetId(long userId, long tweetId);
}
