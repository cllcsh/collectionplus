package com.osource.base.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.base.common.Criteria;
import com.osource.core.AbstractUserSession;
import com.osource.core.page.Pages;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-6 11:06:30
 */
public class BaseEntity
{
    private static final Log logger = LogFactory.getLog(BaseEntity.class);

    private ColBean<Integer> id = new ColBean<Integer>("id", ColBean.INTEGER);
    private ColBean<Integer> deptId = new ColBean<Integer>("dept_id", ColBean.INTEGER);
    private ColBean<String> useFlag = new ColBean<String>("use_flag", ColBean.STRING);
    private ColBean<Date> insertDate = new ColBean<Date>("insert_date", ColBean.DATETIME);
    private ColBean<Integer> insertId = new ColBean<Integer>("insert_id", ColBean.INTEGER);
    private ColBean<Date> updateDate = new ColBean<Date>("update_date", ColBean.DATETIME);
    private ColBean<Integer> updateId = new ColBean<Integer>("update_id", ColBean.INTEGER);

    private String tableName;//实体对应的表名
    private AbstractUserSession userSession;
    private Pages pages;

    protected List<Criteria> oredCriteria = new ArrayList<Criteria>();//基本的查询条件，"OR"连接
    protected String orderByClause;//排序条件


    public List<ColBean> getColList()
    {
    	List<ColBean> colList = new ArrayList<ColBean>();
    	Field[] fdsThis = this.getClass().getDeclaredFields();
    	Field[] fdsBase = BaseEntity.class.getDeclaredFields();
    	colList.addAll(fields2ColBeanList(fdsThis));
    	colList.addAll(fields2ColBeanList(fdsBase));
//        colList.addAll(fields3ColBeanList(fdsThis));
//        colList.addAll(fields3ColBeanList(fdsBase));
    	return colList;
    }
    public List<ColBean> getColListUpdate()
    {
        List<ColBean> colListUpdate = new ArrayList<ColBean>();
        Field[] fdsThis = this.getClass().getDeclaredFields();
        Field[] fdsBase = BaseEntity.class.getDeclaredFields();
        colListUpdate.addAll(fields3ColBeanList(fdsThis));
        colListUpdate.addAll(fields3ColBeanList(fdsBase));
        return colListUpdate;
    }

    private List<ColBean> fields3ColBeanList(Field[] fields)
    {
    	List<ColBean> colList = new ArrayList<ColBean>();
    	for (Field fd : fields)
    	{
    		if (fd.getType() == ColBean.class)
    		{
//                System.out.println(fd.getName());
    			try
    			{
    				fd.setAccessible(true);
    				ColBean cb = (ColBean) fd.get(this);
					colList.add(cb);
    			}
    			catch (IllegalAccessException e)
    			{
    				e.printStackTrace();
    				logger.debug(this.getClass() + " => field " + fd.getName() + " IllegalAccess\r\n" + e);
    			}
    		}
    	}
    	return colList;
    }
    private List<ColBean> fields2ColBeanList(Field[] fields)
    {
        List<ColBean> colList = new ArrayList<ColBean>();
        for (Field fd : fields)
        {
            if (fd.getType() == ColBean.class)
            {
//                System.out.println(fd.getName());
                try
                {
                    fd.setAccessible(true);
                    ColBean cb = (ColBean) fd.get(this);
                    if (cb.getValue() != null || cb.getOpType() == ColBean.SET_NULL)
                    {
                        colList.add(cb);
                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                    logger.debug(this.getClass() + " => field " + fd.getName() + " IllegalAccess\r\n" + e);
                }
            }
        }
        return colList;
    }

    public ColBean<Integer> getId()
    {
        return id;
    }

    public void setId(ColBean<Integer> id)
    {
        this.id = id;
    }

    public ColBean<Integer> getDeptId()
    {
        return deptId;
    }

    public void setDeptId(ColBean<Integer> deptId)
    {
        this.deptId = deptId;
    }

    public ColBean<String> getUseFlag()
    {
        return useFlag;
    }

    public void setUseFlag(ColBean<String> useFlag)
    {
        this.useFlag = useFlag;
    }

    public ColBean<Date> getInsertDate()
    {
        return insertDate;
    }

    public void setInsertDate(ColBean<Date> insertDate)
    {
        this.insertDate = insertDate;
    }

    public ColBean<Integer> getInsertId()
    {
        return insertId;
    }

    public void setInsertId(ColBean<Integer> insertId)
    {
        this.insertId = insertId;
    }

    public ColBean<Date> getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(ColBean<Date> updateDate)
    {
        this.updateDate = updateDate;
    }

    public ColBean<Integer> getUpdateId()
    {
        return updateId;
    }

    public void setUpdateId(ColBean<Integer> updateId)
    {
        this.updateId = updateId;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public AbstractUserSession getUserSession()
    {
        return userSession;
    }

    public void setUserSession(AbstractUserSession userSession)
    {
        this.userSession = userSession;
    }

    public Pages getPages()
    {
        return pages;
    }

    public void setPages(Pages pages)
    {
        this.pages = pages;
    }

    public List<Criteria> getOredCriteria()
    {
        return oredCriteria;
    }

    public void setOredCriteria(List<Criteria> oredCriteria)
    {
        this.oredCriteria = oredCriteria;
    }

    public String getOrderByClause()
    {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause)
    {
        this.orderByClause = orderByClause;
    }

    public Criteria addCriteria()
    {
        Criteria criteria = new Criteria(tableName);
        return addCriteria(criteria);
    }

    public Criteria addCriteria(List<ColBean> colList)
    {
        Criteria criteria = new Criteria(tableName, colList);
        return addCriteria(criteria);
    }

    public Criteria addCriteria(Criteria criteria)
    {
        oredCriteria.add(criteria);
        return criteria;
    }

    public void clearCriteria()
    {
        oredCriteria.clear();
    }

//    public Criteria convertColToCriteria()
//    {
//        return new Criteria(tableName, getColList());
//    }
}
