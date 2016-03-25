package com.osource.base.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.osource.base.model.ColBean;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-13 15:21:12
 */
public class Criteria
{
    private String tableName;

    protected List<String> criteriaWithoutValue;

    protected List<Map<String, Object>> criteriaWithSingleValue;

    protected List<Map<String, Object>> criteriaWithListValue;

    protected List<Map<String, Object>> criteriaWithBetweenValue;

    public Criteria()
    {
        super();
        criteriaWithoutValue = new ArrayList<String>();
        criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
        criteriaWithListValue = new ArrayList<Map<String, Object>>();
        criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
    }

    public Criteria(String tableName)
    {
        this();
        this.tableName = tableName;
    }

    public Criteria(String tableName, List<ColBean> colList)
    {
        this(tableName);
        for (ColBean colbean : colList)
        {
            this.andConditionEX(colbean.getOpType(), colbean.getName(), colbean.getValues());
        }
    }


    //获得列的全名tableName.colName
    public String getFullColName(String colName)
    {
        if (tableName != null && tableName.length() > 0)
        {
            return tableName + "." + colName;
        }
        else
        {
            throw new RuntimeException("tableName is null!");
        }
    }

    public boolean isValid()
    {
        return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
    }

    public List<String> getCriteriaWithoutValue()
    {
        return criteriaWithoutValue;
    }

    public List<Map<String, Object>> getCriteriaWithSingleValue()
    {
        return criteriaWithSingleValue;
    }

    public List<Map<String, Object>> getCriteriaWithListValue()
    {
        return criteriaWithListValue;
    }

    public List<Map<String, Object>> getCriteriaWithBetweenValue()
    {
        return criteriaWithBetweenValue;
    }

    protected void addCriterion(String condition)
    {
        if (condition == null)
        {
            throw new RuntimeException("Value for condition cannot be null");
        }
        criteriaWithoutValue.add(condition);
    }

    protected void addCriterion(String condition, Object value, String property)
    {
        if (value == null)
        {
            throw new RuntimeException("Value for " + property + " cannot be null");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", condition);
        map.put("value", value);
        criteriaWithSingleValue.add(map);
    }

    protected void addCriterion(String condition, List<? extends Object> values, String property)
    {
        if (values == null || values.size() == 0)
        {
            throw new RuntimeException("Value list for " + property + " cannot be null or empty");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", condition);
        map.put("values", values);
        criteriaWithListValue.add(map);
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property)
    {
        if (value1 == null || value2 == null)
        {
            throw new RuntimeException("Between values for " + property + " cannot be null");
        }
        List<Object> list = new ArrayList<Object>();
        list.add(value1);
        list.add(value2);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", condition);
        map.put("values", list);
        criteriaWithBetweenValue.add(map);
    }

    protected void addCriterionForJDBCDate(String condition, Date value, String property)
    {
        addCriterion(condition, new java.sql.Date(value.getTime()), property);
    }

    protected void addCriterionForJDBCDate(String condition, List<Date> values, String property)
    {
        if (values == null || values.size() == 0)
        {
            throw new RuntimeException("Value list for " + property + " cannot be null or empty");
        }
        List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
        Iterator<Date> iter = values.iterator();
        while (iter.hasNext())
        {
            dateList.add(new java.sql.Date(iter.next().getTime()));
        }
        addCriterion(condition, dateList, property);
    }

    protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property)
    {
        if (value1 == null || value2 == null)
        {
            throw new RuntimeException("Between values for " + property + " cannot be null");
        }
        addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
    }

    public Criteria andIsNull(String colName)
    {
        addCriterion(getFullColName(colName) + " is null");
        return this;
    }

    public Criteria andIsNotNull(String colName)
    {
        addCriterion(getFullColName(colName) + "  is not null");
        return this;
    }

    public Criteria andEqualTo(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " =", value, fullColName);
        return this;
    }

