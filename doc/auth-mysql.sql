
CREATE TABLE `sys_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tenant_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '租户编码',
  `user_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户编码',
  `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '账户',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '呢称',
  `real_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '真实姓名',
  `avatar` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `gender` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '性别类型',
  `flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态标识',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0未删除1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `sort` tinyint unsigned NOT NULL DEFAULT '99' COMMENT '排序字段，越小越靠前',
  `admin_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否公司管理员，0否1是，默认否',
  `super_admin_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否系统超级管理员。0否1是',
  `dept_code` varchar(20) NOT NULL DEFAULT '' COMMENT '部门编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户基础信息表';

-- 初始化超级管理员 密码加密 sha256
INSERT INTO auth.sys_user
(id, tenant_code, user_code, account, password, nick_name, real_name, avatar, email, phone, gender, flag, del_flag, create_time, update_time, sort, admin_flag, super_admin_flag, dept_code)
VALUES(null, '0000', 'admin', 'admin', 'd87fb43d72828a4607a7cadbb7f57bd9925ab1cb99e5f60b2e9f854c99fa6e1f', '管理员', '超级管理员', '', 'super@super.com', '18818187967', 0, 0, 0, '2021-10-19 20:50:44', '2024-08-02 15:42:05', 1, 0, 1, '');

CREATE TABLE `sys_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户编码',
  `role_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否涮出0未删除1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联表';

CREATE TABLE `sys_tenant` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tenant_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '租户编码',
  `community_name` varchar(64) NOT NULL DEFAULT '' COMMENT '租户名称',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `icon_url` varchar(255) NOT NULL DEFAULT '' COMMENT '图片地址',
  `contact_name` varchar(64) NOT NULL DEFAULT '' COMMENT '联系人',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '社区状态',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0未删除1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户信息表';

CREATE TABLE `sys_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tenant_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0000' COMMENT '租户编码',
  `role_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `sort` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `parent_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '上级编码',
  `level_flag` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '层级',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0未删除1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_scope` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

CREATE TABLE `sys_role_menu` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `menu_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单编码',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否涮出0未删除1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tenant_code` varchar(128) NOT NULL DEFAULT '0000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单关联表';

CREATE TABLE `sys_role_dept` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(100) NOT NULL DEFAULT '' COMMENT '角色编码',
  `dept_code` varchar(100) NOT NULL DEFAULT '' COMMENT '部门编码',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0否1是',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tenant_code` varchar(100) NOT NULL DEFAULT '0000' COMMENT '租户编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

CREATE TABLE `sys_dept` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(128) NOT NULL DEFAULT '' COMMENT '部门编码',
  `dept_name` varchar(128) NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_code` varchar(128) NOT NULL DEFAULT '' COMMENT '上级部门编码',
  `ancestors` varchar(1000) NOT NULL DEFAULT '' COMMENT '祖级列表,英文逗号分开',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序值，越大越靠前',
  `leader` varchar(100) NOT NULL DEFAULT '' COMMENT '负责人姓名',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态0启用1禁用',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0否1是',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tenant_code` varchar(128) NOT NULL DEFAULT '' COMMENT '租户编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

CREATE TABLE `sys_menu` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单编码',
  `menu_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '上级编码',
  `menu_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '请求地址',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单资源',
  `sort` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `category_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `action_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作按钮类型',
  `open_flag` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否打开新页面0不是1是',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除0未删除1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tenant_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0000' COMMENT '租户编码',
  `perms` varchar(100) NOT NULL DEFAULT '' COMMENT '权限标识',
  `super_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否平台超管菜单。0否1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单信息表';