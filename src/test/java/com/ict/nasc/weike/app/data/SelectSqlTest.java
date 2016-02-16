/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import com.ict.nasc.weike.webcontrol.tools.DbConnectionTool;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: InsertSqlTest.java, v 0.1 2015-12-1 上午9:08:27  Exp $
 */
public class SelectSqlTest extends TestCase {

    /**
     * 
     */
    public void test_select() {
        try {
            String sql = null;
            Statement stmt = DbConnectionTool.getDbStatement();
            sql = "select task_link from task_list_final limit 10";
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()){
                System.out.println(result.getString("task_link"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}

/** sql = "insert into task"
                  + "(task_id, task_title, task_release_date, task_link, release_user_name, release_user_id,"
                  + " release_user_url, total_amt, guaranteed_amt, trade_mode, release_province, release_city,"
                  + " release_source, task_status, mobile_authen, real_name_authen, "
                  + "remark, piecework_amt)"
                  + "values"
                  + "('6241588','1块钱一个','2015-09-21','http://task.zhubajie.com/6241588','wira','12960645',"
                  + "'http://home.zhubajie.com/12960645/','288500','288500','计件','湖北','武汉',"
                  + " '猪八戒网',null,true,true,"
                  + "'发布需求，托管赏金 2015.09.21 服务商交稿 2015.09.24 3 雇主设置合格稿件 2015.10.01 4 交易成功，余额100%退回','100')";


*sql = "insert into task_list ( task_link, main_page_link)" + "values" + "('" + a
                  + "', '" + mainPage + "')";
                  
**/
