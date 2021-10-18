package com.example.demo.controller;

import com.example.demo.entity.TweetEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequestMapping("/api/tweet")
public class TweetController {

    @Autowired
    private TweetServiceImpl tweetService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Tweet to be removed for the current user
     *
     * @param tweetId tweed id
     * @return 200 status code
     */
    @PostMapping
    public ResponseEntity<?> deleteTweet(@RequestParam("tweetId") Long tweetId) {
        return tweetService.deleteTweet(tweetId);
    }

    /**
     * Returns list of tweets for the user that is currently logged-in
     *
     * @param userId current user id
     * @return list of tweets for the current user
     */
    @GetMapping("/current-user/{userId}")
    public ResponseEntity<?> findAllTweetsForCurrentUser(@PathVariable Long userId) {
        final Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return tweetService.findAllTweetsForCurrentUser(user.orElseThrow());
    }

    @PutMapping("/create-tweet-async")
    public void createTweetAsync(final TweetEntity newTweet) {
        tweetService.createTweetAsync(newTweet);
    }

}
