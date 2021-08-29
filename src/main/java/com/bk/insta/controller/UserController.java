package com.bk.insta.controller;

import com.bk.insta.domain.dto.user.UserProfileDto;
import com.bk.insta.domain.dto.user.UserUpdateDto;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/profile")
    public String profile(UserProfileDto userProfileDto) {
        return "user/profile";
    }

    @GetMapping("/user/update")
    public String userUpdateForm() {
        return "user/update";
    }

    @PostMapping("/user/update")
    public String updateUser(UserUpdateDto userUpdateDto, @RequestParam("profileImgUrl") MultipartFile multipartFile,
                      RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
        userService.updateUser(userUpdateDto, multipartFile, principalDetails);
        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }
}
