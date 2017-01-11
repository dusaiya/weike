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
import com.ict.nasc.weike.webcontrol.tools.RewardMsg;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: InsertSqlTest.java, v 0.1 2015-12-1 上午9:08:27  Exp $
 */
public class RewardSqlTest extends TestCase {

    /**
     * 唯一问题数据 http://task.zbj.com/324324/
     * 假设金额600*1,400*1,300*1 总计1300
     */
    public void test_select() {
        try {
            Statement stmt = DbConnectionTool.getDbStatement();
            String sql = "select task_id, payment_Detail from task_f_reward";
            ResultSet result = stmt.executeQuery(sql);
            List<String> taskIdList = new ArrayList<String>();
            List<String> paymentDetailList = new ArrayList<String>();
            while (result.next()) {
                taskIdList.add(result.getString("task_id"));
                paymentDetailList.add(result.getString("payment_Detail"));
            }
            System.out.println("task_f_reward count:" + taskIdList.size());
            for (int i = 0; i < taskIdList.size(); i++) {
                updateReward(taskIdList.get(i), paymentDetailList.get(i), stmt);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     * @param taskId
     * @param paymentDetail
     * @param stmt 
     */
    private void updateReward(String taskId, String paymentDetail, Statement stmt) {
        StringBuilder sql = new StringBuilder();
        try {
            String[][] rewardMsg = RewardMsg.regrex(paymentDetail);
            sql.append("insert into task_f_reward (task_id, payment_detail, count1, reward1, "
                       + "count2, reward2, count3, reward3, count4, reward4, count5, reward5) values ("
                       + taskId + ", '" + paymentDetail + "'");
            for (int i = 0; i < 5; i++) {
                sql.append(", ");
                sql.append(rewardMsg[i][0]);
                sql.append(", ");
                sql.append(rewardMsg[i][1]);
            }
            sql.append(")");

            stmt.executeUpdate(sql.toString());
        } catch (Exception e) {
            System.out.println("Error, taskId:" + taskId + ";paymentDetail:" + paymentDetail
                               + ";sql" + sql.toString());
        }

    }
}
