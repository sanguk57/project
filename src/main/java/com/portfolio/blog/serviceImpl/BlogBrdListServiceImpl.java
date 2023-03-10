package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogBrdListDTO;
import com.portfolio.blog.dto.BlogPostDTO;
import com.portfolio.blog.entity.BlogBrdList;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.repository.BlogBrdListRepository;
import com.portfolio.blog.service.BlogBrdListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogBrdListServiceImpl implements BlogBrdListService {
    private  final BlogBrdListRepository blogBrdListRepository;

    @Override
    public List<BlogBrdList> findByMember_Id(String id) {
        return blogBrdListRepository.findByMember_Id(id);
    }

    @Override
    public List<BlogBrdList> findAll() {
        return null;
    }

    @Override
    public void saveBlogBrdList(BlogBrdListDTO blogBrdListDTO) {
        BlogBrdList blogBrdList = blogBrdListDTO.createBlogBrdList();
        blogBrdListRepository.save(blogBrdList);
    }

    @Override
    public void modifyBlogBrdList(BlogBrdListDTO blogBrdListDTO) {
        Long Cnum = blogBrdListDTO.getCnum();
        BlogBrdList blogBrdList = blogBrdListRepository.findByCnum(Cnum);
        blogBrdList.modifyBlogBrdList(blogBrdListDTO);
    }

    @Override
    public BlogBrdList findByCnum(Long cnum) {
        return blogBrdListRepository.findByCnum(cnum);
    }
}
