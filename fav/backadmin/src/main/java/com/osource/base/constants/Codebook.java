package com.osource.base.constants;

/**
 * <p></p>
 *
 * @author : FengJingzhun
 * @version : 1.0
 * @date : 2009-5-21 10:52:52
 */
public interface Codebook
{
    String COMMON_SHARE_FLAG = "common-share_flag";
    String COMMON_SHARE_FLAG_0 = "0";//私有
    String COMMON_SHARE_FLAG_1 = "1";//共享

    String COMMON_USE_FLAG = "common-use_flag";
    String COMMON_USE_FLAG_0 = "0";//删除
    String COMMON_USE_FLAG_1 = "1";//正常
    
    String COMMON_SEX = "common-sex";
    String COMMON_SEX_1 = "1";//男
    String COMMON_SEX_2 = "2";//女
    
    String COMMON_USER_TYPE = "common-user_type";
    String COMMON_USER_TYPE_1 = "1";//平台管理员
    String COMMON_USER_TYPE_2 = "2";//地市管理员
    String COMMON_USER_TYPE_3 = "3";//法人用户
    String COMMON_USER_TYPE_4 = "4";//普通用户
    
    String COMMON_EDU_BG = "common-edu_bg";
    String COMMON_EDU_BG_1 = "1"; //硕士及以上
    String COMMON_EDU_BG_2 = "2";//本科
    String COMMON_EDU_BG_3 = "3";//大专
    String COMMON_EDU_BG_4 = "4";//中专
    String COMMON_EDU_BG_5 = "5";//高中
    String COMMON_EDU_BG_6 = "6";//初中
    String COMMON_EDU_BG_7 = "7";//小学
    String COMMON_EDU_BG_8 = "8";//文盲
    
    String COMMON_AFFIANCE = "common-affiance";
    String COMMON_AFFIANCE_1 = "1";//未婚
    String COMMON_AFFIANCE_2 = "2";//已婚
    String COMMON_AFFIANCE_3 = "3";//离异
    String COMMON_AFFIANCE_4 = "4";//丧偶

    String COMMON_ACCOUNT_TYPE = "common-account_type";
    String COMMON_ACCOUNT_TYPE_1 = "1";//城镇户口
    String COMMON_ACCOUNT_TYPE_2 = "2";//农村户口
    
    String COMMON_CHARGE_TYPE = "common-charge_type";
    String COMMON_CHARGE_TYPE_1 = "1";//贪污贿赂罪
    String COMMON_CHARGE_TYPE_2 = "2";//妨碍社会管理秩序罪
    String COMMON_CHARGE_TYPE_3 = "3";//危害国家安全罪
    String COMMON_CHARGE_TYPE_4 = "4";//危害公共安全罪
    String COMMON_CHARGE_TYPE_5 = "5";//破坏社会主义市场经济秩序罪
    String COMMON_CHARGE_TYPE_6 = "6";//侵犯公民人身权利、民主权利罪
    String COMMON_CHARGE_TYPE_7 = "7";//侵犯财产罪
    String COMMON_CHARGE_TYPE_8 = "8";//渎职罪
    String COMMON_CHARGE_TYPE_9 = "9";//其他
    String COMMON_CHARGE_TYPE_10 = "10";//掩饰、隐瞒犯罪所得、犯罪所得收益罪

    String COMMON_OUTSIDE_TYPE = "common-outside_type";
    String COMMON_OUTSIDE_TYPE_1 = "1";//管制
    String COMMON_OUTSIDE_TYPE_2 = "2";//缓刑
    String COMMON_OUTSIDE_TYPE_3 = "3";//假释
    String COMMON_OUTSIDE_TYPE_4 = "4";//监外执行
    String COMMON_OUTSIDE_TYPE_5 = "5";//剥夺政治权利
    
    String COMMON_MONITOR_LEVEL = "common-monitor_level";
    String COMMON_MONITOR_LEVEL_1 = "1";//安全
    String COMMON_MONITOR_LEVEL_2 = "2";//一般
    String COMMON_MONITOR_LEVEL_3 = "3";//重视
    String COMMON_MONITOR_LEVEL_4 = "4";//严重
    String COMMON_MONITOR_LEVEL_5 = "5";//特别严重

    String COMMON_STATUS = "common-status";
    String COMMON_STATUS_0 = "0";//其它
    String COMMON_STATUS_1 = "1";//正常
    String COMMON_STATUS_2 = "2";//重新犯罪
    String COMMON_STATUS_3 = "3";//脱逃
    String COMMON_STATUS_4 = "4";//下落不明
    String COMMON_STATUS_5 = "5";//脱管漏管
    String COMMON_STATUS_6 = "6";//超期解矫
    String COMMON_STATUS_7 = "7";//死亡
    String COMMON_STATUS_8 = "8";//收监执行
    String COMMON_STATUS_9 = "9";//转出

/*----------------------------------------------------------*/

