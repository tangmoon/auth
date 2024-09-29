package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.RoleMenu;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 通过角色编码list查询关联记录
     * @param roleCodes
     * @return
     */
    List<RoleMenu> selectByRoleCodeList(@Param("roleCodes") List<String> roleCodes,
                                        @Param("tenantCode") String tenantCode);

    /**
     * 通过角色编码查询菜单列表
     * @param roleCode
     * @return
     */
    List<RoleMenu> selectByRoleCode(@Param("roleCode") String roleCode);
}
