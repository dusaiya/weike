/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import junit.framework.TestCase;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: ReprexTest.java, v 0.1 2016-2-29 下午4:37:14  Exp $
 */
public class ReprexTest extends TestCase {

    /** 交易详情信息 */
    private static final String paymentDetail    = "赏金分配：比赛，已选4个，还需要0个 奖项 一等奖1名(已颁发)，赏金￥300.00元 二等奖1名(已颁发1个)，赏金￥150.00元 三等奖2名(已颁发2个)，赏金￥75.00元";
    /** 名词长度 */
    private static final int    rewardNameLength = 3;
    /** 奖励title的结束字符 */
    private static final String rewardNameEndStr = "名";
    /** 金额字符起始 */
    private static final String moneyStartStr    = "￥";
    /**金额字符结束 */
    private static final String moneyEndStr      = "元";

    /**
     * 测试用例
     */
    public void testRegrex() {
        String[][] rewardMsg = regrex(paymentDetail);
        String taskId = "123";
        StringBuilder sql = new StringBuilder();
        sql.append("insert into task_reward (task_id, payment_detail, count1, reward1, "
                   + "count2, reward2, count3, reward3, count4, reward4, count5, reward5) values ("
                   + taskId + ", '" + paymentDetail + "'");
        for (int i = 0; i < 5; i++) {
            sql.append(", ");
            sql.append(rewardMsg[i][0]);
            sql.append(", ");
            sql.append(rewardMsg[i][1]);
        }
        sql.append(")");
        System.out.println(sql.toString());

    }

    /**
     * 正则表达测试
     * @param detail 
     * @return 字符串数组
     */
    private String[][] regrex(String detail) {
        /** 金额信息 */
        String[][] rewardMsg = new String[5][2];
        int[] idx = new int[5];
        idx[0] = detail.indexOf("一等奖");
        idx[1] = detail.indexOf("二等奖");
        idx[2] = detail.indexOf("三等奖");
        idx[3] = detail.indexOf("四等奖");
        idx[4] = detail.indexOf("五等奖");
        for (int i = 0; i < 5; i++) {
            String subStr = "";
            boolean endFlag = i == 4 || idx[i + 1] == -1;
            if (endFlag) {
                subStr = detail.substring(idx[i]);
            } else {
                subStr = detail.substring(idx[i], idx[i + 1]);
            }
            System.out.println(subStr);
            String position = subStr.substring(rewardNameLength, subStr.indexOf(rewardNameEndStr));
            int moneyStart = subStr.indexOf(moneyStartStr);
            int moneyEnd = subStr.indexOf(moneyEndStr);
            String money = subStr.substring(moneyStart + 1, moneyEnd).replace(".", "");
            rewardMsg[i][0] = position;
            rewardMsg[i][1] = money;
            if (endFlag) {
                break;
            }
        }
        return rewardMsg;

    }
}
