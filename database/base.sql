drop table if exists tb_user;

drop table if exists tb_user_role;

drop table if exists ts_codebook;

drop table if exists ts_func;

drop table if exists tb_role_func;

drop table if exists ts_login_log;

drop table if exists ts_navigation;

drop table if exists ts_sequence;

drop table if exists tb_role;      

drop table if exists tb_attachment;


CREATE TABLE `tb_user` (
  `id` decimal(8,0) NOT NULL,
  `login_name` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(64) NOT NULL,
  `former_password` varchar(64) DEFAULT NULL,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '真实姓名汉字',
  `id_card` varchar(255) DEFAULT NULL,
  `e_mail` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `area` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(512) DEFAULT NULL COMMENT '详细地址',
  `company_name` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `duty` varchar(64) DEFAULT NULL COMMENT '职务名称',
  `reg_number` varchar(255) DEFAULT NULL,
  `idcardb_picPath` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `idcardf_picPath` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `business_picPath` varchar(512) DEFAULT NULL COMMENT '营业执照照片',
  `brand` varchar(255) DEFAULT NULL COMMENT '品牌',
  `vip_pass` varchar(64) DEFAULT NULL COMMENT 'VIP查询密码',
  `approve_flag` varchar(3) DEFAULT '1',
  `reason` varchar(3000) DEFAULT NULL,
  `user_type` varchar(4) DEFAULT '0' COMMENT '用户类型',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `card_no` varchar(100) DEFAULT NULL COMMENT '银行卡号',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '开户银行',
  `account_holder` varchar(100) DEFAULT NULL COMMENT '开户人',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `manager` varchar(100) DEFAULT NULL COMMENT '专属总经理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user comment '系统用户表';

CREATE TABLE `tb_user_role` (
  `id` decimal(8,0) NOT NULL,
  `role_id` decimal(8,0) NOT NULL,
  `user_id` decimal(8,0) NOT NULL,
  `dept_id` decimal(8,0) NOT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_6` (`user_id`) USING BTREE,
  KEY `FK_Reference_7` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_role comment '用户角色表';

CREATE TABLE `ts_codebook` (
  `code_type` varchar(64) NOT NULL,
  `code_key` varchar(4) NOT NULL,
  `code_value` varchar(160) NOT NULL DEFAULT '',
  `order_no` tinyint(4) DEFAULT NULL,
  `code_desc` varchar(512) DEFAULT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  PRIMARY KEY (`code_key`,`code_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ts_func` (
  `id` decimal(8,0) NOT NULL,
  `name` varchar(64) NOT NULL,
  `user_type` varchar(4) NOT NULL COMMENT '1：平台管理员；2：地市管理员；3：法人用户；4：普通用户',
  `url` varchar(64) NOT NULL,
  `front_func` decimal(8,0) DEFAULT NULL,
  `binding` varchar(4) DEFAULT NULL COMMENT '1：无绑定；2：有绑定；3：被绑定',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_role_func` (
  `id` decimal(8,0) NOT NULL,
  `func_id` decimal(8,0) NOT NULL,
  `role_id` decimal(8,0) NOT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`role_id`) USING BTREE,
  KEY `FK_Reference_9` (`func_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ts_login_log` (
  `id` decimal(8,0) NOT NULL,
  `login_name` varchar(20) NOT NULL,
  `login_date` datetime NOT NULL,
  `login_ip` varchar(15) NOT NULL,
  `login_addr` varchar(512) DEFAULT NULL,
  `login_result` varchar(4) NOT NULL COMMENT '1：成功；2：用户名不存在；3 ：密码错误；4：验证码错误 ',
  `dept_id` decimal(8,0) NOT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `online_time` varchar(100) DEFAULT NULL COMMENT '在线时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ts_navigation` (
  `id` decimal(8,0) NOT NULL,
  `func_id` decimal(8,0) DEFAULT NULL COMMENT '本字段为null时，改导航为目录',
  `name` varchar(64) NOT NULL,
  `view_order` int(4) DEFAULT NULL COMMENT '根据此字段order by排序',
  `upper_id` decimal(8,0) DEFAULT NULL COMMENT '存放上级导航的编号，当本字段为null时，该导航为根导航',
  `target` varchar(64) NOT NULL COMMENT '_blank，_self，_parent，_top，目标frame',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ts_sequence` (
  `name` varchar(64) NOT NULL,
  `current_value` decimal(16,0) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_role` (
  `id` decimal(8,0) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `share_flag` varchar(4) DEFAULT '0' COMMENT '1:共享;0:不共享',
  `dept_id` decimal(8,0) NOT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_attachment` (
  `id` decimal(8,0) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '文件名',
  `file_name` varchar(64) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(512) DEFAULT NULL COMMENT '文件路径',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `relevance_id` decimal(8,0) DEFAULT '0' NOT NULL COMMENT '关联id',
  `type` varchar(10) DEFAULT NULL COMMENT '类型',
  `dept_id` decimal(8,0) NOT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
