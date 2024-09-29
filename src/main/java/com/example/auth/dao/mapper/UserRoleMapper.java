package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.UserRole;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 查询用户关联角色信息
     * @param userCode
     * @return
     */
    List<UserRole> selectByUserCode(@Param("userCode") String userCode);
}
