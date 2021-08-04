package com.bk.insta.service.impl;

import com.bk.insta.domain.dto.UserSignupDto;
import com.bk.insta.domain.entity.User;
import com.bk.insta.repository.UserRepository;
import com.bk.insta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public boolean join(UserSignupDto userSignupDto) {
        User user = modelMapper.map(userSignupDto, User.class);
        user.updateProfileImage("/img/default_profile.jpg");
        userRepository.save(user);
        return true;
    }
}
