package com.example.gottani_kotlin.repository;

import com.example.gottani_kotlin.repository.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
}
