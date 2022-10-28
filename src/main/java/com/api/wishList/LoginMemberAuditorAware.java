package com.api.wishList;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;


/**
 * BaseModel 에
 * Session 정보에서 추출한 memberNo를
 * createdByNo, lastModifiedByNo 에 셋팅
 * */
@RequiredArgsConstructor
@Component
public class LoginMemberAuditorAware implements AuditorAware<Long> {
    private final HttpSession httpSession;

    @Override
    public Optional<Long> getCurrentAuditor() {

        if( httpSession.getAttribute("memberNo") == null ){
            return null;
        }

        Long memberNo = Long.valueOf( String.valueOf(  httpSession.getAttribute("memberNo") ) );

        return Optional.ofNullable( memberNo );

    }


}
