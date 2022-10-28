package com.api.wishList.controller;

import com.api.wishList.Utill.RestResponse;
import com.api.wishList.Utill.RestResponseFactory;
import com.api.wishList.domain.Member;
import com.api.wishList.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class memberController {

    private final MemberService memberService;
    private RestResponseFactory responseFactory = RestResponseFactory.getInstance();

    /**
     * 사용자 로그인
     * TODO AUTH 서버를 따로 두고, JWT 방식을 사용하자..
     * */
    @RequestMapping( value = "login", method = { RequestMethod.POST } )
    public @ResponseBody RestResponse login(HttpServletRequest request, HttpServletResponse response, @RequestBody Member member ) throws Exception{

        HttpSession session =  request.getSession();

        Member resultMember = memberService.login(member);

        if( resultMember != null ){
            session.setAttribute("memberNo", resultMember.getMemberNo() );
        }

        RestResponse result = result = responseFactory.create(request, response);
        return result;
    }


    /**
     * 회원 가입
     * */
    @RequestMapping(value = "join", method = { RequestMethod.POST })
    public @ResponseBody RestResponse join(HttpServletRequest request, HttpServletResponse response, @RequestBody Member member ) throws Exception {
        HttpSession session =  request.getSession();

        memberService.join(member);
        RestResponse result = responseFactory.create(request, response);

        return result;
    }

    /**
     * 회원 탈퇴
     * */
    @RequestMapping(value = "withdrawal", method = { RequestMethod.POST })
    public @ResponseBody RestResponse withdrawal(HttpServletRequest request, HttpServletResponse response, @RequestBody Member member ) throws Exception {
        HttpSession session =  request.getSession();

        // TODO 임시방편
        if( member.getMemberNo() == null ){
            Long memberNo = Long.valueOf( String.valueOf( session.getAttribute("memberNo") ) );
            member.setMemberNo( memberNo );
        }

        memberService.withdrawal( member );
        RestResponse result = responseFactory.create(request, response);

        return result;
    }

}
