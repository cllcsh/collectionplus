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
   task_name            varchar(64) comment '�������ͣ�����Ʒ��release_collection�������˶�̬��release_dynamic�����ޣ�get_like������ͷ��change_avatar',
   points               int(11) default 0 comment '����',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_task_points_config comment '����������ñ�';

/*==============================================================*/
/* Table: tb_apply_define                                       */
/*==============================================================*/
create table tb_apply_define
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '����������',
   apply_type           varchar(40) comment '��������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_define comment '�����������';

/*==============================================================*/
/* Table: tb_apply_define_detail                                */
/*==============================================================*/
create table tb_apply_define_detail
(
   id                   decimal(8,0) not null,
   apply_define_id      decimal(8,0) not null comment '����������id',
   approver_id          decimal(8,0) not null comment '������id',
   display_order        int(11) not null comment '���',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_define_detail comment '���������������';

/*==============================================================*/
/* Table: tb_apply_record                                       */
/*==============================================================*/
create table tb_apply_record
(
   id                   decimal(8,0) not null,
   collection_id        decimal(8,0) not null comment '���������Ĳ�Ʒid',
   applier_id           decimal(8,0) not null comment '������Id',
   status               varchar(40) comment '����״̬������ˣ�collection_status_wait_apply������У�collection_status_applying�����ͨ����collection_status_pass_apply����˲�ͨ����collection_status_fail_apply�����ۣ�collection_status_appraisal�������У�collection_status_auction��������collection_status_solded�����ģ�collection_status_invalid',
   remark               varchar(256) comment '��˱�ע',
   apply_time           timestamp default '0000-00-00 00:00:00' comment '����ʱ��',
   apply_type           varchar(40) comment '��������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) default 0 not null,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_record comment '�����¼��';

/*==============================================================*/
/* Table: tb_apply_record_line                                  */
/*==============================================================*/
create table tb_apply_record_line
(
   id                   decimal(8,0) not null,
   apply_record_id      decimal(8,0) not null comment '�����¼id',
   applier_id           decimal(8,0) not null comment '������Id',
   approver_id          decimal(8,0) not null comment '������Id',
   is_pass              varchar(1) comment '�Ƿ�ͨ�� Y:ͨ����N����ͨ��',
   apply_time           timestamp default '0000-00-00 00:00:00' comment '����ʱ��',
   feed_back            varchar(40) comment '�������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_apply_record_line comment '�����¼��ϸ��';

/*==============================================================*/
/* Table: tb_approver                                           */
/*==============================================================*/
create table tb_approver
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '����������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_approver comment '�����˱�';

/*==============================================================*/
/* Table: tb_auction                                            */
/*==============================================================*/
create table tb_auction
(
   id                   decimal(8,0) not null,
   name                 VARCHAR(40) not null comment '����������',
   icon                 varchar(128) not null DEFAULT '/upload/file/sclogo.png' comment '������ͼ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '���ݲɼ���־',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction comment '�����б�';

/*==============================================================*/
/* Table: tb_auction_collection_bid                             */
/*==============================================================*/
create table tb_auction_collection_bid
(
   id                   decimal(8,0) not null,
   collection_id        decimal(8,0) not null comment '��Ʒid',
   userid               decimal(8,0) not null default 0 comment '�û�id',
   username             varchar(40) not null comment '�û���',
   sex_nick             varchar(10) not null  default 'man' comment '�Ա��ǳ�',
   price                double not null default 0 comment '���ۼ۸�',
   price_unit           varchar(15) not null comment '���ۼ۸�λ',
   bid_date             timestamp default '0000-00-00 00:00:00' comment '����ʱ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_collection_bid comment '�����в�Ʒ���۱�';

/*==============================================================*/
/* Table: tb_auction_dynamic_deal                               */
/*==============================================================*/
create table tb_auction_dynamic_deal
(
   id                   decimal(8,0) not null,
   auction_dynamic_id   decimal(8,0) not null comment '�����ж�̬id',
   collection_id        decimal(8,0) not null comment '��Ʒid',
   display_order        int default 0 comment '��ʾ˳��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_deal comment '������̬�ɽ���';

/*==============================================================*/
/* Table: tb_auction_dynamic_images                             */
/*==============================================================*/
create table tb_auction_dynamic_images
(
   id                   decimal(8,0) not null comment 'id',
   auction_dynamic_id   decimal(8,0) not null comment '��̬id',
   content              VARCHAR(2048) default NULL comment '����',
   images               VARCHAR(2048) default NULL comment '�ϴ�ͼƬ',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_images comment '�����ж�̬��Ʒ������';

/*==============================================================*/
/* Table: tb_auction_dynamic_live                               */
/*==============================================================*/
create table tb_auction_dynamic_live
(
   id                   decimal(8,0) not null,
   auction_dynamic_id   decimal(8,0) not null comment '�����ж�̬id',
   collection_id        decimal(8,0) not null comment '��Ʒid',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_live comment '�����в�Ʒ��ֱ̬����';

/*==============================================================*/
/* Table: tb_auction_dynamic_preview                            */
/*==============================================================*/
create table tb_auction_dynamic_preview
(
   id                   decimal(8,0) not null,
   auction_dynamic_id   decimal(8,0) not null comment '�����ж�̬id',
   collection_id        decimal(8,0) not null comment '��Ʒid',
   display_order        int default 0 comment '��ʾ˳��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamic_preview comment '������̬Ԥչ��';

/*==============================================================*/
/* Table: tb_auction_dynamics                                   */
/*==============================================================*/
create table tb_auction_dynamics
(
   id                   decimal(8,0) not null,
   auction_id           decimal(8,0) not null comment '�����б�ʶ',
   title                varchar(128) comment '����',
   auction_dynamic_type_id decimal(8,0) not null comment '��̬����',
   source_id            decimal(8,0) default 0 comment '��̬Դid',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamics comment '�����ж�̬';

/*==============================================================*/
/* Table: tb_auction_dynamics_type                              */
/*==============================================================*/
create table tb_auction_dynamics_type
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '���',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_auction_dynamics_type comment '�����ж�̬����';

/*==============================================================*/
/* Table: tb_city                                               */
/*==============================================================*/
create table tb_city
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '����',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_city comment '���б�';

/*==============================================================*/
/* Table: tb_collection                                         */
/*==============================================================*/
create table tb_collection
(
   id                   decimal(8,0) not null comment 'Id',
   title                varchar(128) not null comment '����',
   category_id          decimal(8,0) not null comment '��Ʒ���',
   collection_period_id decimal(8,0) not null comment '����ʱ��ID',
   introduction         text not null comment '��Ʒ���',
   is_send_racket       varchar(1) default 'Y' comment '�Ƿ�Ը�����ģ�Y:��,N:�� ',
   is_sold              varchar(1) default 'Y' comment '�Ƿ�Ը����ۣ�Y:��,N:��',
   is_identify          varchar(1) default 'N' comment '�Ƿ����(Y:�Ѽ���(,N:û����)',
   label_id             decimal(8,0) not null comment '��ǩid',
   icon_img             varchar(255) default null comment 'ͼ��',
   heat                 int default 0 comment '�ȶ�',
   identify_result      varchar(40) comment '�������',
   status               varchar(40) default 'collection_status_show' comment '״̬��չʾ��collection_status_show�����ģ�collection_status_send_racket������У�collection_status_applying�����ͨ����collection_status_pass_apply����˲�ͨ����collection_status_fail_apply�����ۣ�collection_status_appraisal�������У�collection_status_auction��������collection_status_solded�����ģ�collection_status_invalid',
   auction_id           decimal(8,0) not null comment '������id',
   appraisal            varchar(40) comment '����',
   appraisal_unit       varchar(40) comment '���۵�λ',
   appraisal_time       timestamp default '0000-00-00 00:00:00' comment '����ʱ��',
   appraisal_user_id    decimal(8,0) not null comment '������id',
   appraisal_user_name  varchar(40) comment '������',
   transaction_price    varchar(40) comment '�ɽ���',
   transaction_price_unit	varchar(40) comment '�ɽ��۵�λ',
   transaction_price_time timestamp default '0000-00-00 00:00:00' comment '�ɽ�ʱ��',
   transaction_user_id  decimal(8,0) not null comment '�ɽ���id',
   transaction_user_name varchar(40) comment '�ɽ���',
   transaction_desc      varchar(1024) comment '�ɽ�˵��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '���ݲɼ���־',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection comment '��Ʒ��';

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
   id                   decimal(8,0) not null comment '����Id',
   category_name        varchar(40) not null comment '��������',
   display_order        int default 0 comment '��ʾ˳��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null,
   col_flag             int(2) DEFAULT '0' COMMENT '���ݲɼ���־',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_category comment '��Ʒ����';

/*==============================================================*/
/* Table: tb_collection_images                                  */
/*==============================================================*/
create table tb_collection_images
(
   id                   decimal(8,0) not null comment 'id',
   collection_id        decimal(8,0) not null comment '��ƷId',
   image_url            varchar(256) not null comment '�ϴ���ͼƬURL',
   display_order        int default 0 comment '��ʾ˳��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '���ݲɼ���־',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_images comment '��ƷͼƬ����';

/*==============================================================*/
/* Table: tb_collection_lable                                   */
/*==============================================================*/
create table tb_collection_lable
(
   id                   decimal(8,0) not null comment 'Id',
   name                 varchar(40) not null comment '��ǩ����',
   image_url            varchar(256) default null comment '�ϴ���ͼƬURL',
   display_order        int default 0 comment '��ʾ˳��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_lable comment '��Ʒ��ǩ������Ʒ��չƷ��';

/*==============================================================*/
/* Table: tb_collection_period                                  */
/*==============================================================*/
create table tb_collection_period
(
   id                   decimal(8,0) not null comment 'Id',
   name                 varchar(40) not null comment 'ʱ������',
   image_url            varchar(256) default null comment '�ϴ���ͼƬURL',
   display_order        int default 0 comment '��ʾ˳��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   col_flag             int(2) DEFAULT '0' COMMENT '���ݲɼ���־',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_collection_period comment '��Ʒʱ�ڱ�';

/*==============================================================*/
/* Table: tb_county                                             */
/*==============================================================*/
create table tb_county
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '����',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_county comment '���ر�';

/*==============================================================*/
/* Table: tb_curiosity_shop                                     */
/*==============================================================*/
create table tb_curiosity_shop
(
   id                   decimal(8,0) not null comment 'Id',
   name                 VARCHAR(128) not null comment '����',
   address              VARCHAR(512) not null comment '��ַ',
   phone                VARCHAR(100) not null comment '�绰',
   introduction					VARCHAR(2048) comment '���',
   lat                  double not null default 0 comment 'γ��',
   longitude            double not null default 0 comment '����',
   province             VARCHAR(20) comment 'ʡ��',
   city                 VARCHAR(20) comment '����',
   county               VARCHAR(20) comment '����',
   icon                 VARCHAR(128) comment 'ͼ��',
   images               VARCHAR(2048) default NULL comment '�ϴ�ͼƬ��������Ÿ���',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_curiosity_shop comment '�����';

/*==============================================================*/
/* Table: tb_daily_polemic                                      */
/*==============================================================*/
create table tb_daily_polemic
(
   id                   decimal(8,0) not null comment 'id',
   title                varchar(128) comment '����',
   content              varchar(1028) comment '����',
   images               varchar(1028) comment '��ƷͼƬ�������,�ָ�',
   a_viewpoint          varchar(512) comment '�׷��۵�',
   b_viewpoint          varchar(512) comment '�ҷ��۵�',
   support_a_viewpoint  int default 0 comment '֧�ּ׷��۵�Ʊ��',
   support_b_viewpoint  int default 0 comment '֧���ҷ��۵�Ʊ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_daily_polemic comment '������ս';

/*==============================================================*/
/* Table: tb_dynamic                                            */
/*==============================================================*/
create table tb_dynamic
(
   id                   decimal(8,0) not null comment 'Id',
   release_by           decimal(8,0) not null comment '������(�û�Id)',
   dynamic_content      VARCHAR(1024) default '' comment '��̬����',
   release_time         datetime not null comment '������̬ʱ��',
   is_shield            VARCHAR(1) default 'N' comment '�Ƿ�����(Y:�ǣ�N:��) ����', 
   comment_number       int default 0 comment '������',
   like_number          int default 0 comment '������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_dynamic comment '��̬��';

/*==============================================================*/
/* Table: tb_dynamic_images                                     */
/*==============================================================*/
create table tb_dynamic_images
(
   id                   decimal(8,0) not null comment 'id',
   dynamic_id           decimal(8,0) not null comment '��̬id',
   image                VARCHAR(128) default '' comment '�ϴ�ͼƬ��ַ',
   display_order        int default 0 comment '��ʾ˳��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_dynamic_images comment '��̬ͼƬ��ַ��';

/*==============================================================*/
/* Table: tb_dynamic_like                                       */
/*==============================================================*/
create table tb_dynamic_like 
(
   id                   decimal(8,0) not null comment 'id',
   dynamic_id           decimal(8,0) not null comment '��̬id',
   friend_id            decimal(8,0) not null comment '�����û�id',
   like_time            datetime not null comment '����ʱ��',
   type                 varchar(1) default '0' comment '�������� 0���޳� 1�����ԣ���ɾ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_dynamic_like comment '��̬���ޱ�';

/*==============================================================*/
/* Table: tb_famous_home                                        */
/*==============================================================*/
create table tb_famous_home
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '����',
   icon                 varchar(256) comment 'ͼ��',
   introduction         varchar(2048) comment '���˼��',
   status               varchar(1) comment 'famous_home_status_type �Ƿ���פ 1������פ 0������פ',
   specialids           varchar(256) comment 'ר�������Ÿ���',
   type                 varchar(1) comment 'famous_home_type_type 0��ר���� 1���ղش�',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_famous_home comment '������';

/*==============================================================*/
/* Table: tb_favorites                                          */
/*==============================================================*/
create table tb_favorites
(
   id                   decimal(8,0) not null comment 'id',
   user_id              decimal(8,0) not null comment '�ղ���id',
   collection_id        decimal(8,0) not null comment '��Ʒid',
   favorite_time        datetime not null comment '�ղ�ʱ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
alter table tb_favorites comment '�ղؼб�';

/*==============================================================*/
/* Table: tb_home                                               */
/*==============================================================*/
create table tb_home
(
   id                   decimal(8,0) not null,
   ad_images1           varchar(128) comment '���λͼƬ',
   ad_images2           varchar(128) comment '���λͼƬ',
   ad_images3           varchar(128) comment '���λͼƬ',
   ad_images4           varchar(128) comment '���λͼƬ',
   ad_images5           varchar(128) comment '���λͼƬ',
   ad_images6           varchar(128) comment '���λͼƬ',
   ad_images7           varchar(128) comment '���λͼƬ',
   ad_images8           varchar(128) comment '���λͼƬ',
   ad_images9           varchar(128) comment '���λͼƬ',
   recommend_collection_show_num int default 4 comment '�Ƽ���Ʒչʾ����',
   top_collectors_show_num int default 4 comment '���Ųؼ�չʾ����',
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
   sender               decimal(8,0) not null comment '���� �ˣ������˵��û�id��',
   receiver             decimal(8,0) not null comment '�����ˣ������˵��û�id��',
   send_time            datetime not null comment '����ʱ��',
   content              VARCHAR(512) default '' comment '��Ϣ����',
   is_read              VARCHAR(1) default 'N' comment '�Ƿ��Ѷ���Y:�Ѷ���N��δ��',
   is_delete            VARCHAR(1) default 'N' comment '�Ƿ�ɾ����Y:��ɾ����N��δɾ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_messages comment '��Ϣ';

/*==============================================================*/
/* Table: tb_province                                           */
/*==============================================================*/
create table tb_province
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '����',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_province comment 'ʡ�ݱ�';

/*==============================================================*/
/* Table: tb_sms                                                */
/*==============================================================*/
create table tb_sms
(
   id                   decimal(8,0) not null comment 'Id',
   phone                varchar(40) not null comment '�ֻ���',
   content              varchar(2000) not null comment '��������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_sms comment '���ű�';

/*==============================================================*/
/* Table: tb_special                                            */
/*==============================================================*/
create table tb_special
(
   id                   decimal(8,0) not null,
   name                 varchar(40) not null comment '����',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_special comment 'ר��';

/*==============================================================*/
/* Table: tb_today_appreciation                                 */
/*==============================================================*/
create table tb_today_appreciation
(
   id                   decimal(8,0) not null comment 'Id',
   title                VARCHAR(128) not null comment '����',
   content              VARCHAR(1024) not null comment '����',
   release_time         datetime not null comment '����ʱ��',
   image                VARCHAR(128) default NULL comment '�ϴ�ͼƬ',
   display_order        int(11) DEFAULT '0' COMMENT '��ʾ˳��',
   is_show 						  varchar(1) DEFAULT 'Y' COMMENT '�Ƿ���ʾ��Y:��,N:��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_today_appreciation comment '���ռ���';

/*==============================================================*/
/* Table: tb_fav_user                                               */
/*==============================================================*/
create table tb_fav_user
(
   id                   decimal(8,0) not null comment 'id',
   user_name            VARCHAR(40) not null default ' ' comment '����',
   account              varchar(40) not null comment '�˺�(�ֻ���)',
   phone                varchar(40) comment '�ֻ���',
   heat                 int default 0 comment '�ȶ�',
   password             VARCHAR(128) binary not null comment '���룬���ܴ洢',
   signature            varchar(128) comment '����ǩ��',
   user_level           varchar(20) default '1' comment '�û��ȼ�',
   user_title           varchar(60) comment '�û��ƺ�',
   experience           int default 0 comment '����ֵ',
   avatar               varchar(128) comment '����ͷ��',
   upate_avatar_time    datetime default NULL comment '����ͷ��ʱ��',
   description          VARCHAR(256) comment '����',
   user_points          int default 0 comment '�û���ǰ����',
   user_allPoints       int default 0 comment '�û��ۼƻ���',
   personal_msg_set     varchar(30) DEFAULT 'received_msg_all' COMMENT '����˽������  ȫ������:received_msg_all  ֻ�����ҹ�ע���û� received_msg_attention �ܾ�����˽�� reject_msg_all',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '�û���';

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
   user_id              decimal(8,0) not null comment '��ע��',
   fan_id               decimal(8,0) not null comment '��˿',
   concern_time         datetime not null comment '��עʱ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_fans comment '��˿��';

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
   user_id              decimal(8,0) not null comment '�û�id',
   category_id          decimal(8,0) not null default 0 comment '��Ʒ���id',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id, user_id, category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_interes_category comment '�û���Ȥ�ղ�����';

/*==============================================================*/
/* Table: tb_user_points_record                                 */
/*==============================================================*/
create table tb_user_points_record
(
   id                   decimal(8,0) not null,
   userid               decimal(8,0) comment '�û�id',
   points               int default 0 comment '����',
   comment              varchar(100) comment '���ֻ�ȡ����',
   taskid               decimal(8,0) comment 'ÿ������id',
   day                  varchar(20) comment '��ȡ����yyyyMMdd',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_points_record comment '�û����ּ�¼��';

/*==============================================================*/
/* Table: tb_user_title                                         */
/*==============================================================*/
create table tb_user_title
(
   id                   decimal(8,0) not null,
   name                 varchar(100) not null comment '�ƺ�',
   img_path             varchar(100) not null comment 'ͼƬ��ַ',
   display_order        int(11) default 0 comment '���',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_user_title comment '�û��ƺű�';

CREATE TABLE `tb_daily_polemic_vote` (
  `id` decimal(8,0) NOT NULL,
  `daily_polemic_id` decimal(8,0) NOT NULL COMMENT '������սid',
  `user_id` decimal(8,0) NOT NULL COMMENT 'ͶƱ��',
  `type` varchar(1) DEFAULT '0' COMMENT '1:����;0:�޳�',
	`day` varchar(20) DEFAULT NULL COMMENT 'ͶƱ���� yyyy-MM-dd',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_daily_polemic_vote comment '������սͶƱ��';

create table tb_heat
(
   id                   decimal(8,0) not null comment 'id',
   name                 varchar(20) comment '��������',
   value                int(5) default 0 comment 'ϵ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_heat comment '�ȶȱ�';


create table tb_enum
(
   id                   decimal(8,0) not null comment 'id',
   enum_type            varchar(128) NOT NULL comment '����',
   enum_desc            varchar(128) DEFAULT NULL comment '��������',
   enum_code            varchar(128) NOT NULL comment 'code',
   enum_name            varchar(128) NOT NULL comment '����',
   display_order        int(11) default 0 comment '���',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_enum comment 'ö�ٱ�';

create table tb_comment
(
   id                   decimal(8,0) not null comment 'id',
   source_id            decimal(8,0) not null comment '������Դid',
   source_type	        varchar(1) default '0' comment '������Դ���� 0����Ʒ 1����̬',
   friend_id            decimal(8,0) not null comment '�����û�id',
   comment_content      VARCHAR(512) not null comment '��������',
   comment_time         datetime not null comment '����ʱ��',
   point                int default 0 comment '����',
   type                 varchar(1) default '0' comment '�������� 0������ 1���ظ�',
   reply_id             decimal(8,0)  default 0 comment '�ظ���Ӧ������id',
   top_size	            int default 0 comment '��������',
   like_size            int default 0 comment '��ͬ������',
   oppose_size          int default 0 comment '���Ե�����',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_comment comment '���۱�';

create table tb_comment_like
(
   id                   decimal(8,0) not null comment 'id',
   source_id            decimal(8,0) not null comment '������Դid',
   source_type	        varchar(1) default '0' comment '������Դ���� 0����Ʒ 1����̬',
   comment_id          decimal(8,0) not null comment '����id',
   friend_id            decimal(8,0) not null comment '�����û�id',
   type                 varchar(1) default '0' comment '�������� 0���޳� 1������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_comment_like comment '������ͬ���Ա�';

create table tb_comment_top
(
   id                   decimal(8,0) not null comment 'id',
   source_id            decimal(8,0) not null comment '������Դid',
   source_type	        varchar(1) default '0' comment '������Դ���� 0����Ʒ 1����̬',
   comment_id						decimal(8,0) not null comment '����id',
   friend_id            decimal(8,0) not null comment '�����û�id',
   top_time             datetime not null comment '��ʱ��',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_comment_top comment '���۶���';

create table tb_dynamic_comments
(
   id                   decimal(8,0) not null comment 'id',
   dynamic_id           decimal(8,0) not null comment '��̬id',
   friend_id            decimal(8,0) not null comment '�����û�id',
   comment_content      VARCHAR(512) not null comment '��������',
   comment_time         datetime not null comment '����ʱ��',
   type                 varchar(1) default '0' comment '�������� 0������ 1���ظ�',
   top_size	            int default 0 comment '��������',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table tb_dynamic_comments comment '��̬���۱�';


create table tb_fav_user_set
(
   id                   decimal(8,0) not null comment 'id',
   user_id              decimal(8,0) not null comment '�û�id',
   friend_id            decimal(8,0) not null comment '�����û�id',
   block_msg            varchar(1) DEFAULT '0' COMMENT '�Ƿ�����˽��  1:����;0:����',
   block_dynamic        varchar(1) DEFAULT '0' COMMENT '�Ƿ����λ���  1:����;0:����',
   block_reply          varchar(1) DEFAULT '0' COMMENT '�Ƿ����λظ�  1:����;0:����',
   block_comment        varchar(1) DEFAULT '0' COMMENT '�Ƿ���������  1:����;0:����',
   use_flag             decimal(1,0) not null default 1,
   insert_date          timestamp not null default CURRENT_TIMESTAMP,
   insert_id            decimal(8,0) not null,
   update_date          timestamp not null default '0000-00-00 00:00:00',
   update_id            decimal(8,0) not null default 0,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tb_fav_user_set comment '�û����ñ�';

CREATE TABLE `tb_user_blacklist` (
  `id` decimal(8,0) NOT NULL COMMENT 'id',
  `user_id` decimal(8,0) NOT NULL COMMENT '�û�ID',
  `blacklist_user_id` decimal(8,0) NOT NULL COMMENT '�������û�Id',
  `use_flag` decimal(1,0) NOT NULL DEFAULT '1',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_id` decimal(8,0) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_id` decimal(8,0) NOT NULL default 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û���������';