    public Criteria andNotEqualTo(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " <>", value, fullColName);
        return this;
    }

    public Criteria andGreaterThan(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " >", value, fullColName);
        return this;
    }

    public Criteria andGreaterThanOrEqualTo(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " >=", value, fullColName);
        return this;

    }

    public Criteria andLessThan(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " <", value, fullColName);
        return this;
    }

    public Criteria andLessThanOrEqualTo(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " <=", value, fullColName);
        return this;
    }

    public Criteria andIn(String colName, List values)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " in", values, fullColName);
        return this;
    }

    public Criteria andNotIn(String colName, List values)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " not in", values, fullColName);
        return this;
    }

    public Criteria andBetween(String colName, Object value1, Object value2)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " between", value1, value2, fullColName);
        return this;
    }

    public Criteria andNotBetween(String colName, Object value1, Object value2)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " not between", value1, value2, fullColName);
        return this;
    }

    public Criteria andLike(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " like", "%" + value + "%", fullColName);
        return this;
    }

    public Criteria andFLike(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " like", "%" + value, fullColName);
        return this;
    }

    public Criteria andBLike(String colName, Object value)
    {
        String fullColName = getFullColName(colName);
        addCriterion(fullColName + " like", value + "%", fullColName);
        return this;
    }

    public Criteria andCondition(int opType, String colName, Object... values)
    {
        return andConditionEX(opType, colName, Arrays.asList(values));
    }

    //切记，数据库是什么类型就要传入什么类型
    public Criteria andConditionEX(int opType, String colName, List values)
    {
        switch (opType)
        {
            case ColBean.NULL:
            {
                andIsNull(colName);
                break;
            }
            case ColBean.NOT_NULL:
            {
                andIsNotNull(colName);
                break;
            }
            case ColBean.EQUAL:
            {
                checkValueCount(opType, 1, values);
                andEqualTo(colName, values.get(0));
                break;
            }
            case ColBean.NOT_EQUAL:
            {
                checkValueCount(opType, 1, values);
                andNotEqualTo(colName, values.get(0));
                break;
            }
            case ColBean.GREATER:
            {
                checkValueCount(opType, 1, values);
                andGreaterThan(colName, values.get(0));
                break;
            }
            case ColBean.GREATER_EQUAL:
            {
                checkValueCount(opType, 1, values);
                andGreaterThanOrEqualTo(colName, values.get(0));
                break;
            }
            case ColBean.LESS:
            {
                checkValueCount(opType, 1, values);
                andLessThan(colName, values.get(0));
                break;
            }
            case ColBean.LESS_EQUAL:
            {
                checkValueCount(opType, 1, values);
                andLessThanOrEqualTo(colName, values.get(0));
                break;
            }
            case ColBean.IN:
            {
                andIn(colName, values);
                break;
            }
            case ColBean.NOT_IN:
            {
                andNotIn(colName, values);
                break;
            }
            case ColBean.BETWEEN:
            {
                checkValueCount(opType, 2, values);
                andBetween(colName, values.get(0), values.get(1));
                break;
            }
            case ColBean.NOT_BETWEEN:
            {
                checkValueCount(opType, 2, values);
                andNotBetween(colName, values.get(0), values.get(1));
                break;
            }
            case ColBean.LIKE:
            {
                checkValueCount(opType, 1, values);
                andLike(colName, values.get(0));
                break;
            }
            case ColBean.F_LIKE:
            {
                checkValueCount(opType, 1, values);
                andFLike(colName, values.get(0));
                break;
            }
            case ColBean.B_LIKE:
            {
                checkValueCount(opType, 1, values);
                andBLike(colName, values.get(0));
                break;
            }
            default:
            {
                throw new RuntimeException("unknown opType : " + opType);
            }
        }

        return this;
    }

    private void checkValueCount(int opType, int count, List values)
    {
        if (values == null || values.size() < count)
        {
            throw new RuntimeException("error values count for " + opType);
        }
    }

    @SuppressWarnings("unused")
	private List<Object> array2List(Object[] values)
    {
        if (values.length > 0)
        {
            List<Object> valueList = new ArrayList<Object>();
            valueList.addAll(Arrays.asList(values));
            return valueList;
        }
        else
        {
            return null;
        }
    }
}
