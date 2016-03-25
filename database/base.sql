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
  `login_name` varchar(20) NOT NULL DEFAULT '' COMMENT '�ֻ���',
  `password` varchar(64) NOT NULL,
  `former_password` varchar(64) DEFAULT NULL,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '��ʵ��������',
  `id_card` varchar(255) DEFAULT NULL,
  `e_mail` varchar(64) DEFAULT NULL COMMENT '��������',
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL COMMENT '��',
  `area` varchar(50) DEFAULT NULL COMMENT '��',
  `address` varchar(512) DEFAULT NULL COMMENT '��ϸ��ַ',
  `company_name` varchar(64) DEFAULT NULL COMMENT '��˾����',
  `duty` varchar(64) DEFAULT NULL COMMENT 'ְ������',
  `reg_number` varchar(255) DEFAULT NULL,
  `idcardb_picPath` varchar(255) DEFAULT NULL COMMENT '���֤����',
  `idcardf_picPath` varchar(255) DEFAULT NULL COMMENT '���֤����',
  `business_picPath` varchar(512) DEFAULT NULL COMMENT 'Ӫҵִ����Ƭ',
  `brand` varchar(255) DEFAULT NULL COMMENT 'Ʒ��',
  `vip_pass` varchar(64) DEFAULT NULL COMMENT 'VIP��ѯ����',
  `approve_flag` varchar(3) DEFAULT '1',
  `reason` varchar(3000) DEFAULT NULL,
  `user_type` varchar(4) DEFAULT '0' COMMENT '�û�����',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `card_no` varchar(100) DEFAULT NULL COMMENT '���п���',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '��������',
  `account_holder` varchar(100) DEFAULT NULL COMMENT '������',
  `login_count` int(11) DEFAULT '0' COMMENT '��¼����',
  `manager` varchar(100) DEFAULT NULL COMMENT 'ר���ܾ���',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user comment 'ϵͳ�û���';

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

alter table tb_user_role comment '�û���ɫ��';

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
  `user_type` varchar(4) NOT NULL COMMENT '1��ƽ̨����Ա��2�����й���Ա��3�������û���4����ͨ�û�',
  `url` varchar(64) NOT NULL,
  `front_func` decimal(8,0) DEFAULT NULL,
  `binding` varchar(4) DEFAULT NULL COMMENT '1���ް󶨣�2���а󶨣�3������',
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
  `login_result` varchar(4) NOT NULL COMMENT '1���ɹ���2���û��������ڣ�3 ���������4����֤����� ',
  `dept_id` decimal(8,0) NOT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `online_time` varchar(100) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ts_navigation` (
  `id` decimal(8,0) NOT NULL,
  `func_id` decimal(8,0) DEFAULT NULL COMMENT '���ֶ�Ϊnullʱ���ĵ���ΪĿ¼',
  `name` varchar(64) NOT NULL,
  `view_order` int(4) DEFAULT NULL COMMENT '���ݴ��ֶ�order by����',
  `upper_id` decimal(8,0) DEFAULT NULL COMMENT '����ϼ������ı�ţ������ֶ�Ϊnullʱ���õ���Ϊ������',
  `target` varchar(64) NOT NULL COMMENT '_blank��_self��_parent��_top��Ŀ��frame',
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
  `share_flag` varchar(4) DEFAULT '0' COMMENT '1:����;0:������',
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
  `name` varchar(64) DEFAULT NULL COMMENT '�ļ���',
  `file_name` varchar(64) DEFAULT NULL COMMENT '�ļ���',
  `file_path` varchar(512) DEFAULT NULL COMMENT '�ļ�·��',
  `file_size` int(11) DEFAULT NULL COMMENT '�ļ���С',
  `relevance_id` decimal(8,0) DEFAULT '0' NOT NULL COMMENT '����id',
  `type` varchar(10) DEFAULT NULL COMMENT '����',
  `dept_id` decimal(8,0) NOT NULL,
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
