package com.api.wishList.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/")
    public String index(){
        log.info("test");
        return "index.html";
    }

    @RequestMapping("/joinPage")
    public String join(){
        log.info("join");
        return "joinPage.html";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request, HttpServletResponse response){
        log.info("home");
        return "home.html";
    }

    @RequestMapping("addWishPopup")
    public String addWishPopup(HttpServletRequest request, HttpServletResponse response){
        log.info("addWishPopup");
        return "addWishPopup.html";
    }

}
