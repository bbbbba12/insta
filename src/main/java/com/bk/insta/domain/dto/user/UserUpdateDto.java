package com.bk.insta.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String name;
    private String password;
    private String website;
    private String bio;
    private String phone;
}
