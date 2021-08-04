package com.bk.insta.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDto {
    private String email;
    private String password;
    private String phone;
    private String name;
}
