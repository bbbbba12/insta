package com.bk.insta.service;

public interface LikesService {
    void likes(Long postId, Long userId);
    void unLikes(Long postId, Long userId);
}
