package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.SysRoleDept;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {

    /**
     * 查询部门关联的
     * @param deptCode
     * @return
     */
    List<SysRoleDept> selectByDeptCode(@Param("deptCode") String deptCode);

    /**
     * 查询角色关联的部门
     * @param roleCode
     * @return
     */
    List<SysRoleDept> selectByRoleCode(@Param("roleCode") String roleCode);
}
