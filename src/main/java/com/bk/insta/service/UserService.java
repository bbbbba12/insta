package com.bk.insta.service;


import com.bk.insta.domain.dto.user.UserSignupDto;
import com.bk.insta.domain.dto.user.UserUpdateDto;
import com.bk.insta.domain.entity.User;
import com.bk.insta.security.details.PrincipalDetails;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    boolean join(UserSignupDto userSignupDto);

    void updateUser(UserUpdateDto userUpdateDto, MultipartFile multipartFile, PrincipalDetails principalDetails) throws Exception;
}
