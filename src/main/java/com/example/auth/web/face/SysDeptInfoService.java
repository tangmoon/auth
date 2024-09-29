package com.example.auth.web.face;

import com.example.auth.common.Constants;
import com.example.auth.common.ResultCode;
import com.example.auth.dao.entity.SysDept;
import com.example.auth.dao.entity.SysRoleDept;
import com.example.auth.dao.servcie.SysDeptService;
import com.example.auth.dao.servcie.SysRoleDeptService;
import com.example.auth.exception.AdminServiceException;
import com.example.auth.util.CommonUtils;
import com.example.auth.web.vo.page.BasePage;
import com.example.auth.web.vo.sys.SysDeptEditVO;
import com.example.auth.web.vo.sys.SysDeptVO;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SysDeptInfoService {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    public Page<SysDeptVO> deptInfoPage(BasePage basePage){
        Page<SysDept> deptPage = this.sysDeptService.deptPage(basePage);
        List<SysDept> deptList = deptPage.getRecords();
        deptPage.setRecords(null);
        Page<SysDeptVO> infoVOPage = new Page<>();
        CommonUtils.copyProperties(deptPage, infoVOPage);
        List<SysDeptVO> infoVOList = CommonUtils.copyPropertiesList(deptList, SysDeptVO.class);
        infoVOPage.setRecords(infoVOList);
        return infoVOPage;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void createDept(SysDeptEditVO deptVO) {
        String deptName = deptVO.getDeptName();
        SysDept sysDept = this.sysDeptService.findByName(deptName);
        // 名称是否重副
        if (Objects.nonNull(sysDept)) {
            throw new AdminServiceException(ResultCode.DEPT_EXIST);
        } else {
            Integer deptNum = this.sysDeptService.queryDeptNum();
            String deptCode = "D" + CommonUtils.numSeq(Constants.NUMBER_FORMAT, deptNum + 1);
            sysDept = new SysDept();
            CommonUtils.copyProperties(deptVO, sysDept);
            sysDept.setId(null);
            sysDept.setDeptCode(deptCode);

            String parentCode = deptVO.getParentCode();
            sysDept.setAncestors(parentCode);
            // 拼接
            if (CommonUtils.isNotBlank(parentCode)
                    && !Constants.NUMBER_FORMAT.equals(parentCode)) {
                SysDept parentDept = this.sysDeptService.findByCode(parentCode);
                if (Objects.nonNull(parentDept)) {
                    sysDept.setAncestors(parentDept.getAncestors() + "," + parentCode);
                }
            }
            this.sysDeptService.save(sysDept);

            // 保存角色
            List<String> roleCodes = deptVO.getRoleCodes();
            if (!CollectionUtils.isEmpty(roleCodes)){
                List<SysRoleDept> roleDeptList = new ArrayList<>();
                for (String roleCode : roleCodes){
                    SysRoleDept roleDept = new SysRoleDept();
                    roleDept.setDeptCode(deptCode);
                    roleDept.setRoleCode(roleCode);
                    roleDeptList.add(roleDept);
                }
                this.sysRoleDeptService.saveBatch(roleDeptList);
            }
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateDept(SysDeptEditVO deptVO) {
        String deptName = deptVO.getDeptName();
        String deptCode = deptVO.getDeptCode();
        SysDept sysDept = this.sysDeptService.findByName(deptName);
        if (Objects.nonNull(sysDept)
                && !deptCode.equals(sysDept.getDeptCode())) {
            throw new AdminServiceException(ResultCode.DEPT_EXIST);
        }
        sysDept = this.sysDeptService.findByCode(deptCode);
        if (Objects.isNull(sysDept)) {
            throw new AdminServiceException(ResultCode.DEPT_NOT_EXIST);
        }
        String parentCode = sysDept.getParentCode();
        String editParentCode = deptVO.getParentCode();
        CommonUtils.copyProperties(deptVO, sysDept);
        if (!parentCode.equals(editParentCode)) {
            SysDept parentDept = this.sysDeptService.findByCode(editParentCode);
            if (Objects.nonNull(parentDept)) {
                sysDept.setAncestors(parentDept.getAncestors() + "," + editParentCode);
            }
        }
        this.sysDeptService.updateById(sysDept);

        List<SysRoleDept> sysRoleDepts = this.sysRoleDeptService.findByDeptCode(deptCode);
        if (!CollectionUtils.isEmpty(sysRoleDepts)){
            this.sysRoleDeptService.removeByIds(sysRoleDepts);
        }
        // 保存角色
        List<String> roleCodes = deptVO.getRoleCodes();
        if (!CollectionUtils.isEmpty(roleCodes)){
            List<SysRoleDept> roleDeptList = new ArrayList<>();
            for (String roleCode : roleCodes){
                SysRoleDept roleDept = new SysRoleDept();
                roleDept.setDeptCode(deptCode);
                roleDept.setRoleCode(roleCode);
                roleDeptList.add(roleDept);
            }
            this.sysRoleDeptService.saveBatch(roleDeptList);
        }
    }

    public SysDeptVO deptInfo(String deptCode){
        SysDept sysDept = this.sysDeptService.findByCode(deptCode);
        if (Objects.isNull(sysDept)){
            throw new AdminServiceException(ResultCode.DEPT_NOT_EXIST);
        }
        SysDeptVO deptVO = new SysDeptVO();
        CommonUtils.copyProperties(sysDept, deptVO);
        return deptVO;
    }


}
