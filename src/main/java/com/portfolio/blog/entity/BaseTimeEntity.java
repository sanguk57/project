package com.portfolio.blog.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Data
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false) // 엔티티가 생성되어 저장될 때 시간을 자동 저장
    private LocalDateTime regTime;

    @LastModifiedDate // 엔티티 값을 변경할 때 시간을 자동으로 저장
    private  LocalDateTime updateTime;
}
