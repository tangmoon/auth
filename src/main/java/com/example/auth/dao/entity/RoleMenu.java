package com.example.auth.dao.entity;


import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_role_menu")
public class RoleMenu extends BaseModel {

    private String tenantCode;

    private String roleCode;

    private String menuCode;
}
