/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: DbConnectionTool.java, v 0.1 2015-12-1 下午4:17:18  Exp $
 */
public class DbConnectionTool {

    /**
     * 
     */
    public static final String remote_in_lab = "jdbc:mysql://10.60.1.92:3306/weike?"
                                               + "user=root&password=ictsoft&useUnicode=true&characterEncoding=UTF8";
    /**
     * 
     */
    public static final String remote_out_lab = "jdbc:mysql://159.226.40.98:3306/weike?"
                                               + "user=root&password=ictsoft&useUnicode=true&characterEncoding=UTF8";

    /**
     * 本机连接
     */
    public static final String local         = "jdbc:mysql://localhost:3306/weike?"
                                               + "user=root&password=ictsoft&useUnicode=true&characterEncoding=UTF8";

    /**
     * 获取数据库连接
     * 
     * @return Statement stmt
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Statement getDbStatement() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        String url = remote_in_lab;
        conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        return stmt;
    }
}
