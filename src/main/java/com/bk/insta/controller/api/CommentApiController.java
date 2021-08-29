package com.bk.insta.controller.api;

import com.bk.insta.domain.dto.comment.CommentDto;
import com.bk.insta.domain.entity.Comment;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api")
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/comment")
    Comment addComment(@RequestBody CommentDto commentDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return commentService.addComment(commentDto.getText(), commentDto.getPostId(), principalDetails.getUser().getId());
    }

}
