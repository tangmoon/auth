package com.example.auth.web.vo.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysItem implements Serializable {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;
}
