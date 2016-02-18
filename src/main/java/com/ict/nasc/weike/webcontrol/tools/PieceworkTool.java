/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.tools;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ict.nasc.weike.webcontrol.model.WeikePiecework;


/**
 * 
 * @author xueye.duanxy
 * @version $Id: PieceworkTool.java, v 0.1 2015-12-4 下午12:18:31  Exp $
 */
public class PieceworkTool {
    
    /**日志*/
    private static Log              logger = LogFactory.getLog("NORMAL");
    /**
     * 
     * @param dl
     * @param piecework
     */
    public static void deliveryElement(Element dl, WeikePiecework piecework) {
        Elements aEles = dl.select("div.usertitle");
        //用户信息
        userInfo(piecework, aEles);
        //用户能力信息
        userAbility(dl, piecework);
        //交稿标签信息
        pieceworkTags(dl, piecework);
        //结果标签
        resultLable(dl, piecework);

        //主题内容大字段
        pieceworkContent(dl, piecework);

    }

    /**
     * 
     * @param dl
     * @param piecework
     */
    private static void userAbility(Element dl, WeikePiecework piecework) {
        Elements aEles;
        aEles = dl.select("span.titlelinks").select("a");
        if (CollectionUtils.isNotEmpty(dl.select("span.titlelinks").select("a.employ-butn"))) {
            piecework.setRecommentEmploy(true);
        }
        for (Element subDl : aEles) {
            String aTitle = subDl.attr("title");
            if (StringUtils.isNotBlank(aTitle) && aTitle.contains("服务商已缴纳保证金")) {
                piecework.setFuwubao(true);
                continue; //处理完一项即可跳出，以防止影响后续服务商标签判断
            }
            if (StringUtils.isNotBlank(aTitle) && aTitle.contains("服务商")
                && CollectionUtils.isNotEmpty(subDl.select("i.ui-icosmember"))) {
                piecework.setSpecialCustomerType(aTitle);
                continue; //跳出服务商等级判断
            }
            Elements imgDoc = subDl.select("img");
            if (CollectionUtils.isNotEmpty(imgDoc)) {
                String abilityStr = imgDoc.get(0).attr("title");
                if (StringUtils.isNotBlank(abilityStr) && abilityStr.contains("能力")) {
                    piecework.setAbilityStr(abilityStr);
                    int start = abilityStr.indexOf("能力等级：");
                    int end = abilityStr.indexOf("，能力值：");
                    if (start < 0) {
                        continue;
                    }
                    if (end < 0) {
                        piecework.setAbilityLevel(abilityStr.substring(start + 5));
                    } else {
                        piecework.setAbilityLevel(abilityStr.substring(start + 5, end));
                        piecework.setAbilityValue(abilityStr.substring(end + 5));
                    }
                }
            }
        }
        //是否位推荐服务商
        aEles = dl.select("div.invitetask-details");
        if (CollectionUtils.isNotEmpty(aEles)) {
            piecework.setRecommendCustomer(aEles.get(0).text());
        }
    }

    /**
     * 
     * @param dl
     * @param piecework
     */
    private static void pieceworkTags(Element dl, WeikePiecework piecework) {
        Elements aEles;
        //交稿信息
        aEles = dl.select("div.ntos");
        piecework.setSummitTime(aEles.select("span.time").get(0).attr("title"));//交稿时间
        piecework.setPieceworkId(aEles.select("a.bidid").get(0).text());//参与编号
        piecework.setUserSource(aEles.select("a.likt").get(0).text());//来源
        //浏览状况
        aEles = aEles.select("span.browse");
        if (CollectionUtils.isEmpty(aEles)) {
            aEles = dl.select("span.nobrowse");
        }
        piecework.setReadStatus(aEles.get(0).text());
    }

