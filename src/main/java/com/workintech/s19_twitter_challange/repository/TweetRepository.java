package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet,Long> {


}
