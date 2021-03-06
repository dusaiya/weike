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
import com.ict.nasc.weike.webcontrol.database.WeikeCrawler;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: TaskCrawler.java, v 0.1 2016-2-16 下午1:36:27  Exp $
 */
public class TaskCrawler {
    /**日志*/
    private static Log logger = LogFactory.getLog("TASK");

    /**
     * @param context
     */
    public void execute(Context context) {

        try {
            WeikeCrawler crawler = new WeikeCrawler(CommonConstant.crawlerdbPath);
            crawler.setThreads(70);
            String sql = "select a.task_link from mid_task_list_final a "
                    + "left join task b on a.task_id_str = b.task_id "
                    + "where  b.task_id is null "
                    + "order by a.task_id asc";
            ResultSet result = crawler.getStmt().executeQuery(sql);
            while (result.next()) {
                int i = result.getInt("task_id");
                crawler.addSeed("http://task.zbj.com/" + i + "/");
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
