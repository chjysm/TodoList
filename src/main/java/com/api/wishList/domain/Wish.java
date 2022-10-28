package com.api.wishList.domain;


import com.api.wishList.domain.enums.WishStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Wish extends BaseModel {
    @Id
    @GeneratedValue
    private Long wishNo;                    // todo 번호

    private String wishContents;            // todo 내용

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(name = "MEMBER_ID_NO")
    @JsonIgnore
    private Member member;                  // (회원)작성자

    @Enumerated(EnumType.STRING)
    private WishStatus wishStatus;          // todo 상태

}
