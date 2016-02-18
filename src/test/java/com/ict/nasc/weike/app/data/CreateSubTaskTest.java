/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.ict.nasc.weike.webcontrol.tools.DbConnectionTool;

/**
 * 
 *  create table sub_task as select task_Id,sub_Url_Prefix, sub_Count from task;
Query OK, 2173 rows affected (0.06 sec)
Records: 2173  Duplicates: 0  Warnings: 0
select sum(sub_count) from task;
+----------------+
| sum(sub_count) |
+----------------+
|          45987 |
+----------------+
1 row in set (0.01 sec)

mysql> select count(1) from task where sub_count=0;
+----------+
| count(1) |
+----------+
|       86 |
+----------+
1 row in set (0.01 sec)

mysql> select 45987 + 86;
+------------+
| 45987 + 86 |
+------------+
|      46073 |
+------------+

 * @author xueye.duanxy
 * @version $Id: CreateSubTaskTest.java, v 0.1 2015-12-4 下午5:47:58  Exp $
 */
public class CreateSubTaskTest extends TestCase {

    /**
     * 
     */
    public void testCreate() {
        try {
            Statement stmt = DbConnectionTool.getDbStatement();
            String sql = "select task_id, sub_Url_Prefix, sub_Count from sub_task where sub_count>1";
            ResultSet rs = stmt.executeQuery(sql);
            List<Sqlmodel> list = new ArrayList<CreateSubTaskTest.Sqlmodel>();
            while (rs.next()) {
                list.add(new Sqlmodel(rs.getString("task_id"), rs.getString("sub_Url_Prefix"), rs
                    .getInt("sub_Count")));
            }
            System.out.println("list.size" + list.size());
            int i = 0;
            for (Sqlmodel model : list) {
                i++;
                Integer count = model.getCount();
                String taskId = model.getTaskId();
                String url = model.getUrl();
                //System.out.print("第" + i + "个:" + taskId);
                if (count > 0) {
                    for (count = count - 1; count >= 1; count--) {
                        System.out.print(count + "; ");
                        sql = "insert into  sub_task (task_id, sub_Url_Prefix, sub_Count)"
                              + "values ('" + taskId + "','" + url + "'," + count + ")";
                        stmt.executeUpdate(sql);
                    }
                }
            }
            System.out.println("done,total:" + i);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException;" + e);
        } catch (SQLException e) {
            System.out.println("SQLException:" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    /**
     * 临时小模型
     * 
     * @author xueye.duanxy
     * @version $Id: CreateSubTaskTest.java, v 0.1 2015-12-4 下午6:12:04  Exp $
     */
    public class Sqlmodel {
        /****/
        Integer count;
        /****/
        String  url;
        /****/
        String  taskId;

        /**
         * @param taskId
         * @param url
         * @param count
         * **/
        public Sqlmodel(String taskId, String url, Integer count) {
            this.count = count;
            this.url = url;
            this.taskId = taskId;
        }

        /**
         * Getter method for property <tt>count</tt>.
         * 
         * @return property value of count
         */
        public Integer getCount() {
            return count;
        }

        /**
         * Getter method for property <tt>url</tt>.
         * 
         * @return property value of url
         */
        public String getUrl() {
            return url;
        }

        /**
         * Getter method for property <tt>taskId</tt>.
         * 
         * @return property value of taskId
         */
        public String getTaskId() {
            return taskId;
        }

        /**
         * Setter method for property <tt>count</tt>.
         * 
         * @param count value to be assigned to property count
         */
        public void setCount(Integer count) {
            this.count = count;
        }

        /**
         * Setter method for property <tt>url</tt>.
         * 
         * @param url value to be assigned to property url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * Setter method for property <tt>taskId</tt>.
         * 
         * @param taskId value to be assigned to property taskId
         */
        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

    }
}
