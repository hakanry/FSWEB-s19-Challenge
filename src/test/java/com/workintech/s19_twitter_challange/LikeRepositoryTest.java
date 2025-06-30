package com.workintech.s19_twitter_challange;

import com.workintech.s19_twitter_challange.entity.Like;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.repository.LikeRepository;
import com.workintech.s19_twitter_challange.repository.TweetRepository;
import com.workintech.s19_twitter_challange.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest(classes = S19TwitterChallangeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LikeRepositoryTest {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    @Autowired
    public LikeRepositoryTest(LikeRepository likeRepository,
                              UserRepository userRepository,
                              TweetRepository tweetRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    private User user;
    private Tweet tweet;

    @BeforeAll
    void setUp() {
        user = new User();
        user.setUsername("usertest");
        user.setPassword("passwordtest");
        userRepository.save(user);

        tweet = new Tweet();
        tweet.setTweetText("tweettest");
        tweet.setUser(user);
        tweetRepository.save(tweet);

        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);
        likeRepository.save(like);
    }

    @AfterAll
    void tearDown() {
        userRepository.deleteById(user.getId());
    }

    @Test
    @DisplayName("likeRepository.findByUserIdAndTweetId doğru çalışıyor mu?")
    void testFindLikeByUserIdAndTweetId() {
        Optional<Like> result = likeRepository.findByUserIdAndTweetId(user.getId(), tweet.getId());

        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getUser().getId());
        assertEquals(tweet.getId(), result.get().getTweet().getId());
    }
}