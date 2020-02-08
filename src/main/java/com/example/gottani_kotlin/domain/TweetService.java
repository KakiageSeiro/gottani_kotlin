package com.example.gottani_kotlin.domain;

import com.example.gottani_kotlin.repository.TweetRepository;
import com.example.gottani_kotlin.repository.entity.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    // ツイートする
    public void doTweet(String name, String text) {
        Tweet tweet = new Tweet();
        tweet.setName(name);
        tweet.setText(text);
        tweetRepository.save(tweet);
    }

    // ツイートを取得
    public List<Tweet> retrieveTweets() {
        List<Tweet> tweetList = tweetRepository.findAll();
        return tweetList;
    }
}
