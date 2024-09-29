package com.example.auth.common.config;

import com.example.auth.common.helper.LoginHelper;
import com.example.auth.dao.BaseModel;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.core.tenant.TenantFactory;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class MyBatisFlexConfiguration implements ConfigurationCustomizer {

    public MyBatisFlexConfiguration(){
        FlexGlobalConfig flexGlobalConfig = FlexGlobalConfig.getDefaultConfig();
        BaseInsertListener insertListener = new BaseInsertListener();
        BaseUpdateListener updateListener = new BaseUpdateListener();
        flexGlobalConfig.registerInsertListener(insertListener, BaseModel.class);
        flexGlobalConfig.registerUpdateListener(updateListener, BaseModel.class);
        flexGlobalConfig.setTenantColumn("tenant_code");
        flexGlobalConfig.setPrintBanner(false);
    }

    @Override
    public void customize(FlexConfiguration flexConfiguration) {
        // sql打印
        //flexConfiguration.setLogImpl(StdOutImpl.class);
    }

    @Bean
    public TenantFactory tenantFactory(){
        return () -> {
            // 超级管理员忽略
            if (LoginHelper.isSuperAdmin()){
                return null;
            } else {
                String tenantCode = LoginHelper.getTenantCode();
                log.info("tenantCode: {}", tenantCode);
                return new Object[]{tenantCode};
            }

        };
    }
}
