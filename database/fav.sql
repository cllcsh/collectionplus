/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/11/4 21:51:47                           */
/*==============================================================*/


drop table if exists tb_task_points_config;

drop table if exists tb_apply_define;

drop table if exists tb_apply_define_detail;

drop table if exists tb_apply_record;

drop table if exists tb_apply_record_line;

drop table if exists tb_approver;

drop table if exists tb_auction;

drop table if exists tb_auction_collection_bid;

drop table if exists tb_auction_dynamic_deal;

drop table if exists tb_auction_dynamic_images;

drop table if exists tb_auction_dynamic_live;

drop table if exists tb_auction_dynamic_preview;

drop table if exists tb_auction_dynamics;

drop table if exists tb_auction_dynamics_type;

drop table if exists tb_city;

drop table if exists tb_collection;

drop table if exists tb_collection_category;

drop table if exists tb_collection_images;

drop table if exists tb_collection_lable;

drop table if exists tb_collection_period;

drop table if exists tb_county;

drop table if exists tb_curiosity_shop;

drop table if exists tb_daily_polemic;

drop table if exists tb_dynamic;

drop table if exists tb_dynamic_images;

drop table if exists tb_dynamic_like;

drop table if exists tb_famous_home;

drop table if exists tb_favorites;

drop table if exists tb_home;

drop table if exists tb_messages;

drop table if exists tb_province;

drop table if exists tb_sms;

drop table if exists tb_special;

drop table if exists tb_today_appreciation;

drop table if exists tb_fav_user;

drop table if exists tb_user_fans;

drop table if exists tb_user_interes_category;

drop table if exists tb_user_points_record;

drop table if exists tb_user_title;

drop table if exists tb_daily_polemic_vote;

drop table if exists tb_heat;

drop table if exists tb_enum;

drop table if exists tb_comments;

drop table if exists tb_comments_like;

drop table if exists tb_comment_top;

drop table if exists tb_dynamic_comments;

/*==============================================================*/
/* Table: tb_task_points_config                                    */
/*==============================================================*/
create table tb_task_points_config
(
   id                   decimal(8,0) not null,
   task_name            varchar(64) comment '任务类型：发藏品：release_collection，发个人动态：release_dynamic，获赞：get_like，更换头像：change_avatar',
   points               int(11) default 0 comment '积分',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_task_points_config comment '任务积分设置表';

/*==============================================================*/
/* Table: tb_apply_define                                       */
/*==============================================================*/
create table tb_apply_define
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '审批流名称',
   apply_type           varchar(40) comment '申请类型',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_define comment '审批流定义表';

/*==============================================================*/
/* Table: tb_apply_define_detail                                */
/*==============================================================*/
create table tb_apply_define_detail
(
   id                   decimal(8,0) not null,
   apply_define_id      decimal(8,0) not null comment '审批流定义id',
   approver_id          decimal(8,0) not null comment '审批人id',
   display_order        int(11) not null comment '序号',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_define_detail comment '审批流定义详情表';

/*==============================================================*/
/* Table: tb_apply_record                                       */
/*==============================================================*/
create table tb_apply_record
(
   id                   decimal(8,0) not null,
   collection_id        decimal(8,0) not null comment '申请拍卖的藏品id',
   applier_id           decimal(8,0) not null comment '申请人Id',
   status               varchar(40) comment '申请状态，等审核：collection_status_wait_apply，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid',
   remark               varchar(256) comment '审核备注',
   apply_time           timestamp default '0000-00-00 00:00:00' comment '申请时间',
   apply_type           varchar(40) comment '申请类型',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_record comment '申请记录表';

/*==============================================================*/
/* Table: tb_apply_record_line                                  */
/*==============================================================*/
create table tb_apply_record_line
(
   id                   decimal(8,0) not null,
   apply_record_id      decimal(8,0) not null comment '申请记录id',
   applier_id           decimal(8,0) not null comment '申请人Id',
   approver_id          decimal(8,0) not null comment '审批人Id',
   is_pass              varchar(1) comment '是否通过 Y:通过，N：不通过',
   apply_time           timestamp default '0000-00-00 00:00:00' comment '审批时间',
   feed_back            varchar(40) comment '审批意见',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_record_line comment '申请记录明细表';

/*==============================================================*/
/* Table: tb_approver                                           */
/*==============================================================*/
create table tb_approver
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '审批人名称',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_approver comment '审批人表';

/*==============================================================*/
/* Table: tb_auction                                            */
/*==============================================================*/
create table tb_auction
(
   id                   decimal(8,0) not null,
   name                 VARCHAR(40) not null comment '拍卖行名字',
   icon                 varchar(128) not null DEFAULT '/upload/file/sclogo.png' comment '拍卖行图标',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '数据采集标志',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction comment '拍卖行表';

/*==============================================================*/
/* Table: tb_auction_collection_bid                             */
/*==============================================================*/
create table tb_auction_collection_bid
(
   id                   decimal(8,0) not null,
   collection_id        decimal(8,0) not null comment '藏品id',
   userid               decimal(8,0) not null default 0 comment '用户id',
   username             varchar(40) not null comment '用户名',
   sex_nick             varchar(10) not null  default 'man' comment '性别昵称',
   price                double not null default 0 comment '竞价价格',
   price_unit           varchar(15) not null comment '竞价价格单位',
   bid_date             timestamp default '0000-00-00 00:00:00' comment '竞价时间',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_collection_bid comment '拍卖行藏品竞价表';

/*==============================================================*/
/* Table: tb_auction_dynamic_deal                               */
/*==============================================================*/
create table tb_auction_dynamic_deal
(
   id                   decimal(8,0) not null,
   auction_dynamic_id   decimal(8,0) not null comment '拍卖行动态id',
   collection_id        decimal(8,0) not null comment '藏品id',
   display_order        int default 0 comment '显示顺序',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_deal comment '拍卖动态成交表';

/*==============================================================*/
/* Table: tb_auction_dynamic_images                             */
/*==============================================================*/
create table tb_auction_dynamic_images
(
   id                   decimal(8,0) not null comment 'id',
   auction_dynamic_id   decimal(8,0) not null comment '动态id',
   content              VARCHAR(2048) default NULL comment '内容',
   images               VARCHAR(2048) default NULL comment '上传图片',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_images comment '拍卖行动态拍品征集表';

/*==============================================================*/
/* Table: tb_auction_dynamic_live                               */
/*==============================================================*/
create table tb_auction_dynamic_live
(
   id                   decimal(8,0) not null,
   auction_dynamic_id   decimal(8,0) not null comment '拍卖行动态id',
   collection_id        decimal(8,0) not null comment '藏品id',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_live comment '拍卖行藏品动态直播表';

/*==============================================================*/
/* Table: tb_auction_dynamic_preview                            */
/*==============================================================*/
create table tb_auction_dynamic_preview
(
   id                   decimal(8,0) not null,
   auction_dynamic_id   decimal(8,0) not null comment '拍卖行动态id',
   collection_id        decimal(8,0) not null comment '藏品id',
   display_order        int default 0 comment '显示顺序',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_preview comment '拍卖动态预展表';

/*==============================================================*/
/* Table: tb_auction_dynamics                                   */
/*==============================================================*/
create table tb_auction_dynamics
(
   id                   decimal(8,0) not null,
   auction_id           decimal(8,0) not null comment '拍卖行标识',
   title                varchar(128) comment '标题',
   auction_dynamic_type_id decimal(8,0) not null comment '动态类型',
   source_id            decimal(8,0) default 0 comment '动态源id',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamics comment '拍卖行动态';

/*==============================================================*/
/* Table: tb_auction_dynamics_type                              */
/*==============================================================*/
create table tb_auction_dynamics_type
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '类别',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamics_type comment '拍卖行动态类别表';

/*==============================================================*/
/* Table: tb_city                                               */
/*==============================================================*/
create table tb_city
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '名称',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_city comment '城市表';

/*==============================================================*/
/* Table: tb_collection                                         */
/*==============================================================*/
create table tb_collection
(
   id                   decimal(8,0) not null comment 'Id',
   title                varchar(128) not null comment '标题',
   category_id          decimal(8,0) not null comment '藏品类别',
   collection_period_id decimal(8,0) not null comment '所属时期ID',
   introduction         text not null comment '藏品简介',
   is_send_racket       varchar(1) default 'Y' comment '是否愿意送拍（Y:是,N:否） ',
   is_sold              varchar(1) default 'Y' comment '是否愿意出售（Y:是,N:否）',
   is_identify          varchar(1) default 'N' comment '是否鉴定(Y:已鉴定(,N:没鉴定)',
   label_id             decimal(8,0) not null comment '标签id',
   icon_img             varchar(255) default null comment '图标',
   heat                 int default 0 comment '热度',
   identify_result      varchar(40) comment '鉴定结果',
   status               varchar(40) default 'collection_status_show' comment '状态，展示：collection_status_show，送拍：collection_status_send_racket，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid',
   auction_id           decimal(8,0) not null comment '拍卖行id',
   appraisal            varchar(40) comment '估价',
   appraisal_unit       varchar(40) comment '估价单位',
   appraisal_time       timestamp default '0000-00-00 00:00:00' comment '估价时间',
   appraisal_user_id    decimal(8,0) not null comment '估价人id',
   appraisal_user_name  varchar(40) comment '估计人',
   transaction_price    varchar(40) comment '成交价',
   transaction_price_unit	varchar(40) comment '成交价单位',
   transaction_price_time timestamp default '0000-00-00 00:00:00' comment '成交时间',
   transaction_user_id  decimal(8,0) not null comment '成交人id',
   transaction_user_name varchar(40) comment '成交人',
   transaction_desc      varchar(1024) comment '成交说明',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '数据采集标志',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection comment '藏品表';

/*==============================================================*/
/* Index: idx_collection_category_id                            */
/*==============================================================*/
create index idx_collection_category_id on tb_collection
(
   category_id
);

/*==============================================================*/
/* Table: tb_collection_category                                */
/*==============================================================*/
create table tb_collection_category
(
   id                   decimal(8,0) not null comment '分类Id',
   category_name        varchar(40) not null comment '分类名称',
   display_order        int default 0 comment '显示顺序',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null,
   col_flag             int(2) DEFAULT '0' COMMENT '数据采集标志',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_category comment '藏品类别表';

/*==============================================================*/
/* Table: tb_collection_images                                  */
/*==============================================================*/
create table tb_collection_images
(
   id                   decimal(8,0) not null comment 'id',
   collection_id        decimal(8,0) not null comment '藏品Id',
   image_url            varchar(256) not null comment '上传的图片URL',
   display_order        int default 0 comment '显示顺序',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '数据采集标志',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_images comment '藏品图片集表';

/*==============================================================*/
/* Table: tb_collection_lable                                   */
/*==============================================================*/
create table tb_collection_lable
(
   id                   decimal(8,0) not null comment 'Id',
   name                 varchar(40) not null comment '标签名称',
   image_url            varchar(256) default null comment '上传的图片URL',
   display_order        int default 0 comment '显示顺序',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_lable comment '藏品标签表（拍卖品，展品）';

/*==============================================================*/
/* Table: tb_collection_period                                  */
/*==============================================================*/
create table tb_collection_period
(
   id                   decimal(8,0) not null comment 'Id',
   name                 varchar(40) not null comment '时期名称',
   image_url            varchar(256) default null comment '上传的图片URL',
   display_order        int default 0 comment '显示顺序',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '数据采集标志',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_period comment '藏品时期表';

/*==============================================================*/
/* Table: tb_county                                             */
/*==============================================================*/
create table tb_county
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '名称',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_county comment '区县表';

/*==============================================================*/
/* Table: tb_curiosity_shop                                     */
/*==============================================================*/
create table tb_curiosity_shop
(
   id                   decimal(8,0) not null comment 'Id',
   name                 VARCHAR(128) not null comment '名称',
   address              VARCHAR(512) not null comment '地址',
   phone                VARCHAR(100) not null comment '电话',
   introduction					VARCHAR(2048) comment '简介',
   lat                  double not null default 0 comment '纬度',
   longitude            double not null default 0 comment '经度',
   province             VARCHAR(20) comment '省份',
   city                 VARCHAR(20) comment '城市',
   county               VARCHAR(20) comment '区县',
   icon                 VARCHAR(128) comment '图标',
   images               VARCHAR(2048) default NULL comment '上传图片，多个逗号隔开',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_curiosity_shop comment '古玩店';

/*==============================================================*/
/* Table: tb_daily_polemic                                      */
/*==============================================================*/
create table tb_daily_polemic
(
   id                   decimal(8,0) not null comment 'id',
   title                varchar(128) comment '标题',
   content              varchar(1028) comment '内容',
   images               varchar(1028) comment '藏品图片，多个用,分隔',
   a_viewpoint          varchar(512) comment '甲方观点',
   b_viewpoint          varchar(512) comment '乙方观点',
   support_a_viewpoint  int default 0 comment '支持甲方观点票数',
   support_b_viewpoint  int default 0 comment '支持乙方观点票数',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_daily_polemic comment '天天论战';

/*==============================================================*/
/* Table: tb_dynamic                                            */
/*==============================================================*/
create table tb_dynamic
(
   id                   decimal(8,0) not null comment 'Id',
   release_by           decimal(8,0) not null comment '发布人(用户Id)',
   dynamic_content      VARCHAR(1024) default '' comment '动态内容',
   release_time         datetime not null comment '发布动态时间',
   is_shield            VARCHAR(1) default 'N' comment '是否屏蔽(Y:是，N:否) 废弃', 
   comment_number       int default 0 comment '评论数',
   like_number          int default 0 comment '点赞数',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_dynamic comment '动态表';

/*==============================================================*/
/* Table: tb_dynamic_images                                     */
/*==============================================================*/
create table tb_dynamic_images
(
   id                   decimal(8,0) not null comment 'id',
   dynamic_id           decimal(8,0) not null comment '动态id',
   image                VARCHAR(128) default '' comment '上传图片地址',
   display_order        int default 0 comment '显示顺序',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_dynamic_images comment '动态图片地址表';

/*==============================================================*/
/* Table: tb_dynamic_like                                       */
/*==============================================================*/
create table tb_dynamic_like 
(
   id                   decimal(8,0) not null comment 'id',
   dynamic_id           decimal(8,0) not null comment '动态id',
   friend_id            decimal(8,0) not null comment '好友用户id',
   like_time            datetime not null comment '点赞时间',
   type                 varchar(1) default '0' comment '评论类型 0：赞成 1：反对，已删除',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_dynamic_like comment '动态点赞表';

/*==============================================================*/
/* Table: tb_famous_home                                        */
/*==============================================================*/
create table tb_famous_home
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '名字',
   icon                 varchar(256) comment '图标',
   introduction         varchar(2048) comment '个人简介',
   status               varchar(1) comment 'famous_home_status_type 是否入驻 1：已入驻 0：待入驻',
   specialids           varchar(256) comment '专项多个逗号隔开',
   type                 varchar(1) comment 'famous_home_type_type 0：专家组 1：收藏大咖',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_famous_home comment '名人堂';

/*==============================================================*/
/* Table: tb_favorites                                          */
/*==============================================================*/
create table tb_favorites
(
   id                   decimal(8,0) not null comment 'id',
   user_id              decimal(8,0) not null comment '收藏人id',
   collection_id        decimal(8,0) not null comment '藏品id',
   favorite_time        datetime not null comment '收藏时间',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
alter table tb_favorites comment '收藏夹表';

/*==============================================================*/
/* Table: tb_home                                               */
/*==============================================================*/
create table tb_home
(
   id                   decimal(8,0) not null,
   ad_images1           varchar(128) comment '广告位图片',
   ad_images2           varchar(128) comment '广告位图片',
   ad_images3           varchar(128) comment '广告位图片',
   ad_images4           varchar(128) comment '广告位图片',
   ad_images5           varchar(128) comment '广告位图片',
   ad_images6           varchar(128) comment '广告位图片',
   ad_images7           varchar(128) comment '广告位图片',
   ad_images8           varchar(128) comment '广告位图片',
   ad_images9           varchar(128) comment '广告位图片',
   recommend_collection_show_num int default 4 comment '推荐藏品展示个数',
   top_collectors_show_num int default 4 comment '热门藏家展示个数',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tb_messages                                           */
/*==============================================================*/
create table tb_messages
(
   id                   decimal(8,0) not null comment 'id',
   sender               decimal(8,0) not null comment '发送 人（发送人的用户id）',
   receiver             decimal(8,0) not null comment '接收人（接收人的用户id）',
   send_time            datetime not null comment '发送时间',
   content              VARCHAR(512) default '' comment '消息内容',
   is_read              VARCHAR(1) default 'N' comment '是否已读，Y:已读，N：未读',
   is_delete            VARCHAR(1) default 'N' comment '是否删除，Y:已删除，N：未删除',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_messages comment '消息';

/*==============================================================*/
/* Table: tb_province                                           */
/*==============================================================*/
create table tb_province
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '名称',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_province comment '省份表';

/*==============================================================*/
/* Table: tb_sms                                                */
/*==============================================================*/
create table tb_sms
(
   id                   decimal(8,0) not null comment 'Id',
   phone                varchar(40) not null comment '手机号',
   content              varchar(2000) not null comment '短信内容',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_sms comment '短信表';

/*==============================================================*/
/* Table: tb_special                                            */
/*==============================================================*/
create table tb_special
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '名称',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_special comment '专项';

/*==============================================================*/
/* Table: tb_today_appreciation                                 */
/*==============================================================*/
create table tb_today_appreciation
(
   id                   decimal(8,0) not null comment 'Id',
   title                VARCHAR(128) not null comment '标题',
   content              VARCHAR(1024) not null comment '内容',
   release_time         datetime not null comment '发布时间',
   image                VARCHAR(128) default NULL comment '上传图片',
   display_order        int(11) DEFAULT '0' COMMENT '显示顺序',
   is_show 						  varchar(1) DEFAULT 'Y' COMMENT '是否显示（Y:是,N:否）',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_today_appreciation comment '今日鉴赏';

/*==============================================================*/
/* Table: tb_fav_user                                               */
/*==============================================================*/
create table tb_fav_user
(
   id                   decimal(8,0) not null comment 'id',
   user_name            VARCHAR(40) not null default ' ' comment '名称',
   account              varchar(40) not null comment '账号(手机号)',
   phone                varchar(40) comment '手机号',
   heat                 int default 0 comment '热度',
   password             VARCHAR(128) binary not null comment '密码，加密存储',
   signature            varchar(128) comment '个性签名',
   user_level           varchar(20) default '1' comment '用户等级',
   user_title           varchar(60) comment '用户称号',
   experience           int default 0 comment '经验值',
   avatar               varchar(128) comment '个人头像',
   upate_avatar_time    datetime default NULL comment '更换头像时间',
   description          VARCHAR(256) comment '描述',
   user_points          int default 0 comment '用户当前积分',
   user_allPoints       int default 0 comment '用户累计积分',
   personal_msg_set     varchar(30) DEFAULT 'received_msg_all' COMMENT '个人私信设置  全部接收:received_msg_all  只接收我关注的用户 received_msg_attention 拒绝所有私信 reject_msg_all',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表';

/*==============================================================*/
/* Index: Ind_User_Account                                      */
/*==============================================================*/
create unique index Ind_User_Account on tb_fav_user
(
   account
);

/*==============================================================*/
/* Index: Ind_User_Name                                         */
/*==============================================================*/
create index Ind_User_Name on tb_fav_user
(
   user_name
);

/*==============================================================*/
/* Table: tb_user_fans                                          */
/*==============================================================*/
create table tb_user_fans
(
   id                   decimal(8,0) not null comment 'Id',
   user_id              decimal(8,0) not null comment '关注人',
   fan_id               decimal(8,0) not null comment '粉丝',
   concern_time         datetime not null comment '关注时间',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_fans comment '粉丝表';

/*==============================================================*/
/* Index: idx_fans_user_id                                      */
/*==============================================================*/
create index idx_fans_user_id on tb_user_fans
(
   user_id
);

/*==============================================================*/
/* Index: idx_fans_fan_id                                       */
/*==============================================================*/
create index idx_fans_fan_id on tb_user_fans
(
   fan_id
);

/*==============================================================*/
/* Table: tb_user_interes_category                              */
/*==============================================================*/
create table tb_user_interes_category
(
   id                   decimal(8,0) not null comment 'Id',
   user_id              decimal(8,0) not null comment '用户id',
   category_id          decimal(8,0) not null default 0 comment '藏品类别id',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id, user_id, category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_interes_category comment '用户兴趣收藏类别表';

/*==============================================================*/
/* Table: tb_user_points_record                                 */
/*==============================================================*/
create table tb_user_points_record
(
   id                   decimal(8,0) not null,
   userid               decimal(8,0) comment '用户id',
   points               int default 0 comment '积分',
   comment              varchar(100) comment '积分获取描述',
   taskid               decimal(8,0) comment '每日任务id',
   day                  varchar(20) comment '获取日期yyyyMMdd',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_points_record comment '用户积分记录表';

/*==============================================================*/
/* Table: tb_user_title                                         */
/*==============================================================*/
create table tb_user_title
(
   id                   decimal(8,0) not null,
   name                 varchar(100) not null comment '称号',
   img_path             varchar(100) not null comment '图片地址',
   display_order        int(11) default 0 comment '序号',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_title comment '用户称号表';

CREATE TABLE `tb_daily_polemic_vote` (
  `id` decimal(8,0) NOT NULL,
  `daily_polemic_id` decimal(8,0) NOT NULL COMMENT '天天论战id',
  `user_id` decimal(8,0) NOT NULL COMMENT '投票人',
  `type` varchar(1) DEFAULT '0' COMMENT '1:反对;0:赞成',
	`day` varchar(20) DEFAULT NULL COMMENT '投票日期 yyyy-MM-dd',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_daily_polemic_vote comment '天天论战投票表';

create table tb_heat
(
   id                   decimal(8,0) not null comment 'id',
   name                 varchar(20) comment '类型名称',
   value                int(5) default 0 comment '系数',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_heat comment '热度表';


create table tb_enum
(
   id                   decimal(8,0) not null comment 'id',
   enum_type            varchar(128) NOT NULL comment '类型',
   enum_desc            varchar(128) DEFAULT NULL comment '类型描述',
   enum_code            varchar(128) NOT NULL comment 'code',
   enum_name            varchar(128) NOT NULL comment '名称',
   display_order        int(11) default 0 comment '序号',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_enum comment '枚举表';

create table tb_comment
(
   id                   decimal(8,0) not null comment 'id',
   source_id            decimal(8,0) not null comment '评论来源id',
   source_type	        varchar(1) default '0' comment '评论来源类型 0：藏品 1：动态',
   friend_id            decimal(8,0) not null comment '好友用户id',
   comment_content      VARCHAR(512) not null comment '评论内容',
   comment_time         datetime not null comment '评论时间',
   point                int default 0 comment '评分',
   type                 varchar(1) default '0' comment '评论类型 0：评论 1：回复',
   reply_id             decimal(8,0)  default 0 comment '回复对应的评论id',
   top_size	            int default 0 comment '顶的数量',
   like_size            int default 0 comment '赞同的数量',
   oppose_size          int default 0 comment '反对的数量',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_comment comment '评论表';

create table tb_comment_like
(
   id                   decimal(8,0) not null comment 'id',
   source_id            decimal(8,0) not null comment '评论来源id',
   source_type	        varchar(1) default '0' comment '评论来源类型 0：藏品 1：动态',
   comment_id          decimal(8,0) not null comment '评论id',
   friend_id            decimal(8,0) not null comment '好友用户id',
   type                 varchar(1) default '0' comment '评论类型 0：赞成 1：反对',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_comment_like comment '评论赞同反对表';

create table tb_comment_top
(
   id                   decimal(8,0) not null comment 'id',
   source_id            decimal(8,0) not null comment '评论来源id',
   source_type	        varchar(1) default '0' comment '评论来源类型 0：藏品 1：动态',
   comment_id						decimal(8,0) not null comment '评论id',
   friend_id            decimal(8,0) not null comment '好友用户id',
   top_time             datetime not null comment '顶时间',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_comment_top comment '评论顶表';

create table tb_dynamic_comments
(
   id                   decimal(8,0) not null comment 'id',
   dynamic_id           decimal(8,0) not null comment '动态id',
   friend_id            decimal(8,0) not null comment '好友用户id',
   comment_content      VARCHAR(512) not null comment '评论内容',
   comment_time         datetime not null comment '评论时间',
   type                 varchar(1) default '0' comment '评论类型 0：评论 1：回复',
   top_size	            int default 0 comment '顶的数量',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table tb_dynamic_comments comment '动态评论表';


create table tb_fav_user_set
(
   id                   decimal(8,0) not null comment 'id',
   user_id              decimal(8,0) not null comment '用户id',
   friend_id            decimal(8,0) not null comment '好友用户id',
   block_msg            varchar(1) DEFAULT '0' COMMENT '是否屏蔽私信  1:屏蔽;0:开放',
   block_dynamic        varchar(1) DEFAULT '0' COMMENT '是否屏蔽话题  1:屏蔽;0:开放',
   block_reply          varchar(1) DEFAULT '0' COMMENT '是否屏蔽回复  1:屏蔽;0:开放',
   block_comment        varchar(1) DEFAULT '0' COMMENT '是否屏蔽评论  1:屏蔽;0:开放',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_fav_user_set comment '用户设置表';

CREATE TABLE `tb_user_blacklist` (
  `id` decimal(8,0) NOT NULL COMMENT 'id',
  `user_id` decimal(8,0) NOT NULL COMMENT '用户ID',
  `blacklist_user_id` decimal(8,0) NOT NULL COMMENT '黑名单用户Id',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) NOT NULL default 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户黑名单表';
