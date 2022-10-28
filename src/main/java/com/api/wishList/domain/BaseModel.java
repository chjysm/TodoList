package com.api.wishList.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @CreatedBy
    private Long createdByNo;                 // 생성자 번호

    @CreatedDate
    private LocalDateTime createdDate;          // 생성일시

    @LastModifiedBy
    private Long lastModifiedByNo;            // 수정자 번호

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;     // 수정일시
}
