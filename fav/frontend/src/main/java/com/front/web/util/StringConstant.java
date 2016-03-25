package com.front.web.util;

/**
 * 字符串静态类
 * @author gaoxiang
 *
 */
public final class StringConstant 
{
	
	/**
	 * 产品生效方式
	 */
	public static final String PRODUCT_EFFECTIVE = "producteffective";
	
	public static final String USERID_ADMIN = "_admin_role";
	
	public static final String CARRIERID_ADMIN = "1";
	
	public static final String CARRIERID_ID = "1";
	
	public static final String CARRIERID_NAME = "运营商";
	
	public static final String CARRIERID_CUSTOMERID = "";
	
	/**
	 * LIMIT
	 */
	public static final String LIMIT = "LIMIT";
	
	public static final String COUNT_SQL = "select count(*) ";
	
	public static final String STRING = "String"; 
	
	public static final String NUMBER = "Number"; 
	
	public static final String DECIMAL = "decimal"; 
	
	public static final String BOOL = "Bool"; 
	
	public static final String DATE = "Date"; 
	
	public static final String DATETIME = "datetime"; 
	
	public static final String DATE_DAY = "day"; 
	
	public static final String ADD = "新增";
	public static final String EDIT = "编辑";
	public static final String APPROVE = "审批";
	public static final String RESTORE_DATA = "还原数据";
	public static final String BROWSE_OPERATION_DATA = "浏览运行数据";
	public static final String COOPERATIVE_AGREEMENTS = "销售设置";
	public static final String APPLY_APPROVE = "申请审批";
	public static final String CONFIRM = "确认";
	public static final String RECOVER = "恢复";
    public static final String DELETE = "删除";
	public static final String CANCEL = "作废";
	public static final String COPY = "复制";
	public static final String FINISH = "完成";
	public static final String TERMINATION = "终止";
    public static final String CARRIER_ENTRY_CONFIRM_NEED_CONFIRM = "_need_carrier_entry_comfirm";
    // 审批通过
    public static final String APPROVAL_PASS = "1";
    // 审批驳回
    public static final String APPROVAL_REJECT = "0";
     
	/**
	 * 空字符串
	 */
	public static final String BLANK = "";
	
	/**
	 * 空格
	 */
	public static final String SPACE = " ";
	
	/**
	 * 逗号
	 */
	public static final String COMMA = ",";
	
	/**
	 * 是否叶子节点 N非叶子节点
	 */
    public static final String NOT_DETAIL = "N";
    
    /**
     * 是否叶子节点  Y叶子节点
     */
    public static final String DETAIL = "Y";
    
    /**
     * 是否叶子节点 0非叶子节点
     */
    public static final String NOT_DETAIL_NUM = "0";
    
    /**
     * 是否叶子节点  1叶子节点
     */
    public static final String DETAIL_NUM = "1";
    
    /** 
     * 是否停用   N启用状态
     */
    public static final String VOID_ENABLE = "N";
    
    /**
     *  是否停用   Y禁用状态
     */
    public static final String VOID_DISABLE = "Y";
    
    /** 
     * 是否停用   1启用状态
     */
    public static final String VOID_ENABLE_NUM = "1";
    
    /**
     *  是否停用   0禁用状态
     */
    public static final String VOID_DISABLE_NUM = "0";
    
    /**
     *  状态 正常
     */
    public static final String NORMAL_CUSTOMER_STATUS = "_normal_customer_status";
    
    /**
     *  状态 激活
     */
    public static final String ACTIVE_CUSTOMER_STATUS = "_active_customer_status";
    
    /**
     *  状态 沉寂
     */
    public static final String QUIET_CUSTOMER_STATUS = "_quiet_customer_status";
    
    /**
     *  状态 黑名单
     */
    public static final String BLACK_CUSTOMER_STATUS = "_black_customer_status";
    
    /**
     * 上传图片类型 LOGO
     */
    public static final String PRICTURE_TYPE__LOGO = "LOGO";
    
    /**
     * 上传图片类型 LICENSE 营业执照
     */
    public static final String PRICTURE_TYPE__LICENSE = "LICENSE";
    
    /**
     * 上传图片类型 ORG 组织机构
     */
    public static final String PRICTURE_TYPE__ORG = "ORG";
    
    /**
     * 产品类型：纯流量
     */
    public static final String SINGLE_FLOW_PRODUCT_TYPE = "_single_flow_product_type";
    
    
    /**
     * 产品类型：流量包
     */
    public static final String FLOW_PACKET_PRODUCT_TYPE = "_flow_packet_product_type";
    
    
    /**
    * 产品类型：流量卡
    */
   public static final String CARD_FLOW_PRODUCT_TYPE = "_flow_card_product_type";
    
    
    /**
	 * 产品类型
	 */
	public static final String PRODUCT_TYPE = "producttype";
	
	
	/**
	 * 本渠道类型
	 */
	public static final String  OWN_CUSTOMER_TYPE = "own_customer_type";
	
	/**
	 * 本渠道及下级渠道
	 */
	public static final String OWN_AND_LOW_CUSTOMER_TYPE = "own_and_low_customer_type";
	
