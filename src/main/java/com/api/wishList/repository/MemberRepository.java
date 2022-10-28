package com.api.wishList.repository;

import com.api.wishList.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    // 저장
    public Long save( Member member ){
        em.persist(member);
        return member.getMemberNo();
    }

    // 조회(단건)
    public Member findOne( Long memberNo ){
        return em.find( Member.class, memberNo );
    }

    // 조회(모든 사용자)
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 삭제
    public void removeOne( Member member ) {
        em.remove( member );
    }

    public List<Member> findByName(String memberName) {
        return em.createQuery("select m from Member m where m.memberName = :memberName",
                        Member.class)
                .setParameter("memberName", memberName)
                .getResultList();
    }
    public List<Member> findById(String memberId) {
        return em.createQuery("select m from Member m where m.memberId = :memberId",
                        Member.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public Member findByIdAndPwd(Member member) {
        return em.createQuery("select m from Member m where m.memberId = :memberId and m.memberPwd = :memberPwd",
                        Member.class)
                .setParameter("memberId", member.getMemberId())
                .setParameter("memberPwd", member.getMemberPwd())
                .getSingleResult();
    }

}
