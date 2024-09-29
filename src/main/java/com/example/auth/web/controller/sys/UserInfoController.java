package com.example.auth.web.controller.sys;

import cn.dev33.satoken.stp.StpUtil;
import com.example.auth.common.ResultVO;
import com.example.auth.common.helper.LoginHelper;
import com.example.auth.web.face.UserInfoService;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.user.EditUserVO;
import com.example.auth.web.vo.user.ResetPasswordVO;
import com.example.auth.web.vo.user.UserInfoVO;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "用户分页接口")
    @PostMapping(value = "/page")
    public ResultVO<Page<UserInfoVO>> userPage(@Validated @RequestBody BasePage basePage){
        return ResultVO.data(this.userInfoService.userInfoVOPage(basePage));
    }

    @Operation(summary = "创建用户接口")
    @PostMapping(value = "/create")
    public ResultVO createUser(@Validated @RequestBody EditUserVO editUserVO){
        String tenantCode = LoginHelper.getTenantCode();
        editUserVO.setTenantCode(tenantCode);
        this.userInfoService.createUser(editUserVO);
        return ResultVO.data("");
    }

    @Operation(summary = "更新用户信息接口")
    @PostMapping(value = "/update")
    public ResultVO updateUser(@Validated @RequestBody EditUserVO editUserVO){
        this.userInfoService.updateUser(editUserVO);
        return ResultVO.data("");
    }

    @Operation(summary = "重置密码接口")
    @PostMapping(value = "/reset")
    public ResultVO resetPassword(@Validated @RequestBody ResetPasswordVO resetPasswordVO){
        String currentUserCode = StpUtil.getLoginIdAsString();
        this.userInfoService.resetPassword(resetPasswordVO, currentUserCode);
        return ResultVO.data("");
    }

    @Operation(summary = "删除用户接口",
            parameters = {@Parameter(name = "userCode", description = "用户编码", required = true)}
    )
    @GetMapping(value = "/remove")
    public ResultVO removeUser(@RequestParam("userCode") String userCode) {
        String currentUserCode = StpUtil.getLoginIdAsString();
        this.userInfoService.removeUser(userCode, currentUserCode);
        return ResultVO.data("");
    }

    @Operation(summary = "获取登陆用户个人信息接口")
    @GetMapping(value = "/info")
    public ResultVO<UserInfoVO> getUserInfo(){
        String userCode = StpUtil.getLoginIdAsString();
        return ResultVO.data(this.userInfoService.getUserInfo(userCode));
    }
}
