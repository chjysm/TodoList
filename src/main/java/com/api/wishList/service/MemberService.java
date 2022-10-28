package com.api.wishList.service;

import com.api.wishList.Utill.Encryption;
import com.api.wishList.domain.Member;
import com.api.wishList.domain.Wish;
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
public class MemberService {

    private final MemberRepository memberRepository;
    private final WishRepository wishRepository;

    @Transactional
    public Member login(Member member){
    
        // 1. 필수 데이터 확인
        if( !StringUtils.hasText( member.getMemberId() )
            || !StringUtils.hasText( member.getMemberPwd() ) ){
            throw new ApiRuntimeException("필수 데이터가 없습니다.");
        }

        try{
            // 2. 패스워드 단방향 암호화
            member.setMemberPwd( Encryption.Sha256( member.getMemberPwd() ) );
        }catch ( Exception e ){
            throw new ApiRuntimeException("패스워드 암호화 오류 입니다.");
        }

        Member findMember = null;
        try {
            findMember = memberRepository.findByIdAndPwd(member);
        }catch ( Exception e ){
            throw new ApiRuntimeException("ID 와 패스워드를 확인 해 주세요.");
        }

        return findMember;
    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {

        // 1. 중복 확인
        validateDuplicateMember( member );

        try{
            // 2. 패스워드 단방향 암호화
            member.setMemberPwd( Encryption.Sha256( member.getMemberPwd() ) );
        }catch ( Exception e ){
            throw new ApiRuntimeException("패스워드 암호화 오류 입니다.");
        }

        // 3. 저장
        memberRepository.save( member );

        return member.getMemberNo();
    }

    /**
     * 중복 확인
     * */
    private void validateDuplicateMember( Member member ){
        validateDuplicateMemberWithId( member );
        validateDuplicateMemberWithName( member );
    }

    /**
     *   중복 회원 확인( 닉네임 )
     */
    private void validateDuplicateMemberWithName( Member member ) {
        List<Member> findMembers = memberRepository.findByName( member.getMemberName() );
        if ( !findMembers.isEmpty() ) {
            throw new ApiRuntimeException("이미 존재하는 회원 명 입니다.");
        }
    }

    /**
     *   중복 회원 확인( ID )
     */
    private void validateDuplicateMemberWithId( Member member ) {
        List<Member> findMembers = memberRepository.findById( member.getMemberId() );
        if ( !findMembers.isEmpty() ) {
            throw new ApiRuntimeException("이미 존재하는 회원 ID 입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void withdrawal( Member member ) {

        if( member.getMemberNo() == null ){
            throw new ApiRuntimeException("필수 데이터 미존재");
        }

        List<Wish> wishList = wishRepository.findAllByMemberNo(member.getMemberNo());
        if( wishList != null && wishList.size() > 0 ){
            for( Wish wish : wishList ){
                wishRepository.removeOne(wish);
            }
        }

        Member findMember = memberRepository.findOne(member.getMemberNo());
        memberRepository.removeOne( findMember );
    }


}
