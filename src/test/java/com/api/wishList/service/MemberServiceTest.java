package com.api.wishList.service;

import com.api.wishList.domain.Member;
import com.api.wishList.exception.ApiRuntimeException;
import com.api.wishList.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    // 회원 가입 테스트
    @Test
    public void 회원가입_테스트() throws Exception{

        // Given
        Member member = new Member();
        member.setMemberId("testId");
        member.setMemberName("testName");
        member.setMemberPwd("testPwd");

        // When
        Long saveMemberNo = memberService.join(member);

        // Then
        assertEquals(member, memberRepository.findOne(saveMemberNo));

    }

    // 중복 회원 가입 테스트
    @Test(expected = ApiRuntimeException.class)
    public void 중복_회원가입_테스트() throws Exception{
        // Given
        Member member = new Member();
        member.setMemberId("testId");
        member.setMemberName("testName");
        member.setMemberPwd("testPwd");

        Member member2 = new Member();
        member2.setMemberId("testId");
        member2.setMemberName("testName");
        member2.setMemberPwd("testPwd");

        // When
        Long saveMemberNo = memberService.join(member);
        Long saveMemberNo2 = memberService.join(member2);

        // then
        fail("예외가 발생해야 한다.");

    }
}