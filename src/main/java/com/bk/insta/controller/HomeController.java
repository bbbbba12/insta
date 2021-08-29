package com.bk.insta.controller;

import com.bk.insta.domain.dto.user.UserSignupDto;
import com.bk.insta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;


    @GetMapping("/signup") //회원 가입 폼으로 이동
    public String signup() {
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(UserSignupDto userSignupDto) {
        if(userService.join(userSignupDto)) {
            return "redirect:/login";
        } else {
            return "redirect:/signup?error";
        }
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @GetMapping("/login") //로그인 화면으로 이동
    public String login() {
        return "login";
    }

    //메인 story 화면으로 이동
    @GetMapping({"/", "post/story"})
    public String story() {
        return "post/story";
    }
}
