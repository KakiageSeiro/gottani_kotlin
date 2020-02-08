package com.example.gottani_kotlin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TweetController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}
