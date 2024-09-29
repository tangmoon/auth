package com.example.auth.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class BaseModel implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    @Column(isLogicDelete = true)
    private Integer delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private Map<String, Object> params;
}
