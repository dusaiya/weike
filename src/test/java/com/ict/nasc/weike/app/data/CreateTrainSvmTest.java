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
public class CreateTrainSvmTest extends TestCase {

    /**
     * alter table train_piecework_4train add column work_quality_random int(3);
     * alter table train_piecework_4train add column work_quality_higher int(3);
     */
    public void testCreate() {

        String sql = "";
        try {
            Statement stmt = DbConnectionTool.getDbStatement();
            sql = "select distinct task_id from train_piecework_4train "
                    + "where task_id='6233186'";
                  //+ "where task_end_datetime>='2015-09-01' and task_end_datetime<'2015-10-01' "
                  //+ "group by task_id  having sum(work_quality_random)=0";
            ResultSet rs = stmt.executeQuery(sql);
            List<String> list = new ArrayList<String>();
            while (rs.next()) {
                list.add(rs.getString("task_id"));
            }

            for (String taskId : list) {

                sql = "select piecework_id,ability_level_int,selected from train_piecework_4train where task_id ="
                      + taskId;
                rs = stmt.executeQuery(sql);
                List<Piecework> pieceworkList = new ArrayList<Piecework>();
                int selectedCt = 0;
                while (rs.next()) {
                    Piecework piecework = new Piecework();
                    piecework.setPieceworkId(rs.getString("piecework_id"));
                    piecework.setAbilityLevel(rs.getInt("ability_level_int"));
                    pieceworkList.add(piecework);
                    if (1 == rs.getInt("selected")) {
                        selectedCt = selectedCt + 1;
                    }
                }

                //随机更新
                int size = pieceworkList.size();
                int updatedCt = 0;
                for (int si = 0; si < selectedCt; si++) {
                    try {
                        int idx = (int) (Math.random() * size);
                        if (idx == size) {
                            idx = size - 1;//越界处理
                        }
                        sql = "update train_piecework_4train set work_quality_random=1 where piecework_id="
                              + pieceworkList.get(idx).getPieceworkId();
                        int i = stmt.executeUpdate(sql);
                        updatedCt = updatedCt + i;
                    } catch (Exception e) {
                        System.out.println(taskId + "更新随机选取异常:" + e.getMessage());
                    }
                }
                System.out.println("taskId:" + taskId + ";selectedCt:" + selectedCt + ";updatedCt:"
                                   + updatedCt);
                /**
                Comparator<? super Piecework> c = new Comparator<Piecework>() {
                    public int compare(Piecework o1, Piecework o2) {
                        int ability1 = o1.getAbilityLevel();
                        int ability2 = o2.getAbilityLevel();
                        return ability2 - ability1;
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
                **/
            }
        } catch (Exception e) {
            System.out.println("数据读取异常" + e.getMessage());
        }
    }

    /****/
    public class Piecework {
        /****/
        String  pieceworkId;
        /****/
        Integer abilityLevel;

        /**
         * Getter method for property <tt>pieceworkId</tt>.
         * 
         * @return property value of pieceworkId
         */
        public String getPieceworkId() {
            return pieceworkId;
        }

        /**
         * Getter method for property <tt>abilityLevel</tt>.
         * 
         * @return property value of abilityLevel
         */
        public Integer getAbilityLevel() {
            return abilityLevel;
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
         * Setter method for property <tt>abilityLevel</tt>.
         * 
         * @param abilityLevel value to be assigned to property abilityLevel
         */
        public void setAbilityLevel(Integer abilityLevel) {
            this.abilityLevel = abilityLevel;
        }

    }
}
