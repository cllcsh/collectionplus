package org.express.database;

import java.sql.Connection;

/**
 * 数据库连接对象（接口）
 * @author Rei Ayanami
 *
 */
public interface DBConnection 
{
	public Connection getConn();
	public void setConn(Connection conn);
	public boolean isIsused();
	public void setIsused(boolean isused);
	public boolean isTrans();
	public void setTrans(boolean trans);
}
