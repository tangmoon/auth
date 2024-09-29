package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.SysDept;
import com.example.auth.dao.mapper.SysDeptMapper;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.auth.dao.entity.table.SysDeptTableDef.SYS_DEPT;

@Service
public class SysDeptService extends ServiceImpl<SysDeptMapper, SysDept> {

    public Page<SysDept> deptPage(BasePage basePage){
        Page<SysDept> page = new Page<>(basePage.getCurrent(), basePage.getSize());
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_DEPT.DEPT_NAME.like(basePage.getKeyword(), CommonUtils::isNotBlank))
                .orderBy(SYS_DEPT.ID.desc());
        return this.page(page, queryWrapper);
    }

    public SysDept findByCode(String deptCode){
        return this.mapper.selectByCode(deptCode);
    }

    public SysDept findByName(String deptName){
        return this.mapper.selectByName(deptName);
    }

    public List<SysDept> findByParentCode(String parentCode){
        return this.mapper.selectByParentCode(parentCode);
    }

    public Integer queryDeptNum(){
        return this.mapper.queryDeptNum();
    }
}
