package com.example.auth.web.face;

import com.example.auth.common.Constants;
import com.example.auth.dao.entity.Role;
import com.example.auth.dao.entity.RoleMenu;
import com.example.auth.dao.servcie.RoleMenuService;
import com.example.auth.dao.servcie.RoleService;
import com.example.auth.dao.servcie.UserRoleService;
import com.example.auth.exception.AdminServiceException;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.EditRoleMenusVO;
import com.example.auth.web.vo.sys.RoleInfoVO;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoleInfoService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;


    public Page<RoleInfoVO> roleInfoPage(BasePage basePage){
        Page<Role> rolePage = this.roleService.rolePage(basePage);
        List<Role> roleList = rolePage.getRecords();
        rolePage.setRecords(null);
        Page<RoleInfoVO> infoVOPage = new Page<>();
        CommonUtils.copyProperties(rolePage, infoVOPage);
        List<RoleInfoVO> infoVOList = CommonUtils.copyPropertiesList(roleList, RoleInfoVO.class);
        infoVOPage.setRecords(infoVOList);
        return infoVOPage;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createRole(EditRoleMenusVO editRoleMenusVO) {
        Integer num = this.roleService.queryRole();
        String roleCode = "R" + CommonUtils.numSeq(Constants.NUMBER_FORMAT, num + 1);
        String roleName = editRoleMenusVO.getRoleName();
        Role role = this.roleService.findByRoleName(roleName);
        if (Objects.nonNull(role)){
            throw new AdminServiceException("角色名称已存在");
        }
        role = new Role();
        CommonUtils.copyProperties(editRoleMenusVO, role);
        role.setRoleCode(roleCode);
        this.roleService.save(role);
        // 保存菜单
        List<String> menuCodes = editRoleMenusVO.getMenuCodes();
        if (!CollectionUtils.isEmpty(menuCodes)){
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (String menuCode : menuCodes){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleCode(roleCode);
                roleMenu.setMenuCode(menuCode);
                roleMenus.add(roleMenu);
            }
            this.roleMenuService.saveBatch(roleMenus);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRole(EditRoleMenusVO editRoleMenusVO) {
        String roleCode = editRoleMenusVO.getRoleCode();
        Role role = this.roleService.findByRoleCode(roleCode);
        if (Objects.isNull(role)){
            throw new AdminServiceException("角色不存在");
        }
        String roleName = editRoleMenusVO.getRoleName();
        role = this.roleService.findByRoleName(roleName);
        if (Objects.nonNull(role)
                && !role.getRoleCode().equals(roleCode)){
            throw new AdminServiceException("角色名称已存在");
        }
        role.setRoleName(editRoleMenusVO.getRoleName());
        role.setRemark(editRoleMenusVO.getRemark());
        role.setSort(editRoleMenusVO.getSort());
        this.roleService.updateById(role);

        List<RoleMenu> roleMenus = this.roleMenuService.findByRoleCode(roleCode);
        if (!CollectionUtils.isEmpty(roleMenus)){
            this.roleMenuService.removeByIds(roleMenus);
        }

        // 保存菜单
        List<String> menuCodes = editRoleMenusVO.getMenuCodes();
        if (!CollectionUtils.isEmpty(menuCodes)){
            List<RoleMenu> addRoleMenus = new ArrayList<>();
            for (String menuCode : menuCodes){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleCode(roleCode);
                roleMenu.setMenuCode(menuCode);
                addRoleMenus.add(roleMenu);
            }
            this.roleMenuService.saveBatch(addRoleMenus);
        }

    }

}
