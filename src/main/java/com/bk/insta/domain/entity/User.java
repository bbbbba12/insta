package com.bk.insta.domain.entity;


import com.bk.insta.domain.dto.user.UserUpdateDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String profileImgUrl; // 프로파일 이미지 "경로" + 이름

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    public void updateProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public void update(UserUpdateDto userUpdateDto) {
        this.phone = userUpdateDto.getPhone();
        this.name = userUpdateDto.getName();
        this.bio = userUpdateDto.getBio();
        this.website = userUpdateDto.getWebsite();
    }
}
