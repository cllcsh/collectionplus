drop table `tb_brand`;
CREATE TABLE `tb_brand` (
  `id` decimal(8,0) NOT NULL,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `pic_path` varchar(255) NOT NULL  COMMENT '图标地址',
  `pinyin` varchar(200) NOT NULL COMMENT '拼音',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tb_version`;
CREATE TABLE `tb_version` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tb_series`;
CREATE TABLE `tb_series` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `pic_path` varchar(255) NOT NULL  COMMENT '图标地址',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tb_models`;
CREATE TABLE `tb_models` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID', 
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tb_modelyear`;
CREATE TABLE `tb_modelyear` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID',  
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID', 
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `efficiency` varchar(255) NOT NULL COMMENT '功率',
  `driving` varchar(255) NOT NULL COMMENT '驱动方式',
  `fuel` varchar(255) NOT NULL COMMENT '油耗',
  `seats` varchar(255) NOT NULL COMMENT '座位数',
  `price` varchar(255) NOT NULL COMMENT '价格',
  `pic_path1` varchar(255) NOT NULL  COMMENT '图标地址1',
  `pic_path2` varchar(255) NOT NULL  COMMENT '图标地址2',
  `pic_path3` varchar(255) NOT NULL  COMMENT '图标地址3',
  `pic_path4` varchar(255) NOT NULL  COMMENT '图标地址4',
  `pic_path5` varchar(255) NOT NULL  COMMENT '图标地址5',
  `pic_path6` varchar(255) NOT NULL  COMMENT '图标地址6',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tb_outercolor`;
CREATE TABLE `tb_outercolor` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID',  
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID', 
  `modelyear_id` decimal(8,0) NOT NULL COMMENT '年款ID',  
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tb_innercolor`;
CREATE TABLE `tb_innercolor` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID',  
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID', 
  `modelyear_id` decimal(8,0) NOT NULL COMMENT '年款ID',  
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table `tb_engines`;
CREATE TABLE `tb_engines` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',  
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID', 
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



 
drop table `tb_standard`;
CREATE TABLE `tb_standard` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',  
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID', 
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID', 
  `modelyear_id` decimal(8,0) NOT NULL COMMENT '年款ID',   
  `name` varchar(2000) NOT NULL DEFAULT '' COMMENT '名称',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table `tb_models_hot`;
CREATE TABLE `tb_models_hot` (
  `id` decimal(8,0) NOT NULL,
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID',
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID', 
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `sorting` decimal(8,0) NOT NULL COMMENT '序号',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table `tb_brand_hot`;
CREATE TABLE `tb_brand_hot` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `sorting` decimal(8,0) NOT NULL COMMENT '序号',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table `tb_brand_search`;
CREATE TABLE `tb_brand_search` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `sorting` decimal(8,0) NOT NULL COMMENT '序号',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table `tb_brand_search`;
CREATE TABLE `tb_brand_search` (
  `id` decimal(8,0) NOT NULL,
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `sorting` decimal(8,0) NOT NULL COMMENT '序号',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table `tb_models_number`;
CREATE TABLE `tb_models_number` (
  `id` decimal(8,0) NOT NULL,
  `num` decimal(8,0) NOT NULL COMMENT '数量',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table `tb_user_action`;
CREATE TABLE `tb_user_action` (
  `id` decimal(8,0) NOT NULL,
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID',
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID', 
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `num` decimal(8,0) NOT NULL COMMENT '次数',
  `optype` decimal(8,0) NOT NULL COMMENT '操作类型',  
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table `tb_car`;
CREATE TABLE `tb_car` (
  `id` decimal(8,0) NOT NULL,
  `title` varchar(50) COMMENT '标题',
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID',
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID', 
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `modelyear_id` decimal(8,0) NOT NULL COMMENT '年款ID',
  `engines_id` decimal(8,0) NOT NULL COMMENT '排量ID',
  `fuel` varchar(50) NOT NULL DEFAULT '' COMMENT '燃油',
  `outercolor_id` decimal(8,0) NOT NULL COMMENT '外饰颜色ID',
  `innercolor_id` decimal(8,0) NOT NULL COMMENT '内饰颜色ID',
  `province` varchar(50) NOT NULL  COMMENT '所在省份',
  `deposit_ratio` decimal(1,0) NOT NULL COMMENT '首款比例',
  `source` decimal(1,0) NOT NULL COMMENT '货源',
  `procedures` decimal(1,0) NOT NULL COMMENT '车辆手续',
  `num` decimal(8,0) NOT NULL COMMENT '出车数量',
  `car_status` decimal(8,0) NOT NULL COMMENT '车源状态',  
  `price` decimal(8,0) NOT NULL COMMENT '价格',
  `remark` varchar(1000) COMMENT '特殊说明',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL COMMENT '卖车人',
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tb_order`;
CREATE TABLE `tb_order` (
  `id` decimal(8,0) NOT NULL,
  `car_id` decimal(8,0) NOT NULL COMMENT '车源ID',
  `order_code` varchar(50) NOT NULL COMMENT '订单编号',
  `num` decimal(8,0) NOT NULL COMMENT '数量', 
  `order_status` decimal(8,0) NOT NULL COMMENT '订单状态',  
  `order_price` decimal(8,0) NOT NULL COMMENT '订单总价',  
  `reason` varchar(1000) COMMENT '取消理由',
  `remark` varchar(1000) COMMENT '详细说明',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




drop table `tb_price_day`;
CREATE TABLE `tb_price_day` (
  `id` decimal(8,0) NOT NULL,
  `models_id` decimal(8,0) NOT NULL COMMENT '车型ID',
  `brand_id` decimal(8,0) NOT NULL COMMENT '品牌ID',
  `version_id` decimal(8,0) NOT NULL COMMENT '版本ID', 
  `series_id` decimal(8,0) NOT NULL COMMENT '系列ID',
  `min_price` decimal(8,0) COMMENT '最低价格', 
  `average_price` decimal(8,0) COMMENT '平均价格',  
  `guide_price` decimal(8,0) COMMENT '指导价格',
  `price_date` varchar(50) COMMENT '详细说明',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table `tb_message`;
CREATE TABLE `tb_message` (
  `id` decimal(8,0) NOT NULL,
  `title` varchar(50) COMMENT '标题',
  `content` varchar(5000) COMMENT '内容',
  `pic_path` varchar(255) NOT NULL  COMMENT '图标地址',
  `message_type` decimal(1,0) NOT NULL COMMENT '消息类型',    
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

















