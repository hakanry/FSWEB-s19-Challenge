package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
