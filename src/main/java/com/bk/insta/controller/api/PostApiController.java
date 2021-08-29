package com.bk.insta.controller.api;

import com.bk.insta.domain.entity.Post;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.LikesService;
import com.bk.insta.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final LikesService likesService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> postInfo(@PathVariable Long postId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(postService.getPostInfoDto(postId, principalDetails.getUser().getId()), HttpStatus.OK);
    }

    @PostMapping("/post/{postId}/likes")
    public ResponseEntity<?> likes(@PathVariable long postId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.likes(postId, principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 성공", HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable long postId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.unLikes(postId, principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 취소 성공", HttpStatus.OK);
    }

    @GetMapping("/post/story")
    public Page<Post> story(@PageableDefault(size = 3) Pageable pageable, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 팔로우 한 user의 post의 목록
        return postService.getPagePost(principalDetails.getUser().getId(), pageable);
    }
}
