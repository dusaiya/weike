/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

import com.ict.nasc.weike.webcontrol.tools.DbConnectionTool;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: zhubajie.java, v 0.1 2015-11-10 上午10:01:03  Exp $
 */
public class WeikeTitleCrawler extends DeepCrawler {
    /**日志*/
    private static Log logger = LogFactory.getLog("TASK");
    /**
     * 数据库连接
     */
    private Statement  stmt;

    /**
     * 构造类
     * @param crawlPath
     * @throws Exception
     */
    public WeikeTitleCrawler(String crawlPath) throws Exception {
        super(crawlPath);
        /**
         * 获取新浪微博的cookie，账号密码以明文形式传输，请使用小号
        StringBuilder cookie = new StringBuilder();
        cookie.append(LoginCookieConstant.userIdCookieAttrName + "="
                      + LoginCookieConstant.userIdValue + "; ");
        cookie.append(LoginCookieConstant.userKeyCookieAttrName + "="
                      + LoginCookieConstant.userKeyValue + "; ");
         */
        HttpRequesterImpl myRequester = (HttpRequesterImpl) this.getHttpRequester();
        myRequester
            .setCookie("__utma=149590013.933053285.1447139339.1447139339.1447139339.1; __utmb=149590013.4.10.1447139339; __utmc=149590013; __utmz=149590013.1447139339.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; Hm_lvt_20e969969d6befc1cc3a1bf81f6786ef=1447139339; Hm_lpvt_20e969969d6befc1cc3a1bf81f6786ef=1447139435; uniqid=4f54ae15df6f37d1ecbf1f0fecda7f9d; _analysis=74cf%2Fb3IzGoqDE3b252Nc5ZXkNbze88mnyffhnyJeMpEBRG3wOKQ6w3vAcQ4kNROol6GVM8Uy4lC3f6JbAOVKjRd231O6R09%2F3zFARaPtSULIlZRDUWg9pBgrN7b1zWOEZ3DcksXG6yBfKjV5dAqgPFypKvdboJrzmtFb4MnrSlua2xP8mowX%2F8SnLeHJhKIYB8fjSytyRDqLsqbn23IDrxFRjLNsUyKvEP%2BE0cgdFeF2GUxn1tOueUMX0fBuWNNxQ; fvtime=b97dGOq7lIuAkS8tKB6adz315xV6YgwBoSzbFwsmoDbebV1ixcYG; _uq=a25207e19ba0a417e83d12075e1dbc12; footerBarStateTask=0; _uv=2; userkey=7f17Pa2UMYVPkq4TXiQ6AHEYJRhz9%2B4x33zVMLT2DOp3vzrrL3I32lCLGecgsZIzlUssv3JVRCDve4Z3B8Odn2mZus4cjAsI8prdKyoo3aY4VEC4%2FNOyDF8AteyyMRvpq7wSxgRBaVMSeaqd%2BE%2FN43IR97YehGPpMFryw58dMxWmzwYEMGDye0%2BSY267BSY%2BMwPnrykOr5Q40hKz%2BIMSqV1t3rHrmYQxC9H%2B4McJQaRUMtAzUaIARFURGXzNEeZKhCWuKuIKz4qT; userid=13870023; nickname=crowdextract; brandname=crowdextract; viewed_task=13870023%3A6452414; _zbj_chat_=102; webimMainPage=go7lc025k1f");
        setStmt(DbConnectionTool.getDbStatement());
    }

    @Override
    public Links visitAndGetNextLinks(Page page) {
        try {
            fileOutput(page);
        } catch (Exception e) {
            logger.error(page.getUrl(), e);
        }
        return null;
    }

    /**
     * 爬虫元素获取主程序
     * @param page
     * @return
     * @throws IOException 
     */
    private Links fileOutput(Page page) throws IOException {
        String mainPage = page.getUrl();
        String taskId = mainPage.split("/")[3];
        //Boolean valid = null;
        try {
            Elements dls = page.getDoc().select("title");
            String title = null;
            if (!CollectionUtils.isEmpty(dls)) {
                title = dls.get(0).text().replace(",", "").replace("'", "");
            }
            String sql = "insert into task_list_final ( task_id, task_link, title, gmt_create)"
                         + "values" + "(" + taskId + ", '" + mainPage + "','" + title + "',now())";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            logger.error("[页面处理异常]" + page.getUrl(), e);
        }

        /**
        Elements dls = page.getDoc().select("div.success-task-list").select("a.task-item-title");
        for (Element dl : dls) {
            String subPage = dl.attr("href");
            String title = dl.text();
            try {
                String sql = "insert into task_list ( task_link, task_title, main_page_link)"
                             + "values" + "('" + subPage + "', '" + title + "','" + mainPage + "')";
                stmt.executeUpdate(sql);
                System.out.println(sql);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        **/
        //如果要爬取评论，这里可以抽取评论页面的URL，返回
        return null;
    }

    public static void main(String[] args) throws Exception {
        try {
            WeikeTitleCrawler crawler = new WeikeTitleCrawler("/Users/alibaba/Desktop/zhubajie");
            crawler.setThreads(10);
            String sql = "select a.task_link from mid_task_list_final a "
                         + "left join task b on a.task_id_str = b.task_id "
                         + "where  b.task_id is null "
                         + "limit 10000";
            ResultSet result = crawler.getStmt().executeQuery(sql);
            while (result.next()) {
                crawler.addSeed(result.getString("task_link"));
            }
            crawler.start(1);
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * insert into task_list_final  select distinct task_id, task_link from task_list;
         * update task_list_final set task_id = substring(task_link,21);
         */
    }

    /**
     * Getter method for property <tt>stmt</tt>.
     * 
     * @return property value of stmt
     */
    public Statement getStmt() {
        return stmt;
    }

    /**
     * Setter method for property <tt>stmt</tt>.
     * 
     * @param stmt value to be assigned to property stmt
     */
    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

}
