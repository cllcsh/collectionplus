package com.osource.module.system.dao.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.base.model.ColBean;
import com.osource.base.util.Entry;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.dao.DeptDao;
import com.osource.module.system.model.DeptEntity;
import com.osource.module.system.model.DeptInfo;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:13:44
 */
@SuppressWarnings("unchecked")
@Repository
public class DeptDaoImpl extends BaseDaoImpl implements DeptDao
{

    public List<Entry<String, String>> getDeptSelectList(Integer pid)
    {
        return queryForList("system_dept_getDeptSelectList", pid);
    }
    
    public List<Entry<String, String>> getDeptSelectListEx(Integer pid)
    {
        return queryForList("system_dept_getDeptSelectListEx", pid);
    }

    public List<Entry<String, String>> getDeptSelectListWithoutLower(Integer pid, Integer qid)
    {
        Map param = new HashMap();
        param.put("pid", pid);
        param.put("qid", qid);
        return queryForList("system_dept_getDeptSelectListWithoutLower", param);
    }

    public void insertDept(DeptEntity deptEntity) throws IctException
    {
        insert("solo_insert", deptEntity);
    }

    public void deleteDept(DeptEntity deptEntity) throws IctException
    {
    	update("system_dept_delete", deptEntity);
    }

    public void updateDept(DeptEntity deptEntity) throws IctException
    {
    	//修改更新时间不起作用问题。Modified by lifa,2013-12-24.
    	if(deptEntity != null ){
    		ColBean bean = new ColBean<Integer>("update_date", ColBean.DATE);
    		bean.setValue(new Date());
    		deptEntity.setUpdateDate(bean);
    	}
        update("system_dept_update", deptEntity);
    }

    public void adjustNode(Integer id) throws IctException
    {
        update("system_dept_adjustNode", id);
    }

    public List<DeptEntity> queryDept(DeptEntity deptEntity)
    {
        Pages pages = deptEntity.getPages();
        PageList<DeptEntity> pl = new PageList<DeptEntity>();
        if (pages == null)
        {
            pl.addAll(queryForList("system_dept_select", deptEntity));
        }
        else
        {
            pages.setTotal(queryForList("system_dept_select", deptEntity).size());
            pages.executeCount();
            pl.addAll(queryForList("system_dept_select", deptEntity, pages.getStart(), pages.getLimit()));
            pl.setPages(pages);

        }
        return pl;
    }

    public DeptEntity findDept(DeptEntity deptEntity)
    {
        return (DeptEntity) queryForObject("system_dept_select", deptEntity);
    }
    
    /**
 	 *根据机构id和自定义sqlMap查询所属机构列表 add by bigangwang 2010-03-05
     */
    public List<DeptInfo> findDeptListById(String sqlMap, Integer id){
    	return queryForList(sqlMap, id);
    }
    
     /**
     * added by lifa, 2010-2-2.
     * 用于矫正对象统计月表中
     */
     public DeptEntity findDeptEntityById(Integer id)
    {
        return (DeptEntity) queryForObject("system_dept_findDeptEntityById", id);
    }

     /**
      * added by weiwu, 2010-2-2.
      * 用于历史轨迹中
      */
	public List<DeptInfo> findDeptInfoListById(Integer id) {
		return queryForList("system_dept_findDeptInfoListById", id);
	}

	public DeptInfo findDeptInfoById(Integer id) {
		return (DeptInfo) queryForObject("system_dept_findDeptInfoById", id);
	}

	/*
	 * 根据机构id查询上一级机构信息
	 */
	public DeptInfo getUpperDeptById(Integer id) {
		return (DeptInfo) queryForObject("system_dept_getUpperDeptById", id);
	}
	
	/*
	 * 根据机构id查询下一级机构列表
	 */
	public List<DeptInfo> getLowerDeptById(Integer id) {
		return queryForList("system_dept_getLowerDeptById", id);
	}
	
	/*
	 * 根据机构id查询下一级机构列表(包含本身Id)
	 */
	public List<DeptInfo> getSelfLowerDeptById(Integer id){
		return queryForList("system_dept_getSelfLowerDeptById", id);
	}
}
