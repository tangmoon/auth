package com.example.auth.dao.entity;

import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_dept")
public class SysDept extends BaseModel {

    private String deptCode;

    private String deptName;

    private String parentCode;

    private String ancestors;

    private Integer sort;

    private String leader;

    private String phone;

    private String email;

    private Integer flag;

    private String tenantCode;
}
