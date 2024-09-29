package com.example.auth.web.vo.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "部门列表对象")
@Data
@Accessors(chain = true)
public class SysDeptVO implements Serializable {

    @Schema(description = "部门id")
    private Long id;

    @Schema(description = "部门编码")
    private String deptCode;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "上级编码")
    private String parentCode;

    @Schema(description = "祖级列表,英文逗号分开")
    private String ancestors;

    @Schema(description = "排序字段")
    private Integer sort;

    @Schema(description = "负责人名称")
    private String leader;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "社区编码")
    private String tenantCode;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "角色编码集合")
    private List<String> roleCodes;
}
