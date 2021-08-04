package com.bk.insta.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageSource; // 포스팅 이미지 경로 + 이름
    private String caption;
    private String location;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();

    @Transient
    private int likeCount;

    @CreationTimestamp
    private Timestamp createDate;
    @CreationTimestamp
    private Timestamp updateDate;
}
