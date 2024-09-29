package com.example.auth.dao.servcie;

import com.example.auth.dao.entity.Menu;
import com.example.auth.dao.mapper.MenuMapper;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.auth.dao.entity.table.MenuTableDef.MENU;

@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {

    public Page<Menu> menuPage(BasePage basePage){
        Page<Menu> page = new Page<>(basePage.getCurrent(), basePage.getSize());
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(MENU.MENU_NAME.like(basePage.getKeyword(), CommonUtils::isNotBlank))
                .orderBy(MENU.ID.desc());
        return this.page(page, queryWrapper);
    }

    public List<Menu> findAllList(){
        return this.mapper.selectAllList();
    }

    /**
     * 查询非超管菜单列表
     * @return
     */
    public List<Menu> findNotSuperList(){
        return this.mapper.selectNotSuperList();
    }

    public Menu findByMenuCode(String menuCode) {
        return this.mapper.selectByMenuCode(menuCode);
    }

    public List<Menu> findByParentCode(String parentCode){
        return this.mapper.selectByParentCode(parentCode);
    }

    public List<Menu> findByRoleCode(String roleCode) {
        return this.mapper.selectByRoleCode(roleCode);
    }

    public List<Menu> findByRoleCodeList(List<String> roleCodes){
        return this.mapper.selectByRoleCodeList(roleCodes);
    }

    public Integer queryMenu(){
        return this.mapper.queryMenu();
    }
}
