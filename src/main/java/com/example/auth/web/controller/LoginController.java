package com.example.auth.web.controller;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.auth.common.Constants;
import com.example.auth.common.ResultVO;
import com.example.auth.web.face.UserInfoService;
import com.example.auth.web.vo.LoginVo;
import com.example.auth.web.vo.user.UserInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "登陆管理")
@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HttpServletResponse response;

    @Operation(summary = "登陆接口")
    @PostMapping(value = "login")
    public ResultVO<UserInfoVO> login(@Validated @RequestBody LoginVo loginVo){
        UserInfoVO user = this.userInfoService.login(loginVo);
        StpUtil.login(user.getUserCode());
        StpUtil.getSession().set(Constants.TENANT_CODE, user.getTenantCode());
        StpUtil.getSession().set(Constants.LOGIN_USER, user);
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        response.setHeader(saTokenInfo.getTokenName(), saTokenInfo.getTokenValue());
        return ResultVO.data(user);
    }


    @Operation(summary = "登出接口")
    @GetMapping(value = "logout")
    public ResultVO logout() {
        StpUtil.logout();
        return ResultVO.data("");
    }
}
