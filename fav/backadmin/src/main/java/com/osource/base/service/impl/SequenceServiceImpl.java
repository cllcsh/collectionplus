/**
 * @author luoj
 * @create 2009-10-27
 * @file SequenceServiceImpl.java
 * @since v0.1
 * 
 */
package com.osource.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.dao.SequenceDao;
import com.osource.core.IDCache;
import com.osource.core.SequenceService;
import com.osource.core.exception.IctException;
import com.osource.orm.ibatis.BaseServiceImpl;

/**
 * @author luoj
 *
 */
@Service("sequenceService")
@Transactional
public class SequenceServiceImpl extends BaseServiceImpl implements SequenceService {
	@Autowired
    private SequenceDao sequenceDao;

    public SequenceDao getSequenceDao() {
        return sequenceDao;
    }

    public void setSequenceDao(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }
	/* (non-Javadoc)
	 * @see com.osource.core.SequenceService#loadTableIds(java.lang.String)
	 */
    @Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	public IDCache loadTableIds(String tableName) throws IctException {
		return sequenceDao.loadTableIds(tableName);
	}

}
