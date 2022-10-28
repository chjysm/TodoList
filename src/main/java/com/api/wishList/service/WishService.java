package com.api.wishList.service;

import com.api.wishList.domain.Member;
import com.api.wishList.domain.Wish;
import com.api.wishList.domain.dto.WishDto;
import com.api.wishList.domain.enums.WishStatus;
import com.api.wishList.exception.ApiRuntimeException;
import com.api.wishList.repository.MemberRepository;
import com.api.wishList.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WishService {

    private  final WishRepository wishRepository;
    private  final MemberRepository memberRepository;
    /**
     * 위시 리스트 추가
     * */
    public void addWish( WishDto wishDto ){

        // 1. 필수 데이터 확인
        if( !StringUtils.hasText( wishDto.getWishContents() )
                || wishDto.getMemberNo() == null ){
            throw new ApiRuntimeException("필수 요소 누락으로 인한 추가 오류 입니다.");
        }

        // 2. 유저 조회
        Wish wish = wishDto.toEntity();
        Member findMember = memberRepository.findOne( wishDto.getMemberNo() );

        // 3. 유저 확인
        if( findMember == null ){
            throw new ApiRuntimeException("필수 요소 누락으로 인한 추가 오류 입니다.");
        }

        // 4. 추가
        wish.setMember( findMember );
        wish.setWishStatus(WishStatus.TODO);
        wishRepository.save(wish);
    }

    /**
     * 사용자 기준 위시리스트 조회
     * */
    public List<Wish> findWishes( WishDto wishDto ){
        return wishRepository.findAllByMemberNo( wishDto.getMemberNo() );
    }

    /**
     * 사용자 기준 가장 최근( 생성일자 기준 ) 위시리스트 조회
     * */
    public Wish findRecentOne( WishDto wishDto ){
        return wishRepository.findRecentOneByMemberNo( wishDto.getMemberNo() );
    }

    /**
     * 위시 업데이트
     * */
    public void updateWish( WishDto wishDto ){
        if( wishDto.getWishNo() == null
                || wishDto.getWishStatusName() == null ){
            throw new ApiRuntimeException("필수 요소 누락으로 인한 업테이트 오류 입니다.");
        }
        Wish findWish = wishRepository.findOne( wishDto.getWishNo() );
        findWish.setWishStatus( WishStatus.getStateByName( wishDto.getWishStatusName() ) );
    }
}
