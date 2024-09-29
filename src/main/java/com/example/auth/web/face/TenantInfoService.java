package com.example.auth.web.face;

import com.example.auth.dao.entity.*;
import com.example.auth.dao.servcie.*;
import com.example.auth.exception.AdminServiceException;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.TenantEditVO;
import com.example.auth.web.vo.sys.TenantListVO;
import com.example.auth.web.vo.sys.UpdateTenantFlagVO;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TenantInfoService {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    public Page<TenantListVO> tenantInfoPage(BasePage basePage){
        Page<TenantEntity> tenantPage = this.tenantService.tenantPage(basePage);
        List<TenantEntity> tenantList = tenantPage.getRecords();
        tenantPage.setRecords(null);
        Page<TenantListVO> infoVOPage = new Page<>();
        CommonUtils.copyProperties(tenantPage, infoVOPage);
        List<TenantListVO> infoVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tenantList)){
            for (TenantEntity tenant : tenantList){
                TenantListVO listVO = new TenantListVO();
                CommonUtils.copyProperties(tenant, listVO);

                User user = this.userService.findAdminByTenantCode(tenant.getTenantCode());
                if (Objects.nonNull(user)){
                    listVO.setAccount(user.getAccount());
                }
                infoVOList.add(listVO);
            }
        }
        infoVOPage.setRecords(infoVOList);
        return infoVOPage;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void addTenant(TenantEditVO editVO){
        String tenantName = editVO.getTenantName();
        TenantEntity tenantEntity = this.tenantService.findByName(tenantName);
        if (Objects.nonNull(tenantEntity)) {
            throw new AdminServiceException("租户已存在");
        }
        // 生成格式化序号
        Integer num = this.tenantService.queryTenant() + 1;
        String communityCode = CommonUtils.numSeq("0000", num);
        tenantEntity = new TenantEntity();
        CommonUtils.copyProperties(editVO, tenantEntity);
        tenantEntity.setTenantCode(communityCode);
        tenantEntity.setExpireTime(CommonUtils.nextYear());
        this.tenantService.save(tenantEntity);

        // 默认创建社区管理员账户
        User user = new User();
        user.setTenantCode(communityCode);
        user.setAccount(communityCode + "0001");
        user.setAdminFlag(1);
        user.setUserCode(CommonUtils.getSnowflakeId());
        user.setPassword(CommonUtils.sha256Str("admin2024"));
        user.setRealName(editVO.getContactName());
        user.setPhone(editVO.getPhone());
        user.setNickName(tenantName + "管理员");
        user.setSort(1);
        this.userService.save(user);
        // 创建管理员角色
        String roleCode = "admin";
        Role role = new Role();
        role.setRoleCode(roleCode);
        role.setRoleName("系统管理员");
        role.setTenantCode(communityCode);
        role.setSort(1);
        role.setRemark("系统管理员");
        this.roleService.save(role);

        // 创建角色用户关联
        UserRole userRole = new UserRole();
        userRole.setRoleCode(roleCode);
        userRole.setUserCode(user.getUserCode());
        this.userRoleService.save(userRole);

        // 创建管理员菜单
        List<Menu> menus = this.menuService.findNotSuperList();
        if (!CollectionUtils.isEmpty(menus)){
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (Menu menu : menus){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuCode(menu.getMenuCode());
                roleMenu.setRoleCode(roleCode);
                roleMenu.setTenantCode(communityCode);
                roleMenus.add(roleMenu);
            }
            this.roleMenuService.saveBatch(roleMenus);
        }
    }

    public void editTenant(TenantEditVO editVO){
        String tenantCode = editVO.getTenantCode();
        TenantEntity tenantEntity = this.tenantService.findByCode(tenantCode);
        if (Objects.isNull(tenantEntity)){
            throw new AdminServiceException("租户不存在");
        }
        String tenantName = editVO.getTenantName();
        TenantEntity tenant = this.tenantService.findByName(tenantName);
        if (Objects.nonNull(tenant)
                && !tenantCode.equals(tenant.getTenantCode())) {
            throw new AdminServiceException("租户名称已存在");
        }
        CommonUtils.copyProperties(editVO, tenantEntity);
        this.tenantService.updateById(tenantEntity);
    }

    public void updateTenantFlag(UpdateTenantFlagVO flagVO){
        String tenantCode = flagVO.getTenantCode();
        TenantEntity tenantEntity = this.tenantService.findByCode(tenantCode);
        if (Objects.isNull(tenantEntity)){
            throw new AdminServiceException("租户不存在");
        }
        Integer flag = flagVO.getFlag();
        tenantEntity.setFlag(flag);
        // 正常状态，重置过期时间
        if (flag == 0){
            tenantEntity.setExpireTime(CommonUtils.nextYear());
        }
        this.tenantService.updateById(tenantEntity);
    }

    public TenantListVO getTenantInfo(String tenantCode){
        TenantEntity communityEntity = this.tenantService.findByCode(tenantCode);
        if (Objects.isNull(communityEntity)){
            throw new AdminServiceException("租户不存在");
        }
        TenantListVO listVO = new TenantListVO();
        CommonUtils.copyProperties(communityEntity, listVO);
        return listVO;
    }
}
