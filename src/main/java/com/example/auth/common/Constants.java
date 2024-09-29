package com.example.auth.common;


public interface Constants {

    /**
     * 汉字，英文，数字，下划线，
     */
    String PATTERN = "^[\\u4E00-\\u9FA5A-Za-z0-9_]{2,20}$";

    //8-16个字符，至少1个大写字母，1个小写字母和1个数字：
    String PWD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$";

    /**
     *  英文，数字，下划线，
     */
    String EN_PATTERN = "^\\w{4, 20}$";

    String TENANT_CODE = "tenantCode";


    String LOGIN_USER = "loginUser";

    Integer ADMIN_FLAG = 1;

    /**
     * 数字格式化
     */
    String NUMBER_FORMAT = "0000";
}
