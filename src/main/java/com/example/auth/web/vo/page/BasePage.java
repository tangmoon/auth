package com.example.auth.web.vo.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Schema(description = "基础查询分页对象")
@Data
public class BasePage implements Serializable {

    @Schema(description = "当前分页数在10到50之间")
    @Range(min = 10, max = 50, message = "当前分页数在10到50之间")
    private Integer size = 10;

    @Schema(description = "当前页数最小为1")
    @Min(value = 1, message = "当前页数最小为1")
    private Integer current = 1;

    @Schema(description = "开始时间 yyyy-MM-dd HH:mm:ss")
    private String beginTime;

    @Schema(description = "结束时间 yyyy-MM-dd HH:mm:ss")
    private String endTime;

    @Schema(description = "关键字")
    private String keyword;

    /**
     * 当前登录用户编码
     */
    @JsonIgnore
    private String userCode;

    @Schema(description = "租户编码")
    private String tenantCode;
}
