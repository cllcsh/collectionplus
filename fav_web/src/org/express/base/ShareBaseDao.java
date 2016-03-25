package org.express.base;

import org.express.database.DBSession;
import org.express.database.Query;

public abstract class ShareBaseDao
{
	private DBSession session = null;
	private Query query = null;
	
	/**
	 * 传递数据库连接代理
	 * @param s
	 */
	public void InitDBSession(DBSession s)
	{
		this.session = s;
	}
	
	/**
	 * 得到数据库查询提供
	 * @return
	 */
	public Query getQuery() {
		if(query == null)
			query = new Query(getDBSession());
		
		return query;
	}
	
	/**
	 * 得到数据库连接代理
	 * @return
	 */
	public DBSession getDBSession()
	{
		if(session == null)
		{
			session = new DBSession(true);
		}
		
		return session;
	}
}
