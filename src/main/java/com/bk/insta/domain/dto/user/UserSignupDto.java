package com.bk.insta.domain.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDto {
    @Email @NotNull
    private String email;

    @NotNull
    private String password;
    private String phone;
    private String name;
}