    /**
     * 
     * @param dl
     * @param piecework
     */
    private static void pieceworkContent(Element dl, WeikePiecework piecework) {
        StringBuilder content = new StringBuilder();
        for (Element contentDl : dl.select("p")) {
            content.append(contentDl.text());
        }
        if (content != null) {
            piecework.setContent(content.toString());//修饰内容
        }
    }

    /**
     * 
     * @param dl
     * @param piecework
     */
    private static void resultLable(Element dl, WeikePiecework piecework) {
        //标签
        String lable = "未知";
        if (CollectionUtils.isNotEmpty(dl.select("div.hege"))) {
            lable = "合格";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.buhege"))) {
            lable = "不合格";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level1"))) {
            lable = "一等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level2"))) {
            lable = "二等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level3"))) {
            lable = "三等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level4"))) {
            lable = "四等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level5"))) {
            lable = "五等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level6"))) {
            lable = "六等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level7"))) {
            lable = "七等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.level8"))) {
            lable = "八等奖";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.zhongbiao"))) {
            lable = "中标";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.bidtaotai"))) {
            lable = "淘汰";
        } else if (CollectionUtils.isNotEmpty(dl.select("div.has-select"))) {
            lable = "备选";
        }
        piecework.setWorkQuality(lable);
    }

    /**
     * 
     * @param piecework
     * @param aEles
     */
    private static void userInfo(WeikePiecework piecework, Elements aEles) {
        String userUrl = aEles.select("a").get(0).attr("href");
        piecework.setUserUrl(userUrl);
        piecework.setUserName(aEles.select("a").get(0).text());
        if (StringUtils.isNotBlank(userUrl)) {
            String[] strList = userUrl.split("/");
            if (strList != null && strList.length > 3) {
                piecework.setUserId(strList[3]);
            }
        }
    }

    /**
     * 
     * @param piecework
     * @param stmt
     * @throws SQLException 
     */
    public static void insert(WeikePiecework piecework, Statement stmt) throws SQLException {
        String sql = "insert into piecework(" + "piecework_id, task_id, cur_task_url, "
                     + "user_id, user_name, user_url, "
                     + "ability_level, ability_value, ability_str, "
                     + "submit_time, user_source, read_status, work_quality, "
                     + "special_customer_type, fuwubao," + " recomment_employ, recommend_customer,"
                     + " content) values ('"
                     + piecework.getPieceworkId()
                     + "','"
                     + piecework.getTaskId()
                     + "','"
                     + piecework.getCurTaskUrl()
                     + "','"
                     + piecework.getUserId()
                     + "','"
                     + piecework.getUserName().replace("'", "").replace(";", "").replace(",", "").replace("\\", "")
                     + "','"
                     + piecework.getUserUrl()
                     + "','"
                     + piecework.getAbilityLevel()
                     + "','"
                     + piecework.getAbilityValue()
                     + "','"
                     + piecework.getAbilityStr()
                     + "','"
                     + piecework.getSummitTime()
                     + "','"
                     + piecework.getUserSource()
                     + "','"
                     + piecework.getReadStatus()
                     + "','"
                     + piecework.getWorkQuality()
                     + "','"
                     + piecework.getSpecialCustomerType()
                     + "',"
                     + piecework.getFuwubao()
                     + ","
                     + piecework.isRecommentEmploy()
                     + ",'"
                     + piecework.getRecommendCustomer()
                     + "','"
                     + piecework.getContent().replace("'", "").replace(";", "").replace(",", "").replace("\\", "")
                     + "')";
        //System.out.println(sql.replace("\r", " ").replace("\n", " ").replace("'null'", "null"));
        int rs = stmt.executeUpdate(sql.replace("\r", " ").replace("\n", " ")
            .replace("'null'", "null"));
        if (rs < 1) {
            logger.error("【插入数据库失败】taskId:" + piecework.getTaskId() + ";curUrl:"
                                   + piecework.getCurTaskUrl() + ";pieceworkId"
                                   + piecework.getPieceworkId());
            throw new SQLException("插入数据库失败");
        }

    }
}
