package com.example.auth.web.vo.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "角色信息对象")
@Data
public class RoleInfoVO implements Serializable {

    @Schema(description = "角色编码")
    @NotBlank(message = "角色编码不能为空")
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

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;
}
