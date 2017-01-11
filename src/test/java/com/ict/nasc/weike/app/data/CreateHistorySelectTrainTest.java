/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import com.ict.nasc.weike.webcontrol.tools.DbConnectionTool;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: CreateTrainSvmTest.java, v 0.1 2016-5-17 上午10:33:15  Exp $
 */
public class CreateHistorySelectTrainTest extends TestCase {

    /**
     * alter table train_piecework_4train add column work_quality_random int(3);
     * alter table train_piecework_4train add column work_quality_higher int(3);
     */
    public void testCreate() {

        String sql = "";
        try {
            Statement stmt = DbConnectionTool.getDbStatement();
            sql = "select distinct task_id from train_task_split where task_end_datetime>='2015-09-01' and task_end_datetime<'2015-10-01'";
            ResultSet rs = stmt.executeQuery(sql);
            List<String> list = new ArrayList<String>();
            while (rs.next()) {
                list.add(rs.getString("task_id"));
            }

            for (String taskId : list) {
                sql = "select a.piecework_id,"
                      + "case when b.suc_rate_all is null then 0 else b.suc_rate_all end as suc_rate_all,"
                      + "selected from train_piecework_4train a "
                      + "left join train_worker_summary b on a.user_id = b.user_id"
                      + " where task_id =" + taskId;
                rs = stmt.executeQuery(sql);
                List<Piecework> pieceworkList = new ArrayList<Piecework>();
                int selectedCt = 0;
                while (rs.next()) {
                    Piecework piecework = new Piecework();
                    piecework.setPieceworkId(rs.getString("piecework_id"));
                    piecework.setSucRate(rs.getDouble("ability_level_int"));
                    pieceworkList.add(piecework);
                    if (1 == rs.getInt("selected")) {
                        selectedCt = selectedCt + 1;
                    }
                }
                Comparator<? super Piecework> c = new Comparator<Piecework>() {
                    public int compare(Piecework o1, Piecework o2) {
                        Double ability1 = o1.getSucRate();
                        Double ability2 = o2.getSucRate();
                        return (int)Math.ceil(ability1 - ability2);
                    }
                };
                Collections.sort(pieceworkList, c);
                for (int si = 0; si < selectedCt; si++) {
                    try {
                        sql = "update train_piecework_4train set work_quality_higher=1 where piecework_id="
                              + pieceworkList.get(si).getPieceworkId();
                        stmt.executeUpdate(sql);
                    } catch (Exception e) {
                        System.out.println(taskId + "更新最大reputation异常:" + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("数据读取异常" + e.getMessage());
        }
    }

    /****/
    public class Piecework {
        /****/
        String pieceworkId;
        /****/
        Double sucRate;

        /**
         * Getter method for property <tt>pieceworkId</tt>.
         * 
         * @return property value of pieceworkId
         */
        public String getPieceworkId() {
            return pieceworkId;
        }

        /**
         * Setter method for property <tt>pieceworkId</tt>.
         * 
         * @param pieceworkId value to be assigned to property pieceworkId
         */
        public void setPieceworkId(String pieceworkId) {
            this.pieceworkId = pieceworkId;
        }

        /**
         * Getter method for property <tt>sucRate</tt>.
         * 
         * @return property value of sucRate
         */
        public Double getSucRate() {
            return sucRate;
        }

        /**
         * Setter method for property <tt>sucRate</tt>.
         * 
         * @param sucRate value to be assigned to property sucRate
         */
        public void setSucRate(Double sucRate) {
            this.sucRate = sucRate;
        }

    }
}
