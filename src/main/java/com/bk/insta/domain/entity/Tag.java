package com.bk.insta.domain.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="post_id")
//    private Post post;

    @CreationTimestamp
    private Timestamp createDate;
}
