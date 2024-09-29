package com.example.auth.web.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "用户信息对象")
@Data
public class UserInfoVO implements Serializable {

    @Schema(description = "用户编码")
    private String userCode;

    @Schema(description = "用户账户")
    private String account;

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

    @Schema(description = "状态")
    private Integer flag;

    @Schema(description = "创建时间 格式:yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "角色编码列表")
    private List<String> roles;

    @Schema(description = "是否管理员")
    private Integer adminFlag;

    @Schema(description = "是否超级管理员")
    private Integer superAdminFlag;

    @Schema(description = "租户编码")
    private String tenantCode;

    @Schema(description = "租户社区名称")
    private String tenantName;

    @Schema(description = "部门编码")
    private String deptCode;

    @Schema(description = "部门名称")
    private String deptName = "";
}
