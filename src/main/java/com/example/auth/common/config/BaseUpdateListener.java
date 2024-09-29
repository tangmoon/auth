package com.example.auth.common.config;

import com.example.auth.dao.BaseModel;
import com.mybatisflex.annotation.UpdateListener;

import java.time.LocalDateTime;

public class BaseUpdateListener implements UpdateListener {

    @Override
    public void onUpdate(Object o) {
        BaseModel baseModel = (BaseModel) o;
        LocalDateTime now = LocalDateTime.now();
        baseModel.setUpdateTime(now);
    }
}
