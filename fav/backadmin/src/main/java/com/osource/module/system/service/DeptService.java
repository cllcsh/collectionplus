package com.osource.module.system.service;

import java.util.List;

import com.osource.base.util.Entry;
import com.osource.base.web.UserSession;
import com.osource.core.exception.IctException;
import com.osource.module.system.model.DeptEntity;
import com.osource.module.system.model.DeptInfo;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:54:11
 */

public interface DeptService 
{

    public List<Entry<String, String>> getDeptSelectList(Integer pid);
    
    //根据机构id查询机构的下属机构(不含第5级机构)列表(带缩进)
    public List<Entry<String, String>> getDeptSelectListEx(Integer pid);

    public List<Entry<String, String>> getDeptSelectListWithoutLower(Integer pid, Integer qid);

    public void saveDept(DeptEntity saveEntity, UserSession userSession) throws IctException;

    public void deleteDeptById(Integer id) throws IctException;

    public void deleteDeptById(List<Integer> ids) throws IctException;

    public List<DeptEntity> queryDeptList(DeptEntity queryEntity);

    public DeptEntity findDeptById(Integer id);    
    /**
     * added by lifa, 2010-2-2.
     * 用于矫正对象统计月表中
     */
    public DeptEntity findDeptEntityById(Integer id);
    
    /**
     * added by weiwu, 2010-2-2.
     * 用于历史轨迹中
     */
    public List<DeptInfo> findDeptInfoListById(Integer id);
    
    public DeptInfo findDeptInfoById(Integer id);
    
    public DeptInfo getUpperDeptById(Integer id);
    
    /*
	 * 根据机构id查询下一级机构列表
	 */
	public List<DeptInfo> getLowerDeptById(Integer id);
  
}