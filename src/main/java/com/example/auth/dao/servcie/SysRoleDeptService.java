package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.SysRoleDept;
import com.example.auth.dao.mapper.SysRoleDeptMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleDeptService extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> {

    public List<SysRoleDept> findByDeptCode(String deptCode){
        return this.mapper.selectByDeptCode(deptCode);
    }

    public List<SysRoleDept> findByRoleCode(String roleCode){
        return this.mapper.selectByRoleCode(roleCode);
    }
}
