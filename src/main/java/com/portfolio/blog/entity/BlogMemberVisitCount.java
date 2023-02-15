package com.portfolio.blog.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity(name="blog_member_visit_count")
@Table(name="blog_member_visit_count")
@Data
@ToString
public class BlogMemberVisitCount extends  BaseTimeEntity{
    @Id
    @Column(name="m_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long mnum;

    // 한명의 유저는 여러 블로그를 볼 수 있다.
    @JoinColumn(name="Member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private  Member member;
}
