package com.workintech.s19_twitter_challange.repository;

import com.workintech.s19_twitter_challange.S19TwitterChallangeApplication;
import com.workintech.s19_twitter_challange.entity.ReTweet;
import com.workintech.s19_twitter_challange.entity.Tweet;
import com.workintech.s19_twitter_challange.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = S19TwitterChallangeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReTweetRepositoryTest {
    private final ReTweetRepository reTweetRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    @Autowired
    public ReTweetRepositoryTest(ReTweetRepository reTweetRepository,
                                 UserRepository userRepository,
                                 TweetRepository tweetRepository) {
        this.reTweetRepository = reTweetRepository;
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

        ReTweet reTweet = new ReTweet();
        reTweet.setUser(user);
        reTweet.setTweet(tweet);
        reTweetRepository.save(reTweet);
    }

    @AfterAll
    void tearDown() {
        userRepository.deleteById(user.getId());
    }

    @Test
    @DisplayName("reTweetRepository.findByUserIdAndTweetId doğru çalışıyor mu?")
    void testFindReTweetByUserIdAndTweetId() {
        Optional<ReTweet> result = reTweetRepository.findByUserIdAndTweetId(user.getId(), tweet.getId());

        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getUser().getId());
        assertEquals(tweet.getId(), result.get().getTweet().getId());
    }
}