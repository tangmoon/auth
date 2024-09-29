package com.example.auth.web.controller.sys;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.example.auth.common.ResultVO;
import com.example.auth.web.face.TenantInfoService;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.TenantEditVO;
import com.example.auth.web.vo.sys.TenantListVO;
import com.example.auth.web.vo.sys.UpdateTenantFlagVO;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "租户管理")
@RestController
@RequestMapping(value = "/tenant")
public class TenantController {

    @Autowired
    private TenantInfoService tenantInfoService;

    @SaCheckRole("super-admin")
    @Operation(summary = "获取社区分页信息")
    @PostMapping(value = "/page")
    public ResultVO<Page<TenantListVO>> page(@Validated @RequestBody BasePage basePage) {
        Page<TenantListVO> tenantInfoPage = this.tenantInfoService.tenantInfoPage(basePage);
        return ResultVO.data(tenantInfoPage);
    }

    @Operation(summary = "创建社区")
    @SaCheckRole("super-admin")
    @PostMapping(value = "/create")
    public ResultVO addCommunity(@Validated @RequestBody TenantEditVO editVO) {
        this.tenantInfoService.addTenant(editVO);
        return ResultVO.data("");
    }

    @Operation(summary = "更新社区")
    @SaCheckRole("super-admin")
    @PostMapping(value = "/update")
    public ResultVO updateCommunity(@Validated @RequestBody TenantEditVO editVO) {
        this.tenantInfoService.editTenant(editVO);
        return ResultVO.data("");
    }

    @SaCheckRole("super-admin")
    @Operation(summary = "更新社区状态")
    @PostMapping(value = "/status")
    public ResultVO updateFlag(@Validated @RequestBody UpdateTenantFlagVO updateTenantFlagVO){
        this.tenantInfoService.updateTenantFlag(updateTenantFlagVO);
        return ResultVO.data("");
    }

    @SaCheckRole("super-admin")
    @Operation(summary = "查询社区",
            parameters = {@Parameter(name = "tenantCode", description = "社区编码", required = true)})
    @GetMapping(value = "/info")
    public ResultVO<TenantListVO> communityInfo(@RequestParam("tenantCode") String tenantCode){
        return ResultVO.data(this.tenantInfoService.getTenantInfo(tenantCode));
    }

}
