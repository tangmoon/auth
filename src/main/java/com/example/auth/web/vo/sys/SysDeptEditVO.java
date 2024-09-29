package com.example.auth.web.vo.sys;

import com.example.auth.common.Constants;
import com.example.auth.common.valid.UpdateAction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "部门编辑对象")
@Data
public class SysDeptEditVO implements Serializable {

    @Schema(description = "部门编码")
    @NotBlank(groups = {UpdateAction.class} ,message = "部门编码不能为空")
    private String deptCode;

    @Schema(description = "部门名称", required = true)
    @NotBlank(message = "部门名称不能为空")
    @Size(min = 1, max = 10, message = "用户名称长度在1-10之间")
    private String deptName;

    @Schema(description = "上级编码")
    private String parentCode = Constants.NUMBER_FORMAT;


    @Schema(description = "负责人名称")
    private String leader;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "角色编码集合")
    private List<String> roleCodes;

}
