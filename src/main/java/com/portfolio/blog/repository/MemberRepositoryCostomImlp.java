package com.portfolio.blog.repository;

import com.portfolio.blog.dto.MemberSearchDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.QBlogList;
import com.portfolio.blog.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class MemberRepositoryCostomImlp implements MemberRepositoryCostom{
        private JPAQueryFactory queryFactory;

    public  MemberRepositoryCostomImlp(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private  BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("nickName", searchBy)){
            return QBlogList.blogList.blogName.like("%"+searchQuery+"%");
        } else if (StringUtils.equals("id", searchBy)) {
            return  QBlogList.blogList.blogDetail.like("%"+searchQuery+"%");

        } else if (StringUtils.equals("name", searchBy)) {
            return QBlogList.blogList.member.nickName.like("%"+searchQuery+"%");
        }
        return  null;
    }
    @Override
    public Page<Member> getMemberList(MemberSearchDTO memberSearchDTO, Pageable pageable) {

        List<Member> MemberList = queryFactory
                .select(QMember.member)
                .where(
                        searchByLike(memberSearchDTO.getSearchBy(), memberSearchDTO.getSearchQuery())
                )
                .orderBy(QMember.member.name.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(Wildcard.count)
                .from(QMember.member)
                .where(
                        searchByLike(memberSearchDTO.getSearchBy(), memberSearchDTO.getSearchQuery())
                )
                .fetchOne();

        return new PageImpl<>(MemberList, pageable, total);
    }
}
