package com.bk.insta.repository;

import com.bk.insta.domain.entity.Follow;
import com.bk.insta.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFromUserId(Long fromUserId);

    List<Follow> findByToUserId(Long toUserId);

    @Modifying
    @Query(value = "DELETE FROM follow WHERE from_user_id = :fromUserId AND to_user_id = :toUserId", nativeQuery = true)
    void unFollow(Long fromUserId, Long toUserId);
}
