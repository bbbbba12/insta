package com.bk.insta.service;


import com.bk.insta.domain.dto.follow.FollowDto;

import java.util.List;

public interface FollowService {
    void follow(Long fromUserId, Long toUserId);

    void unFollow(Long fromUserId, Long toUserId);

    List<FollowDto> getFollower(Long profileId, Long loginId);

    List<FollowDto> getFollowing(Long profileId, Long loginId);
}
