package com.osource.base.form.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.base.form.dao.FormTableDao;
import com.osource.base.form.model.FormTable;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class FormTableDaoImpl extends BaseDaoImpl<FormTable> implements FormTableDao {
	@Override
	public String getEntityName() {
		return "archives_formTable";
	}
	
}