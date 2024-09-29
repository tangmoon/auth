package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.Role;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过角色编码查询
     * @param roleCode 角色编码
     * @return
     */
    Role selectByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 通过角色名称查询
     * @param roleName 角色名称
     * @return
     */
    Role selectByRoleName(@Param("roleName") String roleName);

    /**
     *
     * @param userCode 用户编码
     * @return
     */
    List<Role> selectByUserCode(@Param("userCode") String userCode);

    /**
     * 查询角色数量
     * @return
     */
    Integer queryRole();
}
