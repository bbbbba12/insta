package com.bk.insta.service.impl;

import com.bk.insta.domain.entity.Likes;
import com.bk.insta.domain.entity.Post;
import com.bk.insta.domain.entity.User;
import com.bk.insta.repository.LikesRepository;
import com.bk.insta.repository.PostRepository;
import com.bk.insta.repository.UserRepository;
import com.bk.insta.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void likes(Long postId, Long userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();

        Likes likes = new Likes();
        likes.setPost(post);
        likes.setUser(user);
        likesRepository.save(likes);
    }

    @Override
    public void unLikes(Long postId, Long userId) {
        likesRepository.unLikes(postId, userId);
    }
}
