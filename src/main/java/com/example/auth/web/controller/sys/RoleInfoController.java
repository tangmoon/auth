package com.example.auth.web.controller.sys;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.example.auth.common.ResultVO;
import com.example.auth.common.valid.UpdateAction;
import com.example.auth.web.face.RoleInfoService;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.EditRoleMenusVO;
import com.example.auth.web.vo.sys.RoleInfoVO;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "角色管理")
@RestController
@RequestMapping(value = "/role")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;

    @Operation(summary = "角色分页接口")
    @PostMapping(value = "/page")
    public ResultVO<Page<RoleInfoVO>> deptPage(@Validated @RequestBody BasePage basePage){
        return ResultVO.data(this.roleInfoService.roleInfoPage(basePage));
    }

    @SaCheckRole("admin")
    @Operation(summary = "创建角色")
    @PostMapping(value = "/create")
    public ResultVO addRole(@Validated @RequestBody EditRoleMenusVO editRoleMenusVO) {
        this.roleInfoService.createRole(editRoleMenusVO);
        return ResultVO.data("");
    }

    @SaCheckRole("admin")
    @Operation(summary = "更新角色")
    @PostMapping(value = "/update")
    public ResultVO updateRole(@Validated(UpdateAction.class) @RequestBody EditRoleMenusVO editRoleMenusVO) {
        this.roleInfoService.updateRole(editRoleMenusVO);
        return ResultVO.data("");
    }
}
