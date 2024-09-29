package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.SysDept;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     *
     * @param deptCode
     * @return
     */
    SysDept selectByCode(@Param("deptCode") String deptCode);


    /**
     *
     * @param deptName
     * @return
     */
    SysDept selectByName(@Param("deptName") String deptName);


    /**
     *
     * @param parentCode 上级编码
     * @return
     */
    List<SysDept> selectByParentCode(@Param("parentCode") String parentCode);

    /**
     * 查询租户的部门数量
     * @return
     */
    Integer queryDeptNum();
}
