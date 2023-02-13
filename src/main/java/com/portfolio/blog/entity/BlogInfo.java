package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Authority;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    private  String BgColor;

    private String boxBgColor;
    @Enumerated(EnumType.STRING)
    private  Authority viewChk; // 개인 프로필 공개 여부

}
