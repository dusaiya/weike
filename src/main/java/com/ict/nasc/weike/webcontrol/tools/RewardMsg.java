/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.tools;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: RewardMsg.java, v 0.1 2016-2-29 下午5:20:23  Exp $
 */
public class RewardMsg {
    /** 名词长度 */
    private static final int    rewardNameLength = 3;
    /** 奖励title的结束字符 */
    private static final String rewardNameEndStr = "名";
    /** 金额字符起始 */
    private static final String moneyStartStr    = "￥";
    /**金额字符结束 */
    private static final String moneyEndStr      = "元";

    /**
     * 正则表达测试
     * @param detail 
     * @return 字符串数组
     */
    public static String[][] regrex(String detail) {
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
