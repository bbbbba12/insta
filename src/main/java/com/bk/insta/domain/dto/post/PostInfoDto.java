package com.bk.insta.domain.dto.post;

import com.bk.insta.domain.entity.Comment;
import com.bk.insta.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInfoDto {

    private long id;
    private String text;
    private String tag;
    private LocalDateTime createdate;
    private User postUploader;
    private long likesCount;
    private boolean likeState;
    private boolean uploader;
    private String postImgUrl;
    private List<Comment> commentList;
}