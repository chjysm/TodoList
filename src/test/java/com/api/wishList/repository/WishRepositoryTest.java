package com.api.wishList.repository;

import com.api.wishList.domain.enums.WishStatus;
import com.api.wishList.domain.Member;
import com.api.wishList.domain.Wish;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WishRepositoryTest {
    @Autowired
    WishRepository wishRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원의_모든_위시_조회_테스트() throws Exception{

        // given
        Member member = new Member();
        member.setMemberId( "test" );
        member.setMemberPwd( "test" );
        member.setMemberName( "test" );
        Member member2 = new Member();
        member.setMemberId( "test2" );
        member.setMemberPwd( "test2" );
        member.setMemberName( "test2" );
        Long memberNo = memberRepository.save( member );
        Long memberNo2 = memberRepository.save( member2 );
        Member findMember = memberRepository.findOne( memberNo );
        Member findMember2 = memberRepository.findOne( memberNo2 );

        Wish wish = new Wish();
        wish.setWishContents("wish test");
        wish.setWishStatus(WishStatus.TODO);
        wish.setMember(findMember);

        Wish wish2 = new Wish();
        wish2.setWishContents("wish test2");
        wish2.setWishStatus(WishStatus.TODO);
        wish2.setMember(findMember);

        Wish wish3 = new Wish();
        wish3.setWishContents("wish test3");
        wish3.setWishStatus(WishStatus.ONGOING);
        wish3.setMember(findMember2);

        // when
        wishRepository.save(wish);
        wishRepository.save(wish2);
        wishRepository.save(wish3);

        List<Wish> findWishList = wishRepository.findAllByMemberNo(memberNo);

        // then
        Assertions.assertThat(findWishList.size()).isEqualTo(2);
    }

    @Test
    public void 회원의_가장_최근_위시_조회_테스트() throws Exception{

        // given
        Member member = new Member();
        member.setMemberId( "test" );
        member.setMemberPwd( "test" );
        member.setMemberName( "test" );

        Long memberNo = memberRepository.save( member );
        Member findMember = memberRepository.findOne( memberNo );

        Wish wish = new Wish();
        wish.setWishContents("wish test");
        wish.setWishStatus(WishStatus.TODO);
        wish.setMember(findMember);

        Wish wish2 = new Wish();
        wish2.setWishContents("wish test2");
        wish2.setWishStatus(WishStatus.TODO);
        wish2.setMember(findMember);

        Wish wish3 = new Wish();
        wish3.setWishContents("wish test3");
        wish3.setWishStatus(WishStatus.ONGOING);
        wish3.setMember(findMember);

        // when
        wishRepository.save(wish);
        wishRepository.save(wish2);
        wishRepository.save(wish3);

        Wish findWish = wishRepository.findRecentOneByMemberNo(memberNo);

        // then
        Assertions.assertThat( wish3.equals(findWish) );
    }
}