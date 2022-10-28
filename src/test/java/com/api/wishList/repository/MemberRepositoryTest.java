package com.api.wishList.repository;

import com.api.wishList.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() throws Exception{
        // given
        Member member = new Member();
        member.setMemberId( "test" );
        member.setMemberPwd( "test" );
        member.setMemberName( "test" );

        // when
        Long memberNo = memberRepository.save( member );
        Member findMember = memberRepository.findOne( memberNo );

        // then
        Assertions.assertThat( findMember.equals( member ) );
    }
}