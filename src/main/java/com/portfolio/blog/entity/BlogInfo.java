package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.Role;
import com.portfolio.blog.dto.BlogInfoDTO;
import com.portfolio.blog.dto.MemberDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="blog_info")
@Data
@ToString
public class BlogInfo {
    @Id
    @Column(name="i_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inum;

    // 한명의 유저는 하나의 블로그 양식을 가질 수 있다.
    @JoinColumn(name="Member_id")
    @OneToOne
    private  Member member;
    @Enumerated(EnumType.STRING)
    private Authority blogLogo;

    private String  my_profile;

    private  String titleBgColor;

    private String sideBgColor;

    private String boxBgColor;
    @Enumerated(EnumType.STRING)
    private  Authority viewChk; // 개인 프로필 공개 여부

    /*public static BlogInfo createBlog(BlogInfoDTO blogInfoDTO){
        BlogInfo blogInfo = new BlogInfo();
        // dto -> entity : 1:1 맵핑
        blogInfo.set
        member.setName(memberDTO.getName());
        member.setId(memberDTO.getId());
        member.setNickName(memberDTO.getNickName());
        return member;
    }*/

}
