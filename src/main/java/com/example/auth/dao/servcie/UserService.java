package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.User;
import com.example.auth.dao.mapper.UserMapper;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.tenant.TenantManager;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import static com.example.auth.dao.entity.table.UserTableDef.USER;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public User findByAccount(String account){
        return this.mapper.selectByAccount(account);
    }

    /**
     * 统计租户下的用户数
     * @return
     */
    public Integer queryUser(){
        return this.mapper.queryUser();
    }

    public User findByUserCode(String userCode) {
        return this.mapper.selectByUserCode(userCode);
    }

    /**
     * 查询社区主账户信息
     * @param tenantCode
     * @return
     */
    public User findAdminByTenantCode(String tenantCode){
        User user;
        try {
            TenantManager.ignoreTenantCondition();
            user =  this.mapper.selectAdminByTenantCode(tenantCode);
        } finally {
            TenantManager.restoreTenantCondition();
        }
        return user;
    }

    public Page<User> userPage(BasePage basePage){
        Page<User> page = new Page<>(basePage.getCurrent(), basePage.getSize());
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(USER.REAL_NAME.like(basePage.getKeyword(), CommonUtils::isNotBlank))
                .orderBy(USER.ID.desc());
        return this.page(page, queryWrapper);
    }

}
