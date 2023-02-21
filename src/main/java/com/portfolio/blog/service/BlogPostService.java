package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.dto.BlogPostDTO;
import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> findAll();

    void saveBlogPost(BlogPostDTO blogPostDTO);

    Page<BlogPost> getMemberBlogPage(PostSearchDTO postSearchDTO, Pageable pageable);
}
