package com.osource.base.form.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.base.form.dao.FormTableFieldDao;
import com.osource.base.form.model.FormTableField;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class FormTableFieldDaoImpl extends BaseDaoImpl<FormTableField> implements FormTableFieldDao {
	@Override
	public String getEntityName() {
		return "archives_formTableField";
	}
	
}