package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    @Query("SELECT m FROM Member m WHERE m.username = :username")
    Optional<User> findByUserName(String username);
}
