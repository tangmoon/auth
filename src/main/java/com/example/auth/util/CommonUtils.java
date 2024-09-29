package com.example.auth.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.example.auth.web.vo.sys.MenuInfoVO;
import com.example.auth.web.vo.sys.SysDeptVO;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class CommonUtils {

    /**
     * 随机UUID
     * @return
     */
    public static String randomUUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return (new UUID(random.nextLong(), random.nextLong())).toString().replace("-", "");
    }

    /**
     *
     * @param content
     * @return
     */
    public static String md5Str(String content) {
        return DigestUtil.md5Hex(content);
    }

    public static String sha256Str(String content){
        return DigestUtil.sha256Hex(content);
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 两个字符串是否相等
     * @param str1
     * @param str2
     * @return
     */
    public static boolean strEquals (String str1, String str2){
        return StringUtils.equals(str1, str2);
    }

    public static double randomDouble(double min, double max) {
        return RandomUtil.randomDouble(min, max);
    }

    public static String randomStr(int len){
        return RandomUtil.randomString(len);
    }

    public static String getSequence(Long seq) {
        DecimalFormat df = new DecimalFormat("0000");
        return df.format(seq);
    }

    public static void copyProperties(Object source, Object target){
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreNullValue(true));
    }

    public static <T> List<T> copyPropertiesList(Collection<?> collection, Class<T> targetType){
        return BeanUtil.copyToList(collection, targetType, CopyOptions.create().setIgnoreNullValue(true));
    }

    public static String getSnowflakeId(){
        return IdUtil.getSnowflakeNextIdStr();
    }

    public static String numSeq(String format, Integer num){
        return NumberUtil.decimalFormat(format, num);
    }

    public static LocalDateTime nextYear(){
        return LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.YEARS);
    }

    public static String formatStr(CharSequence template, Object... params){
        return StrUtil.format(template, params);
    }

    public static List<Tree<String>> constructTree(List<MenuInfoVO> menus){
        TreeNodeConfig config = new TreeNodeConfig();
        config.setIdKey("menuCode");
        config.setParentIdKey("parentCode");
        config.setDeep(3);
        config.setWeightKey("sort");
        config.setChildrenKey("childList");

        return TreeUtil.build(menus, "0", config, ((menu, treeNode) -> {
            treeNode.putExtra("id", menu.getId().toString());
            treeNode.putExtra("parentCode", menu.getParentCode());
            treeNode.putExtra("actionCode", menu.getActionCode());
            treeNode.putExtra("sort", menu.getSort());
            treeNode.putExtra("menuName", menu.getMenuName());
            treeNode.putExtra("menuCode", menu.getMenuCode());
            treeNode.putExtra("menuPath", menu.getMenuPath());
            treeNode.putExtra("openFlag", menu.getOpenFlag());
            treeNode.putExtra("selectFlag", menu.getSelectFlag());
            treeNode.putExtra("superFlag", menu.getSuperFlag());
        }));
    }

    public static List<Tree<String>> constructDeptTree(List<SysDeptVO> deptVOList){
        TreeNodeConfig config = new TreeNodeConfig();
        config.setIdKey("deptCode");
        config.setParentIdKey("parentCode");
        config.setDeep(3);
        config.setWeightKey("sort");
        config.setChildrenKey("childList");

        return TreeUtil.build(deptVOList, "0", config, ((dept, treeNode) -> {
            treeNode.putExtra("id", dept.getId().toString());
            treeNode.putExtra("parentCode", dept.getParentCode());
            treeNode.putExtra("sort", dept.getSort());
            treeNode.putExtra("deptName", dept.getDeptName());
            treeNode.putExtra("deptCode", dept.getDeptCode());
            treeNode.putExtra("ancestors", dept.getAncestors());
            treeNode.putExtra("leader", dept.getLeader());
        }));
    }


    public static void main(String[] args) {
        System.out.println(randomStr(16));
    }
}
