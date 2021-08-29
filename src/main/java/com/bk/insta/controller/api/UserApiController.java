package com.bk.insta.controller.api;

import com.bk.insta.domain.dto.follow.FollowDto;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "/api")
public class UserApiController {

    private final FollowService followService;

    @GetMapping("/user/${profileId}/follower")
    public List<FollowDto> getFollower(@PathVariable Long profileId, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        return followService.getFollower(profileId, principalDetails.getUser().getId());
    }



    @GetMapping("/api/user/${profileId}/following")
    public List<FollowDto> getFollowing(@PathVariable Long profileId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return followService.getFollowing(profileId, principalDetails.getUser().getId());
    }
}
