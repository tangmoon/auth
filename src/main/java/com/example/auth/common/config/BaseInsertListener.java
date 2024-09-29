package com.example.auth.common.config;

import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.InsertListener;

import java.time.LocalDateTime;

public class BaseInsertListener implements InsertListener {

    @Override
    public void onInsert(Object o) {
        BaseModel baseModel = (BaseModel) o;
        LocalDateTime now = LocalDateTime.now();
        baseModel.setCreateTime(now);
        baseModel.setUpdateTime(now);
    }
}
