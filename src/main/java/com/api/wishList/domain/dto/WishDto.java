package com.api.wishList.domain.dto;


import com.api.wishList.domain.Wish;
import com.api.wishList.domain.enums.WishStatus;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class WishDto {

    private Long wishNo;                    // wish 번호
    private String wishContents;            // wish 내용
    private Long memberNo;                  // (회원)작성자
    private String wishStatusName;          // wish 상태명

    /**
     * Entity 변환 유저 정보는 추가로 조회해야 한다.
     * */
    public Wish toEntity(){
        Wish wish = new Wish();
        wish.setWishNo( this.getWishNo() );
        wish.setWishContents( this.getWishContents() );
        wish.setWishStatus( WishStatus.getStateByName( this.getWishStatusName() ) );
        return wish;
    }
}
