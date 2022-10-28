package com.api.wishList.controller;

import com.api.wishList.Utill.RestResponse;
import com.api.wishList.Utill.RestResponseFactory;
import com.api.wishList.domain.Wish;
import com.api.wishList.domain.dto.WishDto;
import com.api.wishList.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;
    private RestResponseFactory responseFactory = RestResponseFactory.getInstance();

    /**
     * 위시리스트 추가
     * */
    @RequestMapping( value = "addWish", method = { RequestMethod.POST } )
    public @ResponseBody RestResponse addWish(HttpServletRequest request, HttpServletResponse response, @RequestBody WishDto wishDto ) throws Exception{

        HttpSession session = request.getSession();

        // TODO 임시방편
        if( wishDto.getMemberNo() == null ){
            Long memberNo = Long.valueOf( String.valueOf( session.getAttribute("memberNo") ) );
            wishDto.setMemberNo( memberNo );
        }

        wishService.addWish(wishDto);

        RestResponse result = responseFactory.create(request, response);

        return result;
    }


    /**
     * 위시 리스트 조회
     * */
    @RequestMapping(value = "getWishList", method = { RequestMethod.POST })
    public @ResponseBody RestResponse getWishList(HttpServletRequest request, HttpServletResponse response, @RequestBody WishDto wishDto ) throws Exception {
        HttpSession session =  request.getSession();

        // TODO 임시방편
        if( wishDto.getMemberNo() == null ){
            Long memberNo = Long.valueOf( String.valueOf( session.getAttribute("memberNo") ) );
            wishDto.setMemberNo( memberNo );
        }

        List<Wish> results = wishService.findWishes(wishDto);
        RestResponse result = responseFactory.create(request, response, results);

        return result;
    }

    /**
     * 위시 리스트(가장 최근) 조회
     * */
    @RequestMapping(value = "getRecentOneWish", method = { RequestMethod.POST })
    public @ResponseBody RestResponse getRecentOneWish(HttpServletRequest request, HttpServletResponse response, @RequestBody WishDto wishDto ) throws Exception {
        HttpSession session =  request.getSession();

        // TODO 임시방편
        if( wishDto.getMemberNo() == null ){
            Long memberNo = Long.valueOf( String.valueOf( session.getAttribute("memberNo") ) );
            wishDto.setMemberNo( memberNo );
        }

        Wish results = wishService.findRecentOne(wishDto);
        RestResponse result = responseFactory.create(request, response, results);

        return result;
    }

    /**
     * 위시 리스트(상태 변경) 조회
     * */
    @RequestMapping(value = "updateWish", method = { RequestMethod.POST })
    public @ResponseBody RestResponse updateWish(HttpServletRequest request, HttpServletResponse response, @RequestBody WishDto wishDto ) throws Exception {
        HttpSession session =  request.getSession();

        // TODO 임시방편
        if( wishDto.getMemberNo() == null ){
            Long memberNo = Long.valueOf( String.valueOf( session.getAttribute("memberNo") ) );
            wishDto.setMemberNo( memberNo );
        }

        wishService.updateWish(wishDto);
        RestResponse result = responseFactory.create(request, response);

        return result;
    }

}
