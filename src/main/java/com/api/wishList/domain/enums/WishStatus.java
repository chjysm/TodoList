package com.api.wishList.domain.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

/*
    위시리스트 상태 enum
    TODO CODE 및 언어 데이터를 DB로 관리하자.
*/
@Getter
public enum WishStatus {
    TODO,
    ONGOING,
    COMPLETETION,
    ;

    public static WishStatus getStateByName( String name ){

        if( StringUtils.hasText( name ) ){
            for( WishStatus en : WishStatus.values() ) {
                if ( name.equals( en.name() ) ) {
                    return en;
                }
            }
        }

        return null;
    }
}
