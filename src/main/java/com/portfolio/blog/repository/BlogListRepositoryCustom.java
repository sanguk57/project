package com.portfolio.blog.repository;

import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.entity.BlogList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogListRepositoryCustom {

    Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable);
}