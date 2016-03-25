package com.osource.module.system.service;

import java.util.List;

import com.osource.base.util.Entry;


/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:54:11
 */

public interface DomainService
{
    public List<Entry<String, String>> getDomainSelectList();
}