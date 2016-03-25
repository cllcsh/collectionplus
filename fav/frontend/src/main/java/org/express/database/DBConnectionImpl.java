package org.express.database;

import java.sql.Connection;

public class DBConnectionImpl implements DBConnection {
	private Connection connection;
	private boolean isused;
	private boolean trans;
	
	@Override
	public Connection getConn() {
		// TODO Auto-generated method stub
		return connection;
	}

	@Override
	public void setConn(Connection conn) {
		// TODO Auto-generated method stub
		connection = conn;
	}

	@Override
	public boolean isIsused() {
		// TODO Auto-generated method stub
		return isused;
	}

	@Override
	public void setIsused(boolean isused) {
		// TODO Auto-generated method stub
		this.isused = isused;
	}
	
	@Override
	public boolean isTrans() {
		return trans;
	}
	@Override
	public void setTrans(boolean trans) {
		this.trans = trans;
	}
}
