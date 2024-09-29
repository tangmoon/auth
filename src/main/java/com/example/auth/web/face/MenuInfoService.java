package com.example.auth.web.face;

import cn.hutool.core.lang.tree.Tree;
import com.example.auth.common.helper.LoginHelper;
import com.example.auth.dao.entity.Menu;
import com.example.auth.dao.entity.SysRoleDept;
import com.example.auth.dao.entity.UserRole;
import com.example.auth.dao.servcie.MenuService;
import com.example.auth.dao.servcie.SysRoleDeptService;
import com.example.auth.dao.servcie.UserRoleService;
import com.example.auth.exception.AdminServiceException;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.MenuInfoVO;
import com.example.auth.web.vo.user.UserInfoVO;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenuInfoService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    public Page<MenuInfoVO> menuInfoPage(BasePage basePage){
        Page<Menu> menuPage = this.menuService.menuPage(basePage);
        List<Menu> menuList = menuPage.getRecords();
        menuPage.setRecords(null);
        Page<MenuInfoVO> infoVOPage = new Page<>();
        CommonUtils.copyProperties(menuPage, infoVOPage);
        List<MenuInfoVO> infoVOList = CommonUtils.copyPropertiesList(menuList, MenuInfoVO.class);
        infoVOPage.setRecords(infoVOList);
        return infoVOPage;
    }

    public void createMenu(MenuInfoVO menuInfoVO) {
        // 生成格式化序号
        Menu menu = new Menu();
        CommonUtils.copyProperties(menuInfoVO, menu);
        this.menuService.save(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuInfoVO menuInfoVO) {
        String menuCode = menuInfoVO.getMenuCode();
        Menu menu = this.menuService.findByMenuCode(menuCode);
        if (Objects.isNull(menu)) {
            throw new AdminServiceException("菜单不存在");
        }
        Long menuId = menu.getId();
        CommonUtils.copyProperties(menuInfoVO, menu);
        menu.setId(menuId);
        this.menuService.updateById(menu);
    }

    public List<MenuInfoVO> getMenuChild(String parentCode){
        List<Menu> menus = this.menuService.findByParentCode(parentCode);
        return CommonUtils.copyPropertiesList(menus, MenuInfoVO.class);
    }

    public MenuInfoVO getMenuInfo(String menuCode) {
        Menu menu =  this.menuService.findByMenuCode(menuCode);
        if (Objects.isNull(menu)) {
            throw new AdminServiceException("菜单不存在");
        }
        MenuInfoVO infoVO = new MenuInfoVO();
        CommonUtils.copyProperties(menu, infoVO);
        return infoVO;
    }

    public List<Menu> findByRoleCodes(List<String> roleCodes){
        return this.menuService.findByRoleCodeList(roleCodes);
    }

    /**
     * 生成树
     * @return
     */
    public List<Tree<String>> menuTree(UserInfoVO user){
        String userCode = user.getUserCode();
        List<Menu> allMenus = new ArrayList<>();
        if (LoginHelper.isSuperAdmin(user)){
            allMenus = this.menuService.findAllList();
        } else {
            List<String> roleCodes = new ArrayList<>();
            // 查询用户关联角色
            if (CommonUtils.isNotBlank(userCode)){
                List<UserRole> userRoles = this.userRoleService.findByUserCode(userCode);
                for (UserRole userRole : userRoles){
                    roleCodes.add(userRole.getRoleCode());
                }
                String deptCode = user.getDeptCode();
                // 查询部门角色
                if (CommonUtils.isNotBlank(deptCode)){
                    List<SysRoleDept> roleDepts = this.sysRoleDeptService.findByDeptCode(deptCode);
                    for (SysRoleDept roleDept : roleDepts){
                        roleCodes.add(roleDept.getRoleCode());
                    }
                }
                // 查询角色关联菜单
                if (!CollectionUtils.isEmpty(roleCodes)){
                    allMenus = this.menuService.findByRoleCodeList(roleCodes);
                }
            }

        }
        List<MenuInfoVO> menuInfoVOS = new ArrayList<>();
        for (Menu menu : allMenus){
            MenuInfoVO infoVO = new MenuInfoVO();
            CommonUtils.copyProperties(menu, infoVO);
            menuInfoVOS.add(infoVO);
        }
        return CommonUtils.constructTree(menuInfoVOS);
    }
}
