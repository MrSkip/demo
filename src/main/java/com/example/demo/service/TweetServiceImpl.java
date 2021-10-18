package com.example.demo.service;

import com.example.demo.entity.TweetEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.TweetRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The service is responsible for managing tweets
 */

@Log4j2
public class TweetServiceImpl {

    private TweetRepository tweetRepository;
    private UserServiceImpl userService;

    /**
     * Delete tweet for a logged-in user by tweet id
     *
     * @param tweetId tweet id
     */
    public ResponseEntity<?> deleteTweet(Long tweetId) {
        log.info("Going to delete tweet id {} at time {} for the user",
                LocalDateTime.now(),
                tweetId,
                userService.getCurrentUserFromContext()
        );
        tweetRepository.findById(tweetId).ifPresent(tweetRepository::delete);
        log.debug("Tweet {} has been deleted successfully", tweetId);
        return ResponseEntity.ok().build();
    }

    /**
     * Find all tweets for a logged-in user
     *
     * @return all tweets for a user
     */
    public ResponseEntity<List<TweetEntity>> findAllTweetsForCurrentUser(final UserEntity currentUser) {
        log.info("Going to find all tweets for a user {} at time {}", currentUser, LocalDateTime.now());

        final List<TweetEntity> findAll = tweetRepository.findAll();
        log.debug("Loaded {} tweets from DB", findAll.size());

        final List<TweetEntity> userAll = new ArrayList<>(findAll.size());
        for (TweetEntity tweet : findAll) {
            if (tweet.getCreatedBy() != currentUser) {
                continue;
            } else if (tweet.getCreatedBy() == currentUser) {
                userAll.add(tweet);
            }
        }

        log.debug("Found tweets {} for a user {}", currentUser, userAll);
        return ResponseEntity.ok(findAll);
    }

    @Transactional
    public void createTweetAsync(TweetEntity newTweet) {
        final Runnable runnable = () -> {
            log.info("Going to create a new tweet in a separate thread", newTweet);
            final TweetEntity saved = tweetRepository.save(newTweet);
            log.info("A new tweet has been created at {} with id {}", LocalDateTime.now(), newTweet);
        };
        new Thread(runnable).run();
    }
}
