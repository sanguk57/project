package com.portfolio.blog.controller;

import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.dto.BlogListDTO;
import com.portfolio.blog.dto.MemberDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.MemberRepository;
import com.portfolio.blog.service.BlogInfoService;
import com.portfolio.blog.service.BlogListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
    private final MemberRepository memberRepository;
    private final BlogListService blogListService;
    private final BlogInfoService blogInfoService;

    @GetMapping("/blogMain")
    public String main(Authentication authentication, HttpSession session) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();
            LOGGER.info(userDetails.getUsername());

        MemberDTO memberDTO = new MemberDTO();
        Optional<Member> member = memberRepository.findById(id);

        memberDTO.setNickName(member.get().getNickName());
        memberDTO.setName(member.get().getName());
        memberDTO.setId(member.get().getId());

        session.setAttribute("memberDTO", memberDTO);
//        log.info("세션값 확인 : " + memberDTO);
        return "blog/blogForm";
    }

    //블로그생성
    @GetMapping("/blogCreate")
    public String createBlog(Model model) {
        model.addAttribute("blogInfoDTO", new BlogInfoDTO());
        model.addAttribute("blogListDTO", new BlogListDTO());
        return "blog/createBlogForm";
    }

    //블로그정보
    @PostMapping("/blogCreate")
    public String createBlogResult(@Valid BlogInfoDTO blogInfoDTO,
                                   @Valid BlogListDTO blogListDTO,
                                   @RequestParam(value = "blogLogoImg") List<MultipartFile> blogLogoImg,
                                   @RequestParam("id") Member id,
                                   BindingResult bindingResult,
                                   Model model) {

        if (bindingResult.hasErrors()) {
            log.info("에러------------발견");
            return "blog/createBlogForm";
        }

        blogInfoDTO.setMember(id);
        blogListDTO.setMember(id);

        log.info("blogInfoDTO : " + blogInfoDTO);
        log.info("blogListDTO : " + blogListDTO);

        blogInfoService.saveBlogInfo(blogInfoDTO, blogLogoImg);
        blogListService.saveBlogList(blogListDTO);

        return "redirect:/blog/blogMain";
    }// 파일업로드는 하다말았음.

    @GetMapping("/PostCreate")
    public String createPost() {

        return "main/createPostForm";
    }
}