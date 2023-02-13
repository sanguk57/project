package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.repository.BlogListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BlogListTests {

    @Autowired
    BlogListRepository blogListRepository;

    @Test
    public void test(){

        BlogListDTO blogListDTO = new BlogListDTO();


    }
}
