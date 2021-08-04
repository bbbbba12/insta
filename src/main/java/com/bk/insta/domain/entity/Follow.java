package com.bk.insta.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="fromUser_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="toUser_id")
    private User toUser;

    @Transient
    private boolean matpal;

    @CreationTimestamp
    private Timestamp createDate;
    @CreationTimestamp
    private Timestamp updateDate;
}
