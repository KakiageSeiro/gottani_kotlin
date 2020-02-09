package com.example.gottani_kotlin.controller;

import com.example.gottani_kotlin.controller.viewModel.TweetRequest;
import com.example.gottani_kotlin.domain.TweetService;
import com.example.gottani_kotlin.repository.entity.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping("/")
    public String index(Model model) {
        //ツイート取得して画面表示
        List<Tweet> tweets = tweetService.retrieveTweets();
        model.addAttribute("tweets", tweets);

        return "index";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("id") String id) {
        //ツイート1件だけ取得して画面表示
        Tweet tweet = tweetService.retrieveTweetById(Integer.parseInt(id));
        if (tweet == null) {
            model.addAttribute("existsTweet", false);
        } else {
            model.addAttribute("existsTweet", true);
            model.addAttribute("tweet", tweet);
        }

        return "detail";
    }

    @PostMapping("/create")
    public String createTweet(@ModelAttribute TweetRequest request) {
        //ツイートして最初の画面にリダイレクト
        tweetService.doTweet(request.getName(), request.getText());

        return "redirect:/";
    }

}