    String TS_FUNC_BINDING = "ts_func-binding";
    String TS_FUNC_BINDING_1 = "1";//无绑定
    String TS_FUNC_BINDING_2 = "2";//有绑定
    String TS_FUNC_BINDING_3 = "3";//被绑定
    
    String TS_LOGIN_LOG_LOGIN_RESULT = "ts_login_log-login_result";
    String TS_LOGIN_LOG_LOGIN_RESULT_1 = "1";//成功
    String TS_LOGIN_LOG_LOGIN_RESULT_2 = "2";//用户名不存在
    String TS_LOGIN_LOG_LOGIN_RESULT_3 = "3";//密码错误
    String TS_LOGIN_LOG_LOGIN_RESULT_4 = "4";//验证码错误

    String TB_LOCATION_TYPE = "tb_location-type";
    String TB_LOCATION_TYPE_1 = "1";//人工定位
    String TB_LOCATION_TYPE_2 = "2";//定时定位
    String TB_LOCATION_TYPE_3 = "3";//快速定位
    String TB_LOCATION_TYPE_4 = "4";//短信指令定位
    
    String TB_ALARM_CONFIRM_STATUS = "tb_alarm-confirm_status";
    String TB_ALARM_CONFIRM_STATUS_0 = "0";//未确认
    String TB_ALARM_CONFIRM_STATUS_1 = "1";//误报
    String TB_ALARM_CONFIRM_STATUS_2 = "2";//正常

    String TB_ALARM_TYPE = "tb_alarm-type";
    String TB_ALARM_TYPE_1 = "1";//出界
    String TB_ALARM_TYPE_2 = "2";//入界
    
    String TB_RAILINGS_TYPE = "tb_railings-type";
    String TB_RAILINGS_TYPE_1 = "1";//禁出围栏
    String TB_RAILINGS_TYPE_2 = "2";//禁入围栏
    
    String TB_INTEREST_POINT_TYPE = "tb_interest_point-type";
    String TB_INTEREST_POINT_TYPE_01 = "01";//建筑物
    String TB_INTEREST_POINT_TYPE_02 = "02";//公司
    String TB_INTEREST_POINT_TYPE_03 = "03";//住宅
    String TB_INTEREST_POINT_TYPE_04 = "04";//学校
    String TB_INTEREST_POINT_TYPE_05 = "05";//医院
    String TB_INTEREST_POINT_TYPE_06 = "06";//会场
    String TB_INTEREST_POINT_TYPE_07 = "07";//货站
    String TB_INTEREST_POINT_TYPE_08 = "08";//火车站
    String TB_INTEREST_POINT_TYPE_09 = "09";//机场
    String TB_INTEREST_POINT_TYPE_10 = "10";//工厂
    String TB_INTEREST_POINT_TYPE_11 = "11";//其他
    
    String TB_VOLUNTEER_RANK = "tb_volunteer-rank";
    String TB_VOLUNTEER_RANK_1 = "1";// 一级心理咨询师
    String TB_VOLUNTEER_RANK_2 = "2";//二级心理咨询师
    String TB_VOLUNTEER_RANK_3 = "3";//三级心理咨询师
    
    String TB_CRIMINAL_STATUS_CHG_TYPE = "criminal_status_chg-type";// 此处与数据库中表名不一致，名称由张皓提供
    String TB_CRIMINAL_STATUS_CHG_TYPE_1 = "1";//到期解矫
    String TB_CRIMINAL_STATUS_CHG_TYPE_2 = "2";//重新犯罪
    String TB_CRIMINAL_STATUS_CHG_TYPE_3 = "3";//脱逃
    String TB_CRIMINAL_STATUS_CHG_TYPE_4 = "4";//下落不明
    String TB_CRIMINAL_STATUS_CHG_TYPE_5 = "5";//脱管漏管
    String TB_CRIMINAL_STATUS_CHG_TYPE_6 = "6";//超期解矫
    String TB_CRIMINAL_STATUS_CHG_TYPE_7 = "7";//死亡
    String TB_CRIMINAL_STATUS_CHG_TYPE_8 = "8";//其它
    
    String TB_SPECIALIST_POLITICAL_STATUS = "tb_specialist-political_status";
    String TB_SPECIALIST_POLITICAL_STATUS_1 = "1";//中共党员
    String TB_SPECIALIST_POLITICAL_STATUS_2 = "2";//预备党员
    String TB_SPECIALIST_POLITICAL_STATUS_3 = "3";//共青团员
    String TB_SPECIALIST_POLITICAL_STATUS_4 = "4";//群众
    String TB_SPECIALIST_POLITICAL_STATUS_5 = "5";//民主党派人士
    String TB_SPECIALIST_POLITICAL_STATUS_6 = "6";//无党派人士

