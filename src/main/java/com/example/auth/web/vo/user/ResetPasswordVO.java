package com.example.auth.web.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "重置密码对象")
public class ResetPasswordVO implements Serializable {

    @Schema(description = "用户编码")
    @NotBlank(message = "用户编码不能为空")
    private String userCode;

}
