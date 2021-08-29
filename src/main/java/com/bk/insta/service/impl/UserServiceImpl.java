package com.bk.insta.service.impl;

import com.bk.insta.domain.dto.user.UserSignupDto;
import com.bk.insta.domain.dto.user.UserUpdateDto;
import com.bk.insta.domain.entity.User;
import com.bk.insta.repository.UserRepository;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    @Value("${file.path}")
    String filePath;

    @Override
    @Transactional
    public boolean join(UserSignupDto userSignupDto) {
        if(userRepository.findByEmail(userSignupDto.getEmail()) != null) return false;
        User user = modelMapper.map(userSignupDto, User.class);
        user.updateProfileImgUrl("/img/default_profile.jpg");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto, MultipartFile multipartFile, PrincipalDetails principalDetails) throws Exception {
        User user = userRepository.findById(principalDetails.getUser().getId())
                .orElseThrow(() -> {return new Exception();});

        if(!multipartFile.isEmpty()) {
            String imageFileName = user.getId() + "_" + multipartFile.getOriginalFilename();
            Path imageFilePath = Paths.get(filePath + imageFileName);
            try {
                if (user.getProfileImgUrl() != null) {
                    File file = new File(filePath + user.getProfileImgUrl());
                    file.delete(); // 원래파일 삭제
                }
                Files.write(imageFilePath, multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.updateProfileImgUrl(imageFileName);
        }
        userUpdateDto.setPassword(encoder.encode(userUpdateDto.getPassword()));
        user.update(userUpdateDto);

        principalDetails.updateUser(user);
    }
}
