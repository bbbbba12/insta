package com.bk.insta.service.impl;

import com.bk.insta.domain.dto.post.PostDto;
import com.bk.insta.domain.dto.post.PostInfoDto;
import com.bk.insta.domain.dto.post.PostUpdateDto;
import com.bk.insta.domain.dto.post.PostUploadDto;
import com.bk.insta.domain.entity.Post;
import com.bk.insta.domain.entity.User;
import com.bk.insta.repository.PostRepository;
import com.bk.insta.repository.UserRepository;
import com.bk.insta.security.details.PrincipalDetails;
import com.bk.insta.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Value("${file.path}")
    String filePath;

    @Override
    @Transactional
    public void save(PostUploadDto postUploadDto, MultipartFile multipartFile, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + multipartFile.getOriginalFilename();
        Path path = Paths.get(filePath + imageFileName);
        try {
            Files.write(path, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = userRepository.findById(principalDetails.getUser().getId()).get();
        Post post = modelMapper.map(postUploadDto, Post.class);
        post.setPostImgUrl(imageFileName);
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public PostDto getPostDto(Long postId) {
        Post post = postRepository.findById(postId).get();
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public void update(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postUpdateDto.getId()).get();
        post.setTag(postUpdateDto.getTag());
        post.setText(postUpdateDto.getText());
    }

    @Override
    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).get();
        postRepository.delete(post);
    }

    @Override
    public PostInfoDto getPostInfoDto(Long postId, Long id) {
        return null;
    }

    @Override
    public Page<Post> getPagePost(Long id, Pageable pageable) {
        Page<Post> storyList = postRepository.story(id, pageable);
        storyList.forEach(story -> {
            story.setLikesCount(story.getLikes().size());
            story.getLikes().forEach(likes -> {
                if(likes.getUser().getId() == id) story.setLikesState(true);
            });
        });
        return storyList;
    }


}
