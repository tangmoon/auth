package com.example.auth.web.face;

import com.example.auth.common.ResultCode;
import com.example.auth.dao.entity.TenantEntity;
import com.example.auth.dao.entity.User;
import com.example.auth.dao.entity.UserRole;
import com.example.auth.dao.servcie.TenantService;
import com.example.auth.dao.servcie.UserRoleService;
import com.example.auth.dao.servcie.UserService;
import com.example.auth.exception.AdminServiceException;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.LoginVo;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.user.EditUserVO;
import com.example.auth.web.vo.user.ResetPasswordVO;
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
public class UserInfoService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private TenantService tenantService;

    public UserInfoVO login(LoginVo loginVo){
        String account = loginVo.getAccount();
        String password = loginVo.getPassword();
        User user = this.userService.findByAccount(account);
        if (Objects.isNull(user)){
            throw new AdminServiceException("用户名或者密码不正确");
        }
        String putPassword = CommonUtils.sha256Str(password);
        String pwd = user.getPassword();
        if (!putPassword.equals(pwd)){
            throw new AdminServiceException("用户名或者密码不正确");
        }
        return copyUserInfo(user);
    }

    public Page<UserInfoVO> userInfoVOPage(BasePage basePage){
        Page<User> userPage = this.userService.userPage(basePage);
        List<User> userList = userPage.getRecords();
        userPage.setRecords(null);
        Page<UserInfoVO> infoVOPage = new Page<>();
        CommonUtils.copyProperties(userPage, infoVOPage);
        List<UserInfoVO> infoVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userList)){
            for (User user : userList){
                infoVOList.add(copyUserInfo(user));
            }
        }
        infoVOPage.setRecords(infoVOList);
        return infoVOPage;
    }

    private UserInfoVO copyUserInfo(User user){
        UserInfoVO userInfoVO = new UserInfoVO();
        CommonUtils.copyProperties(user, userInfoVO);

        String tenantCode = user.getTenantCode();
        TenantEntity tenant = this.tenantService.findByCode(tenantCode);
        if (Objects.nonNull(tenant)){
            userInfoVO.setTenantName(tenant.getTenantName());
        }
        return userInfoVO;
    }

    public UserInfoVO getUserInfo(String userCode){
        User user = this.userService.findByUserCode(userCode);
        if (Objects.isNull(user)){
            throw new AdminServiceException(ResultCode.USER_NOT_EXIST);
        }
        return copyUserInfo(user);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void createUser(EditUserVO editUserVO) {
        String tenantCode = editUserVO.getTenantCode();
        User user = new User();
        CommonUtils.copyProperties(editUserVO, user);
        Integer userTotal = this.userService.queryUser() + 1;
        String account = tenantCode + CommonUtils.numSeq("0000", userTotal);
        user.setAccount(account);
        user.setUserCode(CommonUtils.getSnowflakeId());
        user.setPassword(CommonUtils.sha256Str("admin2024"));
        this.userService.save(user);

        // 保存角色
        List<String> roleCodes = editUserVO.getRoles();
        if (!CollectionUtils.isEmpty(roleCodes)){
            List<UserRole> userRoles = new ArrayList<>();
            for (String roleCode : roleCodes){
                UserRole userRole = new UserRole();
                userRole.setUserCode(user.getUserCode());
                userRole.setRoleCode(roleCode);
                userRoles.add(userRole);
            }
            this.userRoleService.saveBatch(userRoles);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(EditUserVO userInfoVO) {
        String userCode = userInfoVO.getUserCode();
        User user = this.userService.findByUserCode(userCode);
        if (Objects.isNull(user)) {
            throw new AdminServiceException(ResultCode.USER_NOT_EXIST);
        }

        Long userId = user.getId();
        CommonUtils.copyProperties(userInfoVO, user);
        user.setId(userId);
        this.userService.updateById(user);

        // 删除关联角色
        List<UserRole> oldUserRoles = this.userRoleService.findByUserCode(userCode);
        if (!CollectionUtils.isEmpty(oldUserRoles)){
            this.userRoleService.removeByIds(oldUserRoles);
        }
        // 保存角色
        List<String> roleCodes = userInfoVO.getRoles();
        if (!CollectionUtils.isEmpty(roleCodes)){
            List<UserRole> userRoles = new ArrayList<>();
            for (String roleCode : roleCodes){
                UserRole userRole = new UserRole();
                userRole.setUserCode(user.getUserCode());
                userRole.setRoleCode(roleCode);
                userRoles.add(userRole);
            }
            this.userRoleService.saveBatch(userRoles);
        }
    }

    public void resetPassword(ResetPasswordVO resetPasswordVO, String currentUserCode){
        String userCode = resetPasswordVO.getUserCode();
        if (userCode.equals(currentUserCode)){
            throw new AdminServiceException("不能重置自身密码");
        }
        User user = this.userService.findByUserCode(userCode);
        if (Objects.isNull(user)) {
            throw new AdminServiceException(ResultCode.USER_NOT_EXIST);
        }
        user.setPassword(CommonUtils.sha256Str("admin2024"));
        this.userService.updateById(user);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void removeUser(String userCode, String currentUserCode) {
        if (userCode.equals(currentUserCode)){
            throw new AdminServiceException("不能删除自己");
        }
        User user = this.userService.findByUserCode(userCode);
        if (Objects.isNull(user)) {
            throw new AdminServiceException(ResultCode.USER_NOT_EXIST);
        }
        this.userService.removeById(user);
        // 同时删除关联角色
        List<UserRole> userRoles = this.userRoleService.findByUserCode(userCode);
        if (!CollectionUtils.isEmpty(userRoles)){
            this.userRoleService.removeByIds(userRoles);
        }
    }
}
