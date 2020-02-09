package com.example.gottani_kotlin.domain;

import com.example.gottani_kotlin.repository.TweetRepository;
import com.example.gottani_kotlin.repository.entity.Tweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TweetServiceTest {

    //@Mockアノテーションで指定したクラスを利用する際の挙動を変更させたいクラス
    @InjectMocks
    private TweetService tweetService;

    //@InjectMocksアノテーションで指定したクラス内部で呼び出される際に挙動を変更したいクラス
    @Mock
    private TweetRepository tweetRepository;

    //テスト対象の初期化
    @BeforeEach
    public void setUp() {
        tweetService = new TweetService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doTweetからリポジトリの保存処理を呼び出していること() {
        //テスト対象呼び出し
        final String name = "ユーザーA";
        final String text = "１番目のツイート内容";
        tweetService.doTweet(name, text);

        //TweetRepositoryのsaveメソッドを１回実行していること
        verify(tweetRepository, times(1)).save(any());
    }

    @Test
    void retrieveTweetsでリポジトリから取得したデータをそのまま返すこと() {
        //テスト用データ
        Tweet tweet = new Tweet();
        tweet.setName("ユーザーa");
        tweet.setText("ツイート内容1");

        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet);

        //リポジトリからtweetList（テスト用データ）を返却させる
        when(tweetRepository.findAll()).thenReturn(tweetList);

        //テスト対象呼び出し
        List<Tweet> result = tweetService.retrieveTweets();
        //テストデータを取得できていたらテストケース成功
        if (result.contains(tweet)) {
            return;
        }

        //テストデータを取得できていなかったらテストケース失敗
        fail();
    }

    @Test
    void retrieveTweetByIdで指定したIDのツイートが取得できること() {
        //テスト用データ
        Tweet tweet1 = new Tweet();
        tweet1.setId(1);
        tweet1.setName("ユーザーAAA");
        tweet1.setText("ツイート内容ZZZ");

        Tweet tweet2 = new Tweet();
        tweet2.setId(2);
        tweet2.setName("ユーザーBBB");
        tweet2.setText("ツイート内容XXX");

        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet1);
        tweetList.add(tweet2);

        //リポジトリからtweetList（テスト用データ）を返却させる
        when(tweetRepository.findAll()).thenReturn(tweetList);

        Tweet result = tweetService.retrieveTweetById(1);

        assertEquals(tweet1, result);
    }


    @Test
    void retrieveTweetByIdで指定したIDが存在しない場合はNULLが返却されること() {
        //テスト用データ
        Tweet tweet1 = new Tweet();
        tweet1.setId(1);
        tweet1.setName("ユーザーAAA");
        tweet1.setText("ツイート内容ZZZ");

        Tweet tweet2 = new Tweet();
        tweet2.setId(2);
        tweet2.setName("ユーザーBBB");
        tweet2.setText("ツイート内容XXX");

        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet1);
        tweetList.add(tweet2);

        //リポジトリからtweetList（テスト用データ）を返却させる
        when(tweetRepository.findAll()).thenReturn(tweetList);

        Tweet result = tweetService.retrieveTweetById(3);

        assertEquals(null, result);
    }
}