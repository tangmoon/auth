package com.example.auth.web.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;
}
