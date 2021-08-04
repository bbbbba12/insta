package com.bk.insta.security.details;

import com.bk.insta.domain.entity.User;
public class PrincipalDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public PrincipalDetails(User user) {
        super(user.getEmail(), user.getPassword(), null);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
