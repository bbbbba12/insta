package com.bk.insta.domain.dto.user;


import com.bk.insta.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private boolean loginUser;
    private boolean follow;
    private User user;
    private int postCount;
    private int userFollowerCount;
    private int userFollowingCount;
}