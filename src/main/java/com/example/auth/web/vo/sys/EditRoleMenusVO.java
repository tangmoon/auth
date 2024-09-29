package com.example.auth.web.vo.sys;

import com.example.auth.common.valid.UpdateAction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EditRoleMenusVO implements Serializable {

    @Schema(description = "角色编码")
    @NotBlank(groups = {UpdateAction.class}, message = "角色编码不能为空")
    private String roleCode;

    @Schema(description = "角色中文名称")
    @NotBlank(message = "角色中文名称不能为空")
    private String roleName;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "角色备注")
    private String remark;

    @Schema(description = "菜单编码")
    private List<String> menuCodes;

}
