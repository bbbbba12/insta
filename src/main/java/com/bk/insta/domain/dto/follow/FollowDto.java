package com.bk.insta.domain.dto.follow;

import lombok.*;

import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class FollowDto {

    private long id;
    private String name;
    private String profileImgUrl;
    private int followState;
    private int loginUser;

    public FollowDto(Long id, String name, String profileImgUrl, int followState, int loginUser) {
        this.id = id.longValue();
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.followState = followState;
        this.loginUser = loginUser;
    }
}