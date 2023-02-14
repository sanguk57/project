package com.portfolio.blog.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BlogBrdListDTO {
    private  Long  cnum;


    private  String id;

    private  String brdTitle;

    @Enumerated(EnumType.STRING)
    private  char brdRead;
    @Enumerated(EnumType.STRING)
    private  char brdWrite;

    private  String brdWDate;

}
