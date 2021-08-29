package com.bk.insta.controller.api;

import com.bk.insta.domain.dto.follow.FollowDto;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api")
@RequiredArgsConstructor
public class FollowApiController {

    private final FollowService followService;

    @PostMapping("/follow/{toUserId}")
    public void follow(@PathVariable Long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        followService.follow(principalDetails.getUser().getId(), toUserId);
    }


}
