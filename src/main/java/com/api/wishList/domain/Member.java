package com.api.wishList.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member extends BaseModel {
    @Id @GeneratedValue
    private Long memberNo;          // 회원 번호
    private String memberId;        // 회원 ID
    private String memberPwd;       // 회원 비밀번호
    private String memberName;      // 회원 닉네임
}
