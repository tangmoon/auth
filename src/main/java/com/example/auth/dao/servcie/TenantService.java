package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.TenantEntity;
import com.example.auth.dao.mapper.TenantMapper;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.tenant.TenantManager;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import static com.example.auth.dao.entity.table.TenantEntityTableDef.TENANT_ENTITY;

@Service
public class TenantService extends ServiceImpl<TenantMapper, TenantEntity> {

    public Page<TenantEntity> tenantPage(BasePage basePage){
        Page<TenantEntity> page = new Page<>(basePage.getCurrent(), basePage.getSize());
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(TENANT_ENTITY.TENANT_NAME.like(basePage.getKeyword(), CommonUtils::isNotBlank))
                .orderBy(TENANT_ENTITY.ID.desc());
        return this.page(page, queryWrapper);
    }

    public TenantEntity findByCode(String tenantCode){
        TenantEntity tenantEntity;
        try {
            TenantManager.ignoreTenantCondition();
            tenantEntity = this.mapper.selectByCode(tenantCode);
        } finally {
            TenantManager.restoreTenantCondition();
        }
        return tenantEntity;
    }

    public TenantEntity findByName(String tenantName){
        TenantEntity tenantEntity;
        try {
            TenantManager.ignoreTenantCondition();
            tenantEntity = this.mapper.selectByName(tenantName);
        } finally {
            TenantManager.restoreTenantCondition();
        }
        return tenantEntity;
    }

    public Integer queryTenant(){
        Integer total;
        try {
            TenantManager.ignoreTenantCondition();
            total = this.mapper.queryTenant();
        } finally {
            TenantManager.restoreTenantCondition();
        }
        return total;
    }

}
