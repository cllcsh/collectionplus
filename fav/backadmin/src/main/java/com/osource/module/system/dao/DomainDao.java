package com.osource.module.system.dao;

import java.util.List;

import com.osource.base.util.Entry;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:12:24
 */
public interface DomainDao
{
    public List<Entry<String, String>> getDomainSelectList();
}