package com.osource.base.form.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.form.dao.FormTableFieldDao;
import com.osource.base.form.model.FormTableField;
import com.osource.base.form.service.FormTableFieldService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class FormTableFieldServiceImpl extends BaseServiceImpl<FormTableField> implements FormTableFieldService {

	/** setter and getter methods **/
	
	protected FormTableFieldDao getDao() {
		return (FormTableFieldDao)super.getDao();
	}

	@Autowired
	public void setDao(FormTableFieldDao formTableFieldDao) {
		super.setDao(formTableFieldDao);
	}
}
