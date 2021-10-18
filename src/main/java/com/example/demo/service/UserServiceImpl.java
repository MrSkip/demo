package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired private TweetRepository userRepository;

    public UserEntity getCurrentUserFromContext() {
        // let's just assume it's implemented properly
        return null;
    }
}
