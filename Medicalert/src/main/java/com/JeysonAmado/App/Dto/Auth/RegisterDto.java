package com.JeysonAmado.App.Dto.Auth;

import lombok.Data;

@Data
public class RegisterDto {

    private String name;

    private String password;

    private String confirmPassword;

    private String email;
}
