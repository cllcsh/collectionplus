package com.osource.base.ws.util;

public class ErrorCodes
{
	public static final String RETURN_COMMON_SUCCESS = "0000"; //操作成功
	public static final String RETURN_COMMON_FAIL = "0001"; //操作失败
	public static final String RETURN_COMMON_NPARAM = "0002"; //参数要素不全
	public static final String RETURN_COMMON_NTIME = "0003"; //非业务时间
	public static final String RETURN_COMMON_INVALID_PARAM = "0004"; //非法参数
	public static final String RETURN_COMMON_NOPERMIT = "0005"; //授权不通过
	
	/** 未授权 **/
	public static final String RETURN_NOT_EXIST_PROJECT = "0101"; //项目信息不存在
	public static final String RETURN_NOT_EXIST_TERMINAL= "0102"; //事件信息不存在
	public static final String RETURN_NOT_EXIST_CORP = "0103"; //风险信息不存在
	
	/** 数据库异常 **/
	public static final String DB_EXCP_QUERY = "0201"; //查询数据库异常
	public static final String DB_EXCP_INSERT = "0202"; //插入数据异常
	public static final String DB_EXCP_MODIFY = "0203"; //修改数据异常
	public static final String DB_EXCP_DELETE = "0204"; //删除数据异常
	public static final String DB_EXCP_EXE = "0205"; //过程执行异常
	
	/** 业务逻辑错误 **/
	public static final String LOGIC_EMPTY_KEYDATA = "0401"; //关键数据项不能为空
	public static final String LOGIC_EMPTY_BUSIDATA = "0402"; //业务数据内容不存在
	public static final String LOGIC_DATA_NFORMAT = "0403"; //数据格式不正确
	public static final String LOGIC_ERROR_BUSINESS = "0404"; //业务逻辑执行错误
	
	/** 网络通讯错误 **/
	public static final String NETWORK_EXCP_CONNECT = "0601"; //连接服务异常
	public static final String NETWORK_EXCP_DISCONNECT = "0602"; //断开服务异常
	public static final String NETWORK_FAIL_SENDDATA = "0603"; //发送数据失败
	public static final String NETWORK_FAIL_RECVDATA = "0604"; //接收数据失败
}
