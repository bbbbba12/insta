package com.bk.insta.service.impl;

import com.bk.insta.domain.entity.Comment;
import com.bk.insta.domain.entity.Post;
import com.bk.insta.domain.entity.User;
import com.bk.insta.repository.CommentRepository;
import com.bk.insta.repository.PostRepository;
import com.bk.insta.repository.UserRepository;
import com.bk.insta.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Comment addComment(String text, Long postId, Long userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();
        Comment comment = new Comment();
        comment.setText(text);
        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
