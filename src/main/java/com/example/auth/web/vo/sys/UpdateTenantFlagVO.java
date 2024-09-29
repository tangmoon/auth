package com.example.auth.web.vo.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Schema(description = "社区更新状态信息对象")
@Data
public class UpdateTenantFlagVO implements Serializable {

    @Schema(description = "用户状态，0正常1冻结")
    @NotNull(message = "flag不能为空")
    @Range(min = 0, max = 1, message = "flag只能为0或1")
    private Integer flag;

    @Schema(description = "社区编码")
    @NotBlank(message = "tenantCode不能为空")
    private String tenantCode;
}
