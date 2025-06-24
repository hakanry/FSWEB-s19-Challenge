package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
