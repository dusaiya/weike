/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.tools;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: WeiboCrawlerAddTool.java, v 0.1 2015-11-30 下午2:40:18  Exp $
 */
public class WeiboCrawlerAddTool {

    /**
     * 处理方法
     * @param crawler
     */
    public static void addCrawler(DeepCrawler crawler) {
        for (int i = 0; i <= 7000000; i++) {
            crawler.addSeed("http://task.zbj.com/" + i + "/");
        }
    }

}
