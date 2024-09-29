package com.example.auth.dao.mapper;

import com.example.auth.dao.entity.User;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户账户查询用户信息
     * @param account 账户
     * @return
     */
    User selectByAccount(@Param("account") String account);

    /**
     * 统计租户下的用户数
     * @return
     */
    Integer queryUser();

    /**
     * 通过用户编码查询用户信息
     * @param userCode 用户编码
     * @return
     */
    User selectByUserCode(@Param("userCode") String userCode);

    /**
     * 查询租户主账户信息
     * @param tenantCode
     * @return
     */
    User selectAdminByTenantCode(@Param("tenantCode") String tenantCode);
}
