package com.osource.base.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.osource.util.IctUtil;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-10 10:05:45
 */
public class ColBean<T>
{
    public static final int STRING = 1;
    public static final int DATE = 2;
    public static final int INTEGER = 3;
    public static final int DOUBLE = 4;
    public static final int DATETIME = 5;
    public static final int FLOAT =6;

    public static final int SET_NULL = -1;
    public static final int DEFAULT = 3;
    public static final int NULL = 1;
    public static final int NOT_NULL = 2;
    public static final int EQUAL = 3;
    public static final int NOT_EQUAL = 4;
    public static final int GREATER = 5;
    public static final int GREATER_EQUAL = 6;
    public static final int LESS = 7;
    public static final int LESS_EQUAL = 8;
    public static final int IN = 9;
    public static final int NOT_IN = 10;
    public static final int BETWEEN = 11;
    public static final int NOT_BETWEEN = 12;
    public static final int LIKE = 13;
    public static final int F_LIKE = 14;
    public static final int B_LIKE = 15;

    private String name;
    private int javaType;
    private int opType;
    private List<T> values=new ArrayList<T>();

    public ColBean(String name, int javaType)
    {
        this.name = name;
        this.javaType = javaType;
        this.opType = DEFAULT;
    }

    public String getName()
    {
        return name;
    }

    public int getJavaType()
    {
        return javaType;
    }

    public int getOpType()
    {
        return opType;
    }

    public void setOpType(int opType)
    {
        this.opType = opType;
    }

    /*
    * 使用范围：
    * 拼装where条件
    * */
    public List<T> getValues()
    {
        return values;
    }

    /*
    * 使用范围：
    * update，insert操作,将对象内的ColBean字段转换全部放入至List中
    * */
    public T getValue()
    {
        if (values != null && values.size() > 0)
        {
            return values.get(0);
        }
        else
        {
            return null;
        }
    }

    /*
    * 使用范围：
    * 供页面使用
    * */
    public List<String> getStringValues()
    {
        if (this.values != null && this.values.size() > 0)
        {
            List<String> values = new ArrayList<String>();
            for (int i = 0; 0 < this.values.size(); i++)
            {
                values.add(convert2String(this.values.get(i)));
            }
            return values;
        }
        else
        {
            return null;
        }
    }

    /*
    * 使用范围：
    * 供页面使用
    * */
    public String getStringValue()
    {
        if (values != null && values.size() > 0)
        {
            return convert2String(values.get(0));
        }
        else
        {
            return null;
        }
    }

    /*
    * 使用范围：
    * 
    * */
    public void setValues(List<T> values)
    {
        if (values != null && values.size() > 0)
        {
            this.values = new ArrayList<T>();
            this.values.addAll(values);
        }
        else
        {
            this.values = null;
        }
    }

    /*
    * 使用范围：
    * 供iBATIS使用
    * */
    public void setValue(T value)
    {
        if (value != null)
        {
            this.values = new ArrayList<T>();
            this.values.add(value);
        }
        else
        {
            this.values = null;
        }
    }

    /*
    * 使用范围：
    * 供页面使用
    * */
    public void setStringValues(List<String> values)
    {
        if (values != null && values.size() > 0)
        {
            this.values = new ArrayList<T>();
            for (String value : values)
            {
                if (value != null && !value.trim().equals(""))
                {
                    this.values.add(convert2Object(value));
                }
            }
        }
        else
        {
            this.values = null;
        }
    }

    /*
    * 使用范围：
    * 供页面使用
    * */
    public void setStringValue(String value)
    {
        if (value != null && !value.trim().equals(""))
        {
            values = new ArrayList<T>();
            values.add(convert2Object(value));
        }
    }

    private String convert2String(T o)
    {
        if (o != null)
        {
            switch (javaType)
            {
                case ColBean.STRING:
                {
                    return o.toString();
                }
                case ColBean.DATE:
                {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    return df.format((Date) o);
                }
                case ColBean.INTEGER:
                {
                    return o.toString();
                }
                case ColBean.DOUBLE:
                {
                    return o.toString();
                }
                case ColBean.FLOAT:
                {
                    return o.toString();
                }
                case ColBean.DATETIME:
                {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    return df.format((Date) o);
                }
                default:
                {
                    throw new RuntimeException("unknown javaType : " + opType);
                }
            }
        }
        else
        {
            return null;
        }
    }

    private T convert2Object(String s)
    {
        switch (javaType)
        {
            case ColBean.STRING:
            {
                return (T)s;
            }
            case ColBean.DATE:
            {
                return (T)IctUtil.string2Date(s);
            }
            case ColBean.INTEGER:
            {
                return (T)IctUtil.string2Integer(s);
            }
            case ColBean.DOUBLE:
            {
                return (T)IctUtil.string2Double(s);
            }
            case ColBean.FLOAT:
            {
                return (T)Float.valueOf(s);
            }
            default:
            {
                throw new RuntimeException("unknown javaType : " + opType);
            }
        }
    }
}
