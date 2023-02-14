package com.portfolio.blog.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;

@Data
public class BlogMemberVisitCountDTO {
    private  Long mnum;

    private  String id;

}
