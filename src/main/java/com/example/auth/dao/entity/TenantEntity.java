package com.example.auth.dao.entity;

import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_tenant")
public class TenantEntity extends BaseModel {

    private String tenantCode;

    private String tenantName;

    private String address;

    private String iconUrl;

    private String contactName;

    private String phone;

    private Integer flag;

    private LocalDateTime expireTime;
}
