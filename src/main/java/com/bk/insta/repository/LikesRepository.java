package com.bk.insta.repository;

import com.bk.insta.domain.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Modifying
    @Query(value = "DELETE FROM likes WHERE post_id = :postId AND user_id = :userId", nativeQuery = true)
    void unLikes(Long postId, Long userId);
}