	/**
	 * 指定渠道类型
	 */
	public static final String SELECT_CUSTOMER_TYPE = "select_customer_type";
	
	
	/**
	 * 所有渠道
	 */
	public static final String ALL_CUSTOMER_TYPE = "all_customer_type";
	
	
	public static final char SINGLE_QUOTES = '\'';
	
	/**
	 * 换行
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * SELECT
	 */
	public static final String SELECT = "SELECT";
	
	
	// 用于数据库笔名
	public static final String AS = " as "; 
	
	// Standard-标准列 
	public static final String STANDARD = "Standard"; 
	
	// ID-唯一标识列
	public static final String ID = "ID"; 
	
	// DataLoadMode 
	public static final String SERVICE_DATA_SERVICE = "Service-DataService";
	
	// SystemDataMark-是否为系统预置数据标识列
	public static final String SYSTEMDATAMARK = "SystemDataMark";
	
	// 列左对齐
	public static final String LEFT_ALIGN = "_left_alignment_type";
	
	// 列右对齐
	public static final String RIGHT_ALIGN = "_right_alignment_type";
	
	// 列中间对齐
	public static final String CENTER_ALIGN = "_middle_alignment_type";
	
	public final static String USEROPSESSION = "user_op_session_table";
	
	public final static String REPORT_CACHE = "reportCache";
	public final static String REPORT_EXPORT_CACHE = "reportExportCache";
	// 编辑中
	public final static String  _EDIT_ORDER_STATUS = "_edit_order_status";
	//待审批
	public final static String  _WAITING_APPROVE_ORDER_STATUS = "_waiting_approve_order_status";
	//审批中
	public final static String  APPROVING_ORDER_STATUS = "_approving_order_status";
	//待确认
	public final static String  _WAITING_COMFIRM_ORDER_STATUS = "_waiting_comfirm_order_status";
	
	//不认可
	public final static String  _NOT_ACCEPT_ORDER_STATUS = "_not_accept_order_status";
	
	//待计费
	public final static String  _WAITING_CHARGE_ORDER_STATUS = "_waiting_charge_order_status";
	
	//待计费
	public final static String  _WAITING_ACTIVE_CHARGE_STATUS= "_waiting_active_charge_status";
	
	//计费中
	public final static String _CHARGING_ORDER_STATUS = "_charging_order_status";
	
	//无需计费
	public final static String _NONE_ACTIVE_CHARGE_STATUS = "_none_active_charge_status";
	
	//待结算
	public final static String  _WAITING_SETTLEMENT_ORDER_STATUS = "_waiting_settlement_order_status";
	
	//未完成
	public final static String  _NOT_ORDER_CLOSE_STATUS = "_not_order_close_status";
	
	//手工完成
	public final static String  _SELFT_ORDER_CLOASE_STATUS = "_selft_order_cloase_status";
	
	//自动完成
	public final static String  _AUTO_ORDER_CLOSE_STATUS = "_auto_order_close_status";
	
	//已完成 
	public final static String  _CLOSED_ORDER_STATUS = "_closed_order_status";
	
	//已作废
	public final static String  _VOID_ORDER_STATUS = "_void_order_status";
	
	//无确认
	public final static String  _NO_COMFIRM_STATUS = "_no_comfirm_status";
	
	//按赠送量预付款授权
	public final static String  _SALE_PRE_SETTELMENT_TYPE = "_sale_pre_settelment_type";
	
	//按赠送量保证金授权
	public final static String  _SALE_LATER_SETTELMENT_TYPE = "_sale_later_settelment_type";
	
	//按采购量预付款结算
	public final static String  _ODER_PRE_SETTELMENT_TYPE = "_oder_pre_settelment_type";
	
	//按采购量后付款结算
	public final static String  _ORDER_LATER_SETTELMENT_TYPE = "_order_later_settelment_type";
	
	//按订购实时扣款结算方式
	public final static String  _ORDER_ACTUALTIME_PRE_SETTELMENT_TYPE = "_order_actualTime_pre_settelment_type";
	
	//按订购实时扣款结算方式
	public final static String  _ORDER_ACTUALTIME_PRE_SETTELMENT_TYPE_ZH = "按订购实时扣款结算";
	
	//销售订单标志
	public final static String  SALEORDER = "saleOrder";
	
	//采购订单标志
	public final static String  PURCHASEORDER = "purchaseOrder";
	
	// 销售方
	public final static String SALE = "sale";
	
	// 购买方
	public final static String PURCHASE = "purchase";
	
	// 采购发起
	public final static String _PURCHASE_ORDER_SOURCE = "_purchase_order_source";

	// 销售发起
	public final static String _SALE_ORDER_SOURCE = "_sale_order_source";
	
	public final static String PLUS = "plus";
	
	public final static String SUBTRACT = "subtract";
	
	public final static String VERTICAL_LINE = "|";
	
	public final static String INFOR_TYPE_INTERNAL = "_infor_type_Internal";
	public final static String INFOR_TYPE_EMAIL = "_infor_type_email";
	public final static String INFOR_TYPE_SHORTMESS = "_infor_type_shortmess";
	
	
	//销售设置业务类型id
	public final static String  CUSTOMER_SALESET_ENTRYID = "_customersaleset";
	
	//客户资料业务类型id
	public final static String  CUSTOMER = "_customer";
	
	//客户资料业务类型id
	public final static String  FLOWCARDHAND = "_flowcardhand";
	
	
}
