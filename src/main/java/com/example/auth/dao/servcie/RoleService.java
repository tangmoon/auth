package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.Role;
import com.example.auth.dao.mapper.RoleMapper;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.auth.dao.entity.table.RoleTableDef.ROLE;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    public Page<Role> rolePage(BasePage basePage){
        Page<Role> page = new Page<>(basePage.getCurrent(), basePage.getSize());
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(ROLE.ROLE_NAME.like(basePage.getKeyword(), CommonUtils::isNotBlank))
                .orderBy(ROLE.ID.desc());
        return this.page(page, queryWrapper);
    }

    public Role findByRoleCode(String roleCode) {
        return this.mapper.selectByRoleCode(roleCode);
    }

    public Role findByRoleName(String roleName) {
        return this.mapper.selectByRoleName(roleName);
    }

    public List<Role> findByUserCode(String userCode) {
        return this.mapper.selectByUserCode(userCode);
    }

    public Integer queryRole(){
        return this.mapper.queryRole();
    }
}
