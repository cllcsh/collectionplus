package com.osource.module.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.constants.Codebook;
import com.osource.base.model.ColBean;
import com.osource.base.util.Entry;
import com.osource.base.web.UserSession;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.module.system.dao.DeptDao;
import com.osource.module.system.model.DeptEntity;
import com.osource.module.system.model.DeptInfo;
import com.osource.module.system.service.DeptService;
import com.osource.orm.ibatis.BaseServiceImpl;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:54:11
 */

@SuppressWarnings("unchecked")
@Service
@Scope("prototype")
@Transactional
public class DeptServiceImpl extends BaseServiceImpl implements DeptService
{
    @Autowired
    private DeptDao deptDao;


    public DeptDao getDeptDao()
    {
        return deptDao;
    }

    public void setDeptDao(DeptDao deptDao)
    {
        this.deptDao = deptDao;
    }

    public List<Entry<String, String>> getDeptSelectList(Integer pid)
    {
        return deptDao.getDeptSelectList(pid);
    }
    
    //根据机构id查询机构的下属机构(不含第5级机构)列表(带缩进)
    public List<Entry<String, String>> getDeptSelectListEx(Integer pid)
    {
        return deptDao.getDeptSelectListEx(pid);
    }

    public List<Entry<String, String>> getDeptSelectListWithoutLower(Integer pid, Integer qid)
    {
        return deptDao.getDeptSelectListWithoutLower(pid, qid);
    }

    public void saveDept(DeptEntity saveEntity, UserSession userSession) throws IctException
    {
        saveEntity.setUserSession(userSession);
        if (saveEntity.getId().getValue() == null)
        {
            saveEntity.getId().setStringValue(IDgenerator.getNextID("tb_dept"));
            deptDao.insertDept(saveEntity);
        }
        else
        {
            saveEntity.addCriteria()
                    .andCondition(ColBean.EQUAL, "id", saveEntity.getId().getValue());
            DeptEntity d = findDeptById(saveEntity.getId().getValue());
            saveEntity.setNode(d.getNode());
            saveEntity.setUseFlag(d.getUseFlag());
            saveEntity.setInsertId(d.getInsertId());
            saveEntity.setInsertDate(d.getInsertDate());
//            saveEntity.setCode(d.getCode());
            try {
				deptDao.updateDept(saveEntity);
			} catch (IctException e) {
				e.printStackTrace();
				throw e;
			}
        }
        deptDao.adjustNode(saveEntity.getId().getValue());
    }

    public void deleteDeptById(Integer id) throws IctException
    {
        DeptEntity deleteEntity = new DeptEntity();
        deleteEntity.setUserSession(getUserSession());
        deleteEntity.getUseFlag().setValue(Codebook.COMMON_USE_FLAG_0);
        deleteEntity.addCriteria()
                .andCondition(ColBean.EQUAL, "id", id);
        deptDao.updateDept(deleteEntity);
    }

    public void deleteDeptById(List<Integer> ids) throws IctException
    {
        DeptEntity deleteEntity = new DeptEntity();
        deleteEntity.setUserSession(getUserSession());
        deleteEntity.getUseFlag().setValue(Codebook.COMMON_USE_FLAG_0);
        deleteEntity.addCriteria()
                .andConditionEX(ColBean.IN, "id", ids);
        deptDao.deleteDept(deleteEntity);
    }

    public List<DeptEntity> queryDeptList(DeptEntity queryEntity)
    {
        queryEntity.setUserSession(getUserSession());
        ColBean<String> c = new ColBean<String>("userDeptId", 3);
        c.setStringValue(String.valueOf(getUserSession().getDeptId()));
        //queryEntity.setUserDeptId(c);
        
        queryEntity.addCriteria(queryEntity.getColList());
        return deptDao.queryDept(queryEntity);
    }

    public DeptEntity findDeptById(Integer id)
    {
//        DeptEntity findEntity = new DeptEntity();
//        findEntity.setUserSession(getUserSession());
//        findEntity.addCriteria()
//                .andCondition(ColBean.EQUAL, "id", id);
//        return deptDao.findDept(findEntity);
    	return deptDao.findDeptEntityById(id);
    }
    
    /**
     * added by lifa, 2010-2-2.
     * 用于矫正对象统计月表中
     */
    public DeptEntity findDeptEntityById(Integer id)
    {
        
        return deptDao.findDeptEntityById(id);
    }
    
    /**
     * added by weiwu, 2010-2-2.
     * 用于历史轨迹中
     */
    public List<DeptInfo> findDeptInfoListById(Integer id)
    {
        
        return deptDao.findDeptInfoListById(id);
    }
    
    public DeptInfo findDeptInfoById(Integer id)
    {
        
        return deptDao.findDeptInfoById(id);
    }
    
    /*
	 * 根据机构id查询下一级机构列表
	 */
	public List<DeptInfo> getLowerDeptById(Integer id) {
		return deptDao.getLowerDeptById(id);
	}
	
	/*
	 * 根据机构id查询下一级机构列表(包含本身Id)
	 */
	public List<DeptInfo> getSelfLowerDeptById(Integer id){
		return deptDao.getSelfLowerDeptById(id);
	}
	
    public DeptInfo getUpperDeptById(Integer id)
    {
        
        return deptDao.getUpperDeptById(id);
    }
}