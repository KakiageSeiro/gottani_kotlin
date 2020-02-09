package com.example.gottani_kotlin.domain;

import com.example.gottani_kotlin.repository.TweetRepository;
import com.example.gottani_kotlin.repository.entity.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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

    // ツイートを1件だけ取得
    @Nullable
    public Tweet retrieveTweetById(Integer id){
        List<Tweet> tweetList = tweetRepository.findAll();

        //引数のIDと同じIDを持つツイートがあれば返却
        for (Tweet tweet : tweetList) {
            if (tweet.getId() == id) {
                return tweet;
            }
        }

        //みつからなかったらNULLを返却
        return null;
    }
}
