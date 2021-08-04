package com.bk.insta.domain.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String bio;
    private String phone;
    private String website;
    private String gender;
    private String profileImage; // 프로파일 이미지 "경로" + 이름

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createDate;
    @CreationTimestamp
    private Timestamp updateDate;

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
