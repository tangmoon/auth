package com.example.auth.web.face;

import com.example.auth.common.helper.LoginHelper;
import com.example.auth.dao.entity.Menu;
import com.example.auth.dao.entity.UserRole;
import com.example.auth.dao.servcie.MenuService;
import com.example.auth.dao.servcie.UserRoleService;
import com.example.auth.web.vo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysPermissionService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取角色数据权限
     * @return
     */
    public List<String> getRolePermission(){
        UserInfoVO user = LoginHelper.getLoginUser();
        Set<String> roles = new HashSet<>();
        if (LoginHelper.isSuperAdmin()){
            roles.add("super-admin");
        }
        if (LoginHelper.isAdmin()){
            roles.add("admin");
        }
        String userCode = user.getUserCode();
        List<UserRole> roleList = this.userRoleService.findByUserCode(userCode);
        if (!CollectionUtils.isEmpty(roleList)){
            for (UserRole userRole : roleList){
                roles.add(userRole.getRoleCode());
            }
        }
        return new ArrayList<>(roles);
    }

    /**
     * 获取用户的权限

     * @return
     */
    public List<String> getMenuPermission(){
        Set<String> perms = new HashSet<>();
        if (LoginHelper.isSuperAdmin()){
            perms.add("*:*:*");
        }
        List<Menu> menus = this.menuService.findByRoleCodeList(getRolePermission());
        if (!CollectionUtils.isEmpty(menus)){
            for (Menu menu : menus){
                perms.add(menu.getPerms());
            }
        }
        return new ArrayList<>(perms);
    }
}
