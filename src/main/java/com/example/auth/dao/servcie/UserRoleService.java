package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.UserRole;
import com.example.auth.dao.mapper.UserRoleMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

    public List<UserRole> findByUserCode(String userCode){
        return this.mapper.selectByUserCode(userCode);
    }
}
