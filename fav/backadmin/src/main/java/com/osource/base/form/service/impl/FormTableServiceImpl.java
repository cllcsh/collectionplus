package com.osource.base.form.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.form.dao.FormTableDao;
import com.osource.base.form.model.FormTable;
import com.osource.base.form.service.FormTableService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class FormTableServiceImpl extends BaseServiceImpl<FormTable> implements FormTableService {

	/** setter and getter methods **/
	
	protected FormTableDao getDao() {
		return (FormTableDao)super.getDao();
	}

	@Autowired
	public void setDao(FormTableDao formTableDao) {
		super.setDao(formTableDao);
	}
}
