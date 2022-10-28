package com.api.wishList.service;

import com.api.wishList.domain.Member;
import com.api.wishList.domain.Wish;
import com.api.wishList.domain.dto.WishDto;
import com.api.wishList.domain.enums.WishStatus;
import com.api.wishList.exception.ApiRuntimeException;
import com.api.wishList.repository.MemberRepository;
import com.api.wishList.repository.WishRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WishServiceTest {
    @Autowired
    WishService wishService;

    @Autowired
    WishRepository wishRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test(expected = ApiRuntimeException.class)
    public void 위시_상태_업데이트_에러_테스트() throws Exception{
        // Given
        Member member = new Member();
        member.setMemberId( "test" );
        member.setMemberPwd( "test" );
        member.setMemberName( "test" );
        Long memberNo = memberRepository.save( member );

        Wish wish = new Wish();
        wish.setWishContents("test");
        wish.setWishStatus(WishStatus.TODO);
        wish.setMember(member);
        Long saveWishNo = wishRepository.save(wish);

        WishDto errorWish = new WishDto();
        // wishNo 누락
        errorWish.setWishContents("test");
        errorWish.setWishStatusName(WishStatus.TODO.name());
        errorWish.setMemberNo(memberNo);

        // When
        wishService.updateWish( errorWish );

        // Then
        fail("익셉션 발생 해야함");
    }
}