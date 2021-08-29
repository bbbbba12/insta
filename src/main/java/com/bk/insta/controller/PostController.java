package com.bk.insta.controller;

import com.bk.insta.domain.dto.post.PostUpdateDto;
import com.bk.insta.domain.dto.post.PostUploadDto;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/upload")
    public String postUpload() {
        return "/post/upload";
    }

    @PostMapping("/post")
    public String post(PostUploadDto postUploadDto, @RequestParam("uploadImgUrl") MultipartFile multipartFile,
                       @AuthenticationPrincipal PrincipalDetails principalDetails,
                       RedirectAttributes redirectAttributes
    ) {
        postService.save(postUploadDto, multipartFile, principalDetails);
        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }

    @GetMapping("/post/update/{postId}")
    public String update(@PathVariable Long postId, Model model) {
        model.addAttribute("postDto", postService.getPostDto(postId));
        return "post/update";
    }
    @PostMapping("/post/update")
    public String postUpdate(PostUpdateDto postUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes redirectAttributes) {
        postService.update(postUpdateDto);
        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }

    //포스트 삭제 폼
    @DeleteMapping("/post/delete")
    public String delete(@RequestParam("postId") Long postId, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes redirectAttributes) {
        postService.delete(postId);
        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }
}
