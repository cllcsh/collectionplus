package com.front.web.util;

/**
 * 自定义异常类
 * 
 * @author chenhua
 * 
 */
public class FavRuntimeException extends RuntimeException 
{

	/**
	 * 串行化ID
	 */
	private static final long serialVersionUID = 5843186450382066098L;
	
	private int errorCode;
    
    private Object obj;
    
    public FavRuntimeException()
    {
    	super();
    }
    
    /**
     * 带异常信息的构造函数
     * @param message 异常消息
     */
    public FavRuntimeException(String message)
    {

        super(message);
    }
    
    /**
     * 带异常信息和异常码构造函数
     * @param message 异常消息
     * @param errorCode 异常码
     */
    public FavRuntimeException(String message, int errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * 带异常信息和异常码构造函数
     * @param message 异常消息
     * @param errorCode 异常码
     * @param obj obj
     */
    public FavRuntimeException(String message, int errorCode, Object obj)
    {
        super(message);
        this.errorCode = errorCode;
        this.obj = obj;
    }
    
    /**
     * 带异常信息和异常实例的构造函数
     * @param message 异常消息
     * @param t    异常实例
     */
    public FavRuntimeException(String message, Throwable t)
    {
        super(message, t);
    }
    
    public int getErrorCode()
    {

        return errorCode;

    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj = obj;
    }
}
