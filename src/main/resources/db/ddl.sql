create schema if not exists admin collate latin1_swedish_ci;

create table if not exists generator_demo
(
	id int auto_increment comment '主键'
		primary key,
	name varchar(255) null comment '名称',
	value varchar(255) null comment '数据'
)
collate=utf8_bin;

create table if not exists menu_basic_face
(
	ID int auto_increment
		primary key,
	MENU_CODE varchar(64) default '' null comment '菜单编号',
	MENU_NAME varchar(128) default '' null comment '菜单名称',
	MENU_ICON varchar(64) default '' null comment '菜单图标',
	MENU_ACTION varchar(255) default '#' null comment '菜单地址',
	PARENT_MENU_CODE varchar(64) default '' null comment '上级菜单编号',
	MENU_TYPE varchar(2) default '00' null comment '菜单类型(01-目录 02-菜单 03-按钮)',
	DISPLAY_INDEX int(11) unsigned default 0 null comment '显示顺序',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
comment '菜单基础信息表' collate=utf8_bin;

create table if not exists organization_basic_face
(
	ID int(11) unsigned auto_increment
		primary key,
	ORGANIZATION_CODE varchar(64) default '' not null comment '编号',
	ORGANIZATION_NAME varchar(255) null comment '名称',
	PAREN_CODE varchar(255) null comment '上级编号',
	ORGANIZATION_STATUS varchar(255) null comment '状态',
	ORGANIZATION_TYPE varchar(2) null comment '类型(00-部门,01-岗位)',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
collate=utf8_bin;

create table if not exists organization_menu_relation
(
	ID int(11) unsigned auto_increment
		primary key,
	ORGANIZATION_CODE varchar(64) default '' not null comment '编号',
	MENU_CODE varchar(64) default '' null comment '菜单编号',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
collate=utf8_bin;

create table if not exists role_basic_face
(
	ID int auto_increment
		primary key,
	ROLE_CODE varchar(64) default '' null comment '角色编号',
	ROLE_NAME varchar(255) default '' null comment '角色名称',
	ROLE_LABEL varchar(255) default '' null comment '角色标签',
	ROLE_MEMO varchar(255) default '' null comment '角色备注',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
comment '角色基本信息表' collate=utf8_bin;

create table if not exists role_menu_relation
(
	ID int auto_increment
		primary key,
	ROLE_CODE varchar(64) default '' null comment '角色编号',
	MENU_CODE varchar(64) default '' null comment '菜单编号',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
comment '角色菜单关联表' collate=utf8_bin;

create table if not exists scheduled_task
(
	id int auto_increment
		primary key,
	task_code varchar(128) default '' null comment '任务编号',
	task_name varchar(128) null comment '任务名称',
	task_type varchar(2) default '' null comment '任务类型(字典表: task_code)',
	task_url varchar(512) default '' null comment '远程任务地址',
	task_service_name varchar(128) default '' null comment '任务类名',
	task_service_method varchar(128) default '' null comment '任务方法',
	task_desc varchar(128) charset utf16 null comment '任务描述',
	task_cron varchar(128) null comment '任务表达式',
	task_status varchar(2) default '' null comment '任务状态',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) collate utf8_bin default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) collate utf8_bin default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) collate utf8_bin default '00' null comment '删除标识(00-正常 01-删除)'
)
comment '定时任务主表' charset=utf8;

create table if not exists scheduled_task_form
(
	id int auto_increment
		primary key,
	task_code varchar(128) default '' null comment '任务编号',
	input_name varchar(128) default '' null comment '任务表单名称',
	input_label varchar(128) default '' null comment '任务表单label',
	input_memo varchar(128) default '' null comment '任务表单备注',
	input_not_null varchar(2) default '' null comment '任务表单是否必填',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) collate utf8_bin default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) collate utf8_bin default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) collate utf8_bin default '00' null comment '删除标识(00-正常 01-删除)'
)
comment '定时任务主表' charset=utf8;

create table if not exists user_basic_face
(
	ID int unsigned auto_increment
		primary key,
	USER_CODE varchar(64) default '' null comment '用户编号',
	USER_LABEL varchar(64) default '' null comment '用户中文名',
	USER_NAME varchar(128) default '' null comment '用户名称',
	LOGIN_PASSWORD varchar(255) default '' null comment '账号登录密码',
	SECURITY_PASSWORD varchar(255) default '' null comment '安全密码',
	IS_LOCK varchar(2) default '00' null comment '是否锁定(00-未锁定 01-锁定)',
	IS_REMEMBER varchar(2) default '00' null comment '是否记住登录状态(00-记录 01-不记录)',
	IS_NEED_RESET_PASSWORD varchar(2) default '01' null comment '是否需要重置密码(00-不需要 01-需要)',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
comment '用户基础信息表' collate=utf8_bin;

create table if not exists user_login_log
(
	ID int auto_increment
		primary key,
	USER_NAME varchar(64) null comment '登录用户编号',
	IP_ADDRESS varchar(32) null comment '登录地址',
	SYSTEM_TYPE varchar(64) null comment '系统类型',
	BROWSER_TYPE varchar(64) charset utf8mb4 null comment '浏览器类型',
	LOGIN_STATUS varchar(2) null comment '登录状态',
	REMARK varchar(255) charset utf8mb4 null comment '备注',
	LOGIN_TIME datetime default CURRENT_TIMESTAMP null comment '登录时间',
	LOGIN_OUT_TIME datetime default CURRENT_TIMESTAMP null comment '退出时间'
)
collate=utf8_bin;

create table if not exists user_organization_relation
(
	ID int(11) unsigned auto_increment
		primary key,
	ORGANIZATION_CODE varchar(64) default '' not null comment '编号',
	USER_CODE varchar(64) default '' null comment '用户编号',
	POST_NAME varchar(255) null comment '岗位名称',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
collate=utf8_bin;

create table if not exists user_role_relation
(
	ID int(11) unsigned auto_increment
		primary key,
	ROLE_CODE varchar(64) default '' null comment '角色编号',
	USER_CODE varchar(64) default '' null comment '用户编号',
	UPDATE_TIME datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	UPDATE_USER varchar(64) default 'sys' null comment '更新用户编号',
	CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '创建时间',
	CREATE_USER varchar(64) default 'sys' null comment '创建用户编号',
	DELETE_FLAG varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
comment '用户角色关联表' collate=utf8_bin;

create table if not exists wordbook
(
	id int auto_increment
		primary key,
	wordbook_code varchar(64) default '' null comment '字典编码',
	wordbook_name varchar(64) null comment '字典名称',
	wordbook_status varchar(64) null comment '字典状态(00-正常 01-禁用)',
	memo varchar(255) null comment '备注',
	update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	update_user varchar(64) default 'sys' null comment '更新用户编号',
	create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
	create_user varchar(64) default 'sys' null comment '创建用户编号',
	delete_flag varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
collate=utf8mb4_unicode_ci;

create table if not exists wordbook_attribute
(
	id int auto_increment
		primary key,
	wordbook_code varchar(64) null comment '属性编码',
	attribute_value varchar(255) null comment '属性值',
	attribute_name varchar(255) null comment '属性名称',
	attribute_status varchar(2) null comment '属性状态(00-正常 01-禁用)',
	attribute_memo varchar(255) null comment '属性备注',
	update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	update_user varchar(64) default 'sys' null comment '更新用户编号',
	create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
	create_user varchar(64) default 'sys' null comment '创建用户编号',
	delete_flag varchar(2) default '00' null comment '删除标识(00-正常 01-删除)'
)
collate=utf8mb4_unicode_ci;

