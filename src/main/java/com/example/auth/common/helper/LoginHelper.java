package com.example.auth.common.helper;

import cn.dev33.satoken.stp.StpUtil;
import com.example.auth.common.Constants;
import com.example.auth.web.vo.user.UserInfoVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    /**
     * 获取用户基于token
     */
    public static UserInfoVO getLoginUser() {
        return (UserInfoVO) StpUtil.getSession().get(Constants.LOGIN_USER);
    }

    public static boolean isSuperAdmin(){
        return Constants.ADMIN_FLAG.equals(getLoginUser().getSuperAdminFlag());
    }

    public static boolean isSuperAdmin(UserInfoVO user){
        return Constants.ADMIN_FLAG.equals(user.getSuperAdminFlag());
    }

    public static boolean isAdmin(){
        return Constants.ADMIN_FLAG.equals(getLoginUser().getAdminFlag());
    }

    public static String getTenantCode(){
        return (String) StpUtil.getSession().get(Constants.TENANT_CODE);
    }
}
