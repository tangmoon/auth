package com.example.auth.web.controller.sys;


import com.example.auth.common.ResultVO;
import com.example.auth.common.valid.UpdateAction;
import com.example.auth.web.face.SysDeptInfoService;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.SysDeptEditVO;
import com.example.auth.web.vo.sys.SysDeptVO;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "部门管理")
@RestController
@RequestMapping(value = "/dept")
public class DeptInfoController {

    @Autowired
    private SysDeptInfoService sysDeptInfoService;

    @Operation(summary = "部门分页接口")
    @PostMapping(value = "/page")
    public ResultVO<Page<SysDeptVO>> deptPage(@Validated @RequestBody BasePage basePage){
        return ResultVO.data(this.sysDeptInfoService.deptInfoPage(basePage));
    }

    @Operation(summary = "创建部门")
    @PostMapping(value = "/create")
    public ResultVO create(@Validated @RequestBody SysDeptEditVO editVO){
        this.sysDeptInfoService.createDept(editVO);
        return ResultVO.data("");
    }

    @Operation(summary = "更新部门")
    @PostMapping(value = "/update")
    public ResultVO update(@Validated(UpdateAction.class) @RequestBody SysDeptEditVO editVO){
        this.sysDeptInfoService.updateDept(editVO);
        return ResultVO.data("");
    }

    @Operation(summary = "查询部门详情",
            parameters = {@Parameter(name = "deptCode", description = "部门编码", required = true)}
    )
    @GetMapping(value = "/info")
    public ResultVO<SysDeptVO> info(@RequestParam("deptCode") String deptCode){
        return ResultVO.data(this.sysDeptInfoService.deptInfo(deptCode));
    }
}
