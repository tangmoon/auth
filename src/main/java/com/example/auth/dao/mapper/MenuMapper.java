package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.Menu;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {



    List<Menu> selectAllList();

    /**
     * 查询非超管菜单列表
     * @return
     */
    List<Menu> selectNotSuperList();

    /**
     *
     * @param menuCode
     * @return
     */
    Menu selectByMenuCode(@Param("menuCode") String menuCode);

    /**
     *
     * @param roleCode 角色编码
     * @return
     */
    List<Menu> selectByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 通过roleCodes查询菜单
     * @param roleCodes 角色编码集合
     * @return
     */
    List<Menu> selectByRoleCodeList(@Param("roleCodes") List<String> roleCodes);

    /**
     *
     * @param parentCode 上级编码
     * @return
     */
    List<Menu> selectByParentCode(@Param("parentCode") String parentCode);

    /**
     * 查询社区的数量
     * @return
     */
    Integer queryMenu();
}
