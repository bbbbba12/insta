package com.bk.insta.repository;

import com.bk.insta.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post WHERE user_id IN (SELECT to_user_id FROM FOLLOW WHERE from_user_id = :id) ORDER BY id DESC", nativeQuery = true)
    Page<Post> story(Long id, Pageable pageable);
}
