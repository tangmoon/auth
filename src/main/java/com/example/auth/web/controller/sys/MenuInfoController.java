package com.example.auth.web.controller.sys;


import cn.hutool.core.lang.tree.Tree;
import com.example.auth.common.ResultVO;
import com.example.auth.common.helper.LoginHelper;
import com.example.auth.common.valid.UpdateAction;
import com.example.auth.web.face.MenuInfoService;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.MenuInfoVO;
import com.example.auth.web.vo.user.UserInfoVO;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "菜单管理")
@RestController
@RequestMapping(value = "/menu")
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;

    @Operation(summary = "菜单分页接口")
    @PostMapping(value = "/page")
    public ResultVO<Page<MenuInfoVO>> deptPage(@Validated @RequestBody BasePage basePage){
        return ResultVO.data(this.menuInfoService.menuInfoPage(basePage));
    }

    @Operation(summary = "创建菜单")
    @PostMapping(value = "/create")
    public ResultVO addMenu(@Validated @RequestBody MenuInfoVO menuInfoVO) {
        this.menuInfoService.createMenu(menuInfoVO);
        return ResultVO.data("");
    }

    @Operation(summary = "更新菜单")
    @PostMapping(value = "/update")
    public ResultVO updateMenu(@Validated(UpdateAction.class) @RequestBody MenuInfoVO menuInfoVO) {
        this.menuInfoService.updateMenu(menuInfoVO);
        return ResultVO.data("");
    }

    @Operation(summary = "获取子菜单列表",
            parameters = {@Parameter(name = "parentCode", description = "上级菜单编码", required = true)}
    )
    @PostMapping(value = "/child")
    public ResultVO<List<MenuInfoVO>> getMenuChild(@RequestParam("parentCode") String parentCode){
        return ResultVO.data(this.menuInfoService.getMenuChild(parentCode));
    }

    @Operation(summary = "获取菜单信息",
            parameters = {@Parameter(name = "menuCode", description = "菜单编码", required = true)})
    @GetMapping(value = "/info")
    public ResultVO<MenuInfoVO> getInfo(@RequestParam("menuCode") String menuCode) {
        return ResultVO.data(this.menuInfoService.getMenuInfo(menuCode));
    }

    @Operation(summary = "获取菜单树型")
    @GetMapping(value = "/tree")
    public ResultVO<List<Tree<String>>> tree() {
        UserInfoVO user = LoginHelper.getLoginUser();
        return ResultVO.data(this.menuInfoService.menuTree(user));
    }
}
