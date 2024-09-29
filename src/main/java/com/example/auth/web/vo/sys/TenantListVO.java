package com.example.auth.web.vo.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "租户列表对象")
@Data
public class TenantListVO implements Serializable {

    private String tenantCode;

    private String tenantName;

    private String address;

    private String iconUrl;

    private String contactName;

    private String phone;

    @Schema(description = "社区管理员账户")
    private String account;

    @Schema(description = "社区状态 0正常 1冻结")
    private Integer flag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;


    @Schema(description = "过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime expireTime;
}
