package com.example.auth.common.config;

import com.mybatisflex.core.dialect.impl.CommonsDialectImpl;
import com.mybatisflex.core.query.QueryWrapper;

public class MyPermissionDialect extends CommonsDialectImpl {

    @Override
    public String forSelectByQuery(QueryWrapper queryWrapper) {
        return super.forSelectByQuery(queryWrapper);
    }
}
