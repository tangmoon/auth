package com.example.auth.dao.entity;

import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_user_role")
public class UserRole extends BaseModel {

    private String userCode;

    private String roleCode;
}
