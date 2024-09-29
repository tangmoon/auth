package com.example.auth.web.vo.sys;

import com.example.auth.common.valid.UpdateAction;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "菜单返回对象")
@Data
public class MenuInfoVO implements Serializable {

    @Schema(description = "菜单id")
    private Long id;

    @Schema(description = "菜单编码")
    @NotBlank(groups = {UpdateAction.class},message = "菜单编码不能为空")
    private String menuCode;

    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    @Schema(description = "上级编码")
    private String parentCode = "0";

    @Schema(description = "菜单地址")
    private String menuPath;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注说明")
    private String remark;

    @Schema(description = "菜单类型0目录1菜单")
    private String actionCode = "0";

    @Schema(description = "是否外链0不是1是")
    private Integer openFlag;

    @Schema(description = "是否选中0否1是")
    private Integer selectFlag = 0;

    @Schema(description = "是否平台超管菜单。0否1是")
    private Integer superFlag;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;
}
