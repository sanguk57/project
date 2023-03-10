package com.portfolio.blog.repository;

import com.portfolio.blog.constant.Authority;
import com.portfolio.blog.constant.Category;
import com.portfolio.blog.dto.PostSearchDTO;
import com.portfolio.blog.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Primary
public class BlogPostRepositoryCustomImpl implements BlogPostRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public BlogPostRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchAuthorityEq1(Authority searchAuthority){
        return searchAuthority == null ? null : QBlogBrdList.blogBrdList.brdWrite.eq(searchAuthority);
    }
    private BooleanExpression searchAuthorityEq2(Authority searchAuthority) {
        return searchAuthority == null ? null : QBlogBrdList.blogBrdList.brdRead.eq(searchAuthority);
    }
    private BooleanExpression searchCategoryEq(Category searchCategory) {
        return searchCategory == null ? null : QBlogPost.blogPost.category.eq(searchCategory);
    }

    private  BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType ==null){
            return null;
        }else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        }else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        }else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        }else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }
        return  QBlogPost.blogPost.regTime.after(dateTime);
    }

    private  BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("postTitle", searchBy)){
            return QBlogPost.blogPost.postTitle.like("%"+searchQuery+"%");
        } else if (StringUtils.equals("postText", searchBy)) {
            return  QBlogPost.blogPost.postText.like("%"+searchQuery+"%");
        } else if (StringUtils.equals("nickName", searchBy)) {
            return QBlogPost.blogPost.member.nickName.like("%" + searchQuery + "%");
        }
        return  null;
    }

    private  BooleanExpression searchByBnum(Long bnum) {
            return bnum != null ? QBlogList.blogList.bnum.eq(bnum) : null;
    }

    @Override
    public Page<BlogPost> getMemberBlogPage(PostSearchDTO postSearchDTO, Pageable pageable) {

        List<BlogPost> blogLists = queryFactory
                .selectFrom(QBlogPost.blogPost)
                .where(
                        regDtsAfter(postSearchDTO.getSearchDateType()),
                        searchAuthorityEq1(postSearchDTO.getBrdWrite()),
                        searchAuthorityEq2(postSearchDTO.getBrdRead()),
                        searchCategoryEq(postSearchDTO.getCategory()),
                        searchByLike(postSearchDTO.getSearchBy(), postSearchDTO.getSearchQuery()),
                        searchByBnum(postSearchDTO.getBnum()),
                        QBlogPost.blogPost.blogBrdList.brdRead.eq(Authority.PERMISSION)
                )
                .orderBy(QBlogPost.blogPost.postTitle.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long total = queryFactory
                .select(Wildcard.count)
                .from(QBlogPost.blogPost)
                .where(
                        regDtsAfter(postSearchDTO.getSearchDateType()),
                        searchAuthorityEq1(postSearchDTO.getBrdWrite()),
                        searchAuthorityEq2(postSearchDTO.getBrdRead()),
                        searchCategoryEq(postSearchDTO.getCategory()),
                        searchByLike(postSearchDTO.getSearchBy(), postSearchDTO.getSearchQuery()),
                        searchByBnum(postSearchDTO.getBnum()),
                        QBlogPost.blogPost.blogBrdList.brdRead.eq(Authority.PERMISSION)
                )
                .fetchOne();
        return new PageImpl<>(blogLists, pageable, total);
    }
}
