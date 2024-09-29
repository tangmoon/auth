package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.RoleMenu;
import com.example.auth.dao.mapper.RoleMenuMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {

    public List<RoleMenu> findByRoleCodeList(List<String> roleCodes, String tenantCode){
        return this.mapper.selectByRoleCodeList(roleCodes, tenantCode);
    }

    public List<RoleMenu> findByRoleCode(String roleCode){
        return this.mapper.selectByRoleCode(roleCode);
    }
}
