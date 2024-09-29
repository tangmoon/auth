package com.example.auth.web.config;

import cn.dev33.satoken.stp.StpInterface;
import com.example.auth.web.face.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return this.sysPermissionService.getMenuPermission();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return this.sysPermissionService.getRolePermission();
    }
}
