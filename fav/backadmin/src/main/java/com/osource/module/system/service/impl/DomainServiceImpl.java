package com.osource.module.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.util.Entry;
import com.osource.module.system.dao.DomainDao;
import com.osource.module.system.service.DomainService;
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
public class DomainServiceImpl extends BaseServiceImpl implements DomainService
{
	
	@Autowired
    private DomainDao domainDao;

    public DomainDao getDomainDao()
    {
        return domainDao;
    }

    public void setDomainDao(DomainDao domainDao)
    {
        this.domainDao = domainDao;
    }

    public List<Entry<String, String>> getDomainSelectList()
    {
        return domainDao.getDomainSelectList();
    }
}