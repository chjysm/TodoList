package com.api.wishList.repository;

import com.api.wishList.domain.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WishRepository {

    @PersistenceContext
    private final EntityManager em;

    // 저장
    public Long save( Wish wish ){
        if( wish.getWishNo() == null ){
            em.persist(wish);
        } else {
            em.merge(wish);
        }
        return wish.getWishNo();
    }

    // 조회( 해당 사용자 기준 전체 목록 )
    public List<Wish> findAllByMemberNo(Long memberNo) {
        return em.createQuery("select m from Wish m inner join m.member s where s.memberNo = :memberNo", Wish.class )
                .setParameter("memberNo", memberNo)
                .getResultList();
    }

    // 조회( 가장 최근( 생성일시 기준 )의 위시리스트 1개 )
    public Wish findRecentOneByMemberNo(Long memberNo) {
        return em.createQuery("select m from Wish m inner join m.member s where s.memberNo = :memberNo order by m.createdDate desc", Wish.class )
                .setParameter("memberNo", memberNo)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Wish findOne( Long wishNo ) {
        return em.find( Wish.class, wishNo );
    }

    public void removeOne( Wish wish ) {
        em.remove( wish );
    }

}
