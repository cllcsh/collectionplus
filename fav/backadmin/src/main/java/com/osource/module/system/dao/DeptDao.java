package com.osource.module.system.dao;

import java.util.List;

import com.osource.base.util.Entry;
import com.osource.core.exception.IctException;
import com.osource.module.system.model.DeptEntity;
import com.osource.module.system.model.DeptInfo;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:12:24
 */
public interface DeptDao
{
    /**
     * getDeptSelectList(根据父id取得该机构及其下级机构树形列表)
     * <p>在jsp页面用ict标签使用
     * <p><code>&lt;ict:select beanContextId="deptSelect"/&gt;</code>
     * <p>显示列表形如
     * <p><b>江苏</b>
     * <p><b>--南京</b>
     * <p><b>----鼓楼区</b>
     * <p><b>--苏州</b>
     *
     * @param pid 父机构id
     * @return <b>Entry<机构id, 机构名><b>
     * @Exception 异常对象
     */
    public List<Entry<String, String>> getDeptSelectList(Integer pid);
    
    public List<Entry<String, String>> getDeptSelectListEx(Integer pid);

    public List<Entry<String, String>> getDeptSelectListWithoutLower(Integer pid, Integer qid);

    public void insertDept(DeptEntity deptEntity) throws IctException;

    public void deleteDept(DeptEntity deptEntity) throws IctException;

    public void updateDept(DeptEntity deptEntity) throws IctException;

    public void adjustNode(Integer id) throws IctException;

    public List<DeptEntity> queryDept(DeptEntity deptEntity);

    public DeptEntity findDept(DeptEntity deptEntity);
    
    public DeptEntity findDeptEntityById(Integer id);
    
    public List<DeptInfo> findDeptInfoListById(Integer id);
 
    public DeptInfo findDeptInfoById(Integer id);    
    
    public DeptInfo getUpperDeptById(Integer id);
    
    /**
     * @param add by bgwang 2010-03-05
     */
    public List<DeptInfo> findDeptListById(String sqlMap, Integer id);
    
    /*
	 * 根据机构id查询下一级机构列表
	 */
	public List<DeptInfo> getLowerDeptById(Integer id);
	
	/*
	 * 根据机构id查询下一级机构列表(包含本身Id)
	 */
	public List<DeptInfo> getSelfLowerDeptById(Integer id);
}
