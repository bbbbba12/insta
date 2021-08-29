package com.bk.insta.service;


import com.bk.insta.domain.dto.post.PostDto;
import com.bk.insta.domain.dto.post.PostInfoDto;
import com.bk.insta.domain.dto.post.PostUpdateDto;
import com.bk.insta.domain.dto.post.PostUploadDto;
import com.bk.insta.domain.entity.Post;
import com.bk.insta.security.details.PrincipalDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    void save(PostUploadDto postUploadDto, MultipartFile multipartFile, PrincipalDetails principalDetails);

    PostDto getPostDto(Long postId);

    void update(PostUpdateDto postUpdateDto);

    void delete(Long postId);

    PostInfoDto getPostInfoDto(Long postId, Long id);

    Page<Post> getPagePost(Long id, Pageable pageable);
}
