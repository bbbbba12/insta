package com.bk.insta.domain.dto.post;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUploadDto {
    private String text;
    private String tag;
}
