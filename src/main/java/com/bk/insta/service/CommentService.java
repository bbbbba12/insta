package com.bk.insta.service;

import com.bk.insta.domain.entity.Comment;

public interface CommentService {
    Comment addComment(String text, Long postId, Long userId);

    void deleteComment(Long id);
}
