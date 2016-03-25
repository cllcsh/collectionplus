package com.front.web.framework.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetInterface {
	Object getObject(ResultSet rs) throws SQLException;
}
