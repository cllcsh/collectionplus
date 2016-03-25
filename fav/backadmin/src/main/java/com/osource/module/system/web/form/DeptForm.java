package com.osource.module.system.web.form;

import java.util.List;

import com.osource.base.util.Entry;
import com.osource.module.system.model.DeptEntity;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-13 14:33:52
 */
public class DeptForm
{
    private List<Entry<String, String>> domainList;
    private List<Entry<String, String>> deptList;
    private boolean editFlag;
    private Integer deptId;
    private DeptEntity deptEntity;

    public List<Entry<String, String>> getDomainList()
    {
        return domainList;
    }

    public void setDomainList(List<Entry<String, String>> domainList)
    {
        this.domainList = domainList;
    }

    public List<Entry<String, String>> getDeptList()
    {
        return deptList;
    }

    public void setDeptList(List<Entry<String, String>> deptList)
    {
        this.deptList = deptList;
    }

    public boolean isEditFlag()
    {
        return editFlag;
    }

    public void setEditFlag(boolean editFlag)
    {
        this.editFlag = editFlag;
    }

    public Integer getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Integer deptId)
    {
        this.deptId = deptId;
    }

    public DeptEntity getDeptEntity()
    {
        return deptEntity;
    }

    public void setDeptEntity(DeptEntity deptEntity)
    {
        this.deptEntity = deptEntity;
    }
}