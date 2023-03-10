package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.dto.BlogSearchDTO;
import com.portfolio.blog.entity.BlogList;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.MemberFriend;
import com.portfolio.blog.repository.BlogListRepository;
import com.portfolio.blog.service.BlogListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogListServiceImpl implements BlogListService {

    private  final BlogListRepository blogListRepository;

     @Override
     public BlogList findByBnum(Long bnum){
         return blogListRepository.findByBnum(bnum);
    }

    @Override
    public BlogList findByMember_id(String id) {
        return blogListRepository.findByMember_id(id);
    }

    //블로그 정보저장
    @Override
    public void saveBlogList(BlogListDTO blogListDTO) {
        BlogList bloglist = blogListDTO.createBlogList();
        blogListRepository.save(bloglist);
    }

    //블로그 정보수정
    @Override
    public void modifyBlogList(BlogListDTO blogListDTO) {
        Member member = blogListDTO.getId();
        String id = member.getId();
        BlogList blogList = blogListRepository.findByMember_id(id);
        blogList.modifyBlogList(blogListDTO);
    }

    @Override
    public Page<BlogList> getMemberBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable) {
        return blogListRepository.getMemberBlogPage(blogSearchDTO, pageable);
    }

    @Override
    public Page<MemberFriend> getFriendBlogPage(BlogSearchDTO blogSearchDTO, Pageable pageable, String loginId) {
        return blogListRepository.getFriendBlogPage(blogSearchDTO, pageable, loginId);
    }
}
