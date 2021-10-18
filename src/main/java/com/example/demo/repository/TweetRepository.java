package com.example.demo.repository;

import com.example.demo.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {

    List<TweetEntity> findAll();

    List<TweetEntity> findAllByCreatedAtAfter(final LocalDateTime date);

}
