package com.example.auth.web.vo.sys;

import com.example.auth.common.valid.UpdateAction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "租户编辑对象")
@Data
public class TenantEditVO implements Serializable {

    @NotBlank(groups = {UpdateAction.class} ,message = "租户编码不能为空")
    private String tenantCode;

    @NotBlank(message = "租户名称不能未空")
    private String tenantName;

    private String address;

    private String iconUrl;

    private String contactName;

    private String phone;
}
