/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.alibaba.webx.weike.app1.module.screen;

import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.citrus.turbine.Context;
import com.ict.nasc.weike.webcontrol.CommonConstant;
import com.ict.nasc.weike.webcontrol.database.WeikePieceworkCrawler;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: TaskCrawler.java, v 0.1 2016-2-16 下午1:36:27  Exp $
 */
public class PieceworkCrawler {
    /**日志*/
    private static Log logger = LogFactory.getLog("PIECEWORK");

    /**
     * @param context
     */
    public void execute(Context context) {

        try {
            WeikePieceworkCrawler crawler = new WeikePieceworkCrawler(CommonConstant.crawlerdbPath);
            crawler.setThreads(100);
            String sql = "select distinct a.cur_task_url from sub_task a "
                         + "left join mid_piecework_record b on a.cur_task_url=b.cur_task_url "
                         + "where  b.Cur_task_url is null "
                         + "order by task_id asc";
            ResultSet rs = crawler.getStmt().executeQuery(sql);
            while (rs.next()) {
                String url = rs.getString("cur_task_url");
                crawler.addSeed(url);
            }
            crawler.start(1);
        } catch (Exception e) {
            logger.error("任务抓取整体任务异常:", e);
            context.put("errMsg", e.getMessage());
        }

    }
    /**
     * 
     * 
     * @param args
     * @throws Exception
     */
    /**
    public static void main(String[] args) throws Exception {

        try {
            WeikeCrawler crawler = new WeikeCrawler("/Users/alibaba/Desktop/zhubajie");
            crawler.setThreads(20);
            WeiboCrawlerAddTool.addCrawler(crawler);
            crawler.start(1);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    **/
}
