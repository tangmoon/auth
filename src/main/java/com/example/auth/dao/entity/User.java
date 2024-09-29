package com.example.auth.dao.entity;

import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_user")
public class User extends BaseModel {

    private String tenantCode;

    private String userCode;

    private String account;

    private String password;

    private String nickName;

    private String realName;

    private String avatar;

    private String email;

    private String phone;

    private Integer gender;

    private Integer flag;

    private Integer sort;

    private Integer adminFlag;

    private Integer superAdminFlag;

    private String deptCode;
}
