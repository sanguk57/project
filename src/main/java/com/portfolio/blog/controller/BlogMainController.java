package com.portfolio.blog.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/main")
public class BlogMainController {

    @GetMapping("/mainPage")
    public String main(){

        return "main/main";
    }

    //블로그생성
    @GetMapping("/createBlog")
    public String createBlog(){

        return "main/createBlog";
    }
}
