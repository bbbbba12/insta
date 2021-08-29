package com.bk.insta.service.impl;

import com.bk.insta.domain.dto.follow.FollowDto;
import com.bk.insta.domain.entity.Follow;
import com.bk.insta.domain.entity.User;
import com.bk.insta.repository.FollowRepository;
import com.bk.insta.repository.UserRepository;
import com.bk.insta.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final EntityManager em;

    @Override
    @Transactional
    public void follow(Long fromUserId, Long toUserId) {
        Follow follow = new Follow();

        User fromUser = userRepository.findById(fromUserId).get();
        User toUser = userRepository.findById(toUserId).get();

        follow.setFromUser(fromUser);
        follow.setToUser(toUser);
        // 맞팔 여부
        for (Follow follow1 : followRepository.findByFromUserId(toUserId)) {
            if(follow1.getToUser() == fromUser) {
                follow.setMatpal(true);
                follow1.setMatpal(true);
            }
        }
        followRepository.save(follow);
    }

    @Override
    public void unFollow(Long fromUserId, Long toUserId) {
        followRepository.unFollow(fromUserId, toUserId);
    }

    @Override
    public List<FollowDto> getFollower(Long profileId, Long loginId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.from_user_id AND f.to_user_id = ?");
        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, loginId)
                .setParameter(3, profileId);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);
        System.out.println("in service " + followDtoList);
        return followDtoList;
    }

    @Override
    public List<FollowDto> getFollowing(Long profileId, Long loginId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ?");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, loginId)
                .setParameter(3, profileId);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);
        return followDtoList;
    }
}