    String TB_SPECIALIST_PROFESSIONAL_TITLE = "tb_specialist-professional_title";
    String TB_SPECIALIST_PROFESSIONAL_TITLE_1 = "1";//教授
    String TB_SPECIALIST_PROFESSIONAL_TITLE_2 = "2";//副教授
    String TB_SPECIALIST_PROFESSIONAL_TITLE_3 = "3";//讲师
    String TB_SPECIALIST_PROFESSIONAL_TITLE_4 = "4";//研究员
    String TB_SPECIALIST_PROFESSIONAL_TITLE_5 = "5";//副研究员

    String TB_DEPT_RANK = "tb_dept-rank";
    String TB_DEPT_RANK_1 = "1";//司法所
    String TB_DEPT_RANK_2 = "2";//区司法局
    String TB_DEPT_RANK_3 = "3";//市司法局
    String TB_DEPT_RANK_4 = "4";//其他

    String TB_USER_DUTY = "tb_user-duty";
    String TB_USER_DUTY_1 = "1";//局长
    String TB_USER_DUTY_2 = "2";//科长
    String TB_USER_DUTY_3 = "3";//所长
    String TB_USER_DUTY_4 = "4";//工作人员
    String TB_USER_DUTY_5 = "5";//管理员

    String TB_NOTICE_TYPE = "tb_notice-type";
    String TB_NOTICE_TYPE_1 = "1";//系统公告
    String TB_NOTICE_TYPE_2 = "2";//司法局公告
    String TB_NOTICE_TYPE_3 = "3";//司法所公告
    
    String TB_NOTICE_STATUS = "tb_notice-status";
    String TB_NOTICE_STATUS_1 = "1";//未发布
    String TB_NOTICE_STATUS_2 = "2";//已发布
    
    String TB_NOTICE_DISPLAY_POSITION = "tb_notice-display_position";
    String TB_NOTICE_DISPLAY_POSITION_1 = "1";//在主界面显示
    String TB_NOTICE_DISPLAY_POSITION_2 = "2";//在登陆页面显示
    String TB_NOTICE_DISPLAY_POSITION_3 = "3";//都显示
    
    String TB_USER_STAFF_TYPE = "tb_user-staff_type";
    String TB_USER_STAFF_TYPE_1 = "1";//社区矫正执法人员
    String TB_USER_STAFF_TYPE_2 = "2";//社区矫正专职工作者
    String TB_USER_STAFF_TYPE_3 = "3";//心理矫治志愿者
    String TB_USER_STAFF_TYPE_4 = "4";//系统管理员
    String TB_USER_STAFF_TYPE_5 = "5";//其它人员

    String TB_USER_STAFF_STATUS = "tb_user-staff_status";
    String TB_USER_STAFF_STATUS_1 = "1";//正常
    String TB_USER_STAFF_STATUS_2 = "2";//离职
    
    int COMMON_PERSON_TYPE = 0;
    int COMMON_PERSON_TYPE_1 = 1;// 矫正工作者
    int COMMON_PERSON_TYPE_2 = 2;//矫正对象
    
    String TB_PERSON_TYPE = "tb_person_type";
    String TB_PERSON_TYPE_0 = "0";
    String TB_PERSON_TYPE_1 = "1";// 矫正工作者
    String TB_PERSON_TYPE_2 = "2";//矫正对象
    
    String COMMON_SMS_SOURCE_TYPE = "";//信息来源类型
    String COMMON_SMS_SOURCE_TYPE_1 = "1";//越界告警
    String COMMON_SMS_SOURCE_TYPE_2 = "2";//信息交互
    String COMMON_SMS_SOURCE_TYPE_3 = "3";//查询积分
    String COMMON_SMS_SOURCE_TYPE_4 = "4";//随机抽查
    String COMMON_SMS_SOURCE_TYPE_5 = "5";//集中学习
    String COMMON_SMS_SOURCE_TYPE_6 = "6";//公益劳动
    String COMMON_SMS_SOURCE_TYPE_7 = "7"; //短信认证码
    
    String SUBMIT_CHECK_TYPE = "submit_check_type";//提请类型
    String SUBMIT_CHECK_TYPE_1 = "1";//图片
    String SUBMIT_CHECK_TYPE_2 = "2";//文档
    
    String SUBMIT_CHECK_TABLE_TYPE = "submit_check_table_type";//审核表类型
    String SUBMIT_CHECK_TABLE_TYPE_1 = "1";//治安管理处罚
    String SUBMIT_CHECK_TABLE_TYPE_2 = "2";//撤销缓刑
    String SUBMIT_CHECK_TABLE_TYPE_3 = "3";//撤销假释
    String SUBMIT_CHECK_TABLE_TYPE_4 = "4";//收监执行
    String SUBMIT_CHECK_TABLE_TYPE_5 = "5";//减刑
     
    public static final int NODE_TYPE_REAL=0; //物理节点
    public static final int NOD_TYPE_LOGIC=1; //逻辑节点
}
