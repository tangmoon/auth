package com.example.auth.web.vo.user;


import com.example.auth.common.Constants;
import com.example.auth.common.valid.UpdateAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "更新用户信息对象")
@Data
public class EditUserVO implements Serializable {

    @Schema(description = "用户编码")
    @NotBlank(groups = {UpdateAction.class}, message = "用户编码不能为空")
    private String userCode;

    /*@ApiModelProperty(value = "用户账户")
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]{4,20}$", message = "4-20位数字和字母")
    private String account;*/

    @Schema(description = "用户密码")
    @Pattern(regexp = Constants.PWD_PATTERN, message = "8-16个字符，至少1个大写字母，1个小写字母和1个数字：")
    private String password;

    @Schema(description = "用户呢称")
    private String nickName;

    @Schema(description = "用户真实姓名")
    private String realName;

    @Schema(description = "用户头像地址")
    private String avatar;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "用户手机号")
    private String phone;

    @Schema(description = "性别 0男1女")
    private Integer gender;

    @Schema(description = "角色编码列表")
    private List<String> roles;

    @Schema(description = "部门编码")
    private String deptCode;

    @JsonIgnore
    private String tenantCode;
}
