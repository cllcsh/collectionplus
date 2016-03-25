package org.express.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接代理
 *
 * @author Rei Ayanami
 */
public class DBSession {
    private DBConnection dbConnection = null;
    private Boolean open = false;
    private Boolean trans = false;
    private Boolean isNotCloseMode = false;
    private Boolean isNewConnection = false;

    /***
     * 创建DBSession,默认不关闭连接
     */
    public DBSession() {
        //nothing to do;
    }

    /***
     * 创建是否带关闭连接的DBSession
     *
     * @param isNotCloseMode 是否关闭连接
     */
    public DBSession(boolean isNotCloseMode) {
        this.isNotCloseMode = isNotCloseMode;
    }

    /**
     * 是否创建一个新的DBSession
     *
     * @param isNotCloseMode  是否关闭连接
     * @param isNewConnection 是否是新连接
     */
    public DBSession(boolean isNotCloseMode, boolean isNewConnection) {
        this.isNotCloseMode = isNotCloseMode;
        this.isNewConnection = isNewConnection;
    }

    /**
     * 获取是否关闭属性
     *
     * @return
     */
    public Boolean IsNotCloseMode() {
        return isNotCloseMode;
    }

    /**
     * 设置是否关闭属性
     *
     * @param isNotCloseMode
     */
    public void IsNotCloseMode(Boolean isNotCloseMode) {
        this.isNotCloseMode = isNotCloseMode;
    }

    /**
     * 获取是否新连接属性
     *
     * @return
     */
    public Boolean IsNewConnection() {
        return isNewConnection;
    }

    /**
     * 设置是否新连接属性
     *
     * @param isNewConnection
     */
    public void IsNewConnection(Boolean isNewConnection) {
        if (dbConnection != null && open) {
            throw new DBException("不能在开启连接的情况下，设置此参数");
        }

        this.isNewConnection = isNewConnection;
    }

    /**
     * 当前是否是打开的连接
     *
     * @return
     */
    public Boolean isOpen() {
        return open;
    }

    /**
     * 当前是否开启了事务
     *
     * @return
     */
    public Boolean isBeginTrans() {
        return trans;
    }

    /**
     * 是否打开的连接(线程级别)
     *
     * @return
     */
    public Boolean isDatabaseOpen() {
        if (open) return open;

        try {
            if (getDBConnection() != null && !getDBConnection().getConn().isClosed()) {
                return true;
            }
        } catch (SQLException err) {
            throw new DBException(err);
        }

        return open;
    }

    /**
     * 是否开启了事务(线程级别)
     *
     * @return
     */
    public Boolean isDatabaseBeginTrans() {
        if (trans) return trans;

        try {
            if (getDBConnection() != null) {
                return !getDBConnection().getConn().getAutoCommit();
            }
        } catch (SQLException err) {
            throw new DBException(err);
        }

        return trans;
    }

    /**
     * 得到连接
     *
     * @return
     */
    public Connection getConnection() {
        try {
            if (getDBConnection() != null && !getDBConnection().getConn().isClosed())
                return getDBConnection().getConn();

            DBConnection conn = null;
            if (isNewConnection)
                conn = OpenNewConnection();
            else
                conn = DBManager.getDBConnection();

            if (!conn.isIsused()) {
                conn.setIsused(true);
                open = true;
            }

            setDBConnection(conn);
        } catch (SQLException e) {
            throw new DBException(e);
        }

        return getDBConnection().getConn();
    }

    /**
     * 关闭连接
     *
     * @return 是否成功关闭的连接
     */
    public boolean CloseConnection() {
        boolean result = false;
        try {
            if (open) {
                if (isNewConnection) {
                    CloseConnection(getDBConnection());
                    dbConnection = null;
                    result = true;
                } else {
                    if (!isNotCloseMode) {
                        getDBConnection().setIsused(false);
                        getDBConnection().getConn().close();
                        result = true;
                    }
                }
                open = false;
            }
        } catch (SQLException err) {
            throw new DBException(err);
        }
        return result;
    }

    /**
     * 开始事务
     *
     * @return 是否开始事务
     */
    public boolean BeginTrans() {
        try {
            if (!trans && open) {
                getDBConnection().getConn().setAutoCommit(false);
                trans = true;
            }
        } catch (SQLException err) {
            throw new DBException(err);
        }

        return trans;
    }

    /**
     * 提交事务
     *
     * @return 是否成功提交事务
     */
    public boolean Commit() {
        boolean result = false;
        try {
            if (trans && open) {
                getDBConnection().getConn().commit();
                getDBConnection().getConn().setAutoCommit(true);
                trans = false;
                result = true;
            }
        } catch (SQLException err) {
            throw new DBException(err);
        }

        return result;
    }

    /**
     * 回滚事务
     *
     * @return 是否成功回滚事务
     */
    public boolean RollBack() {
        boolean result = false;
        try {
            if (trans && open) {
                getDBConnection().getConn().rollback();
                getDBConnection().getConn().setAutoCommit(true);
                trans = false;
                result = true;
            }
        } catch (SQLException err) {
            throw new DBException(err);
        }
        return result;
    }

    /**
     * 从连接池得到一个新的Connection,一定记得关闭
     *
     * @return
     * @throws SQLException
     */
    private DBConnection OpenNewConnection() {
        DBConnection conn = null;
        try {
            conn = DBManager.openNewDBConnection();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return conn;
    }

    /**
     * 关闭指定的Connection
     *
     * @param conn
     */
    private void CloseConnection(DBConnection conn) {
        try {
            conn.setIsused(false);
            DBManager.closeConnection(conn.getConn());
        } catch (SQLException err) {
            throw new DBException(err);
        }
    }


    /**
     * 得到Connection
     *
     * @return
     */
    private DBConnection getDBConnection() {
        return dbConnection;
    }

    /**
     * 设置Connection
     *
     * @param conn
     */
    private void setDBConnection(DBConnection conn) {
        this.dbConnection = conn;
    }
}
