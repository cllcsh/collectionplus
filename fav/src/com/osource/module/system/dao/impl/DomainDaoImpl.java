package com.osource.module.system.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.osource.base.util.Entry;
import com.osource.module.system.dao.DomainDao;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:13:44
 */
@SuppressWarnings("unchecked")
@Repository
public class DomainDaoImpl extends BaseDaoImpl implements DomainDao
{
    public List<Entry<String, String>> getDomainSelectList()
    {
        return queryForList("system_domain_getDomainSelectList");
    }
}