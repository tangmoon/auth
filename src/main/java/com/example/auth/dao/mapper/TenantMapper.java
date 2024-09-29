package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.TenantEntity;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface TenantMapper extends BaseMapper<TenantEntity> {

    TenantEntity selectByCode(@Param("tenantCode") String tenantCode);

    TenantEntity selectByName(@Param("tenantName") String tenantName);

    Integer queryTenant();
}
