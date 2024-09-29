package com.example.auth.dao.entity;

import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_role_dept")
public class SysRoleDept extends BaseModel {

    private String roleCode;

    private String deptCode;

    private String tenantCode;
}
