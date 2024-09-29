package com.example.auth.dao.entity;


import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_menu")
public class Menu extends BaseModel {

    private String tenantCode;

    private String menuCode;

    private String menuName;

    private String parentCode;

    private String menuPath;

    private String source;

    private String categoryCode;

    private String actionCode;

    private Integer sort;

    private Integer openFlag;

    private String remark;

    private String perms;

    private Integer superFlag;
}
