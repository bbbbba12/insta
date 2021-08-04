package com.bk.insta.service;


import com.bk.insta.domain.dto.UserSignupDto;

public interface UserService {
    boolean join(UserSignupDto userSignupDto);
}
