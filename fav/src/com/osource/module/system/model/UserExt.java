package com.osource.module.system.model;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-10 16:43:10
 */
public class UserExt
{
    private Integer ageStart;
    private Integer ageEnd;

    public Integer getAgeStart()
    {
        return ageStart;
    }

    public void setAgeStart(Integer ageStart)
    {
        this.ageStart = ageStart;
    }

    public Integer getAgeEnd()
    {
        return ageEnd;
    }

    public void setAgeEnd(Integer ageEnd)
    {
        this.ageEnd = ageEnd;
    }

    //是否存在额外查询条件
    public boolean isExistCond()
    {
        return ageStart != null || ageEnd != null;
    }

}
