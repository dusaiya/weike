/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

import com.ict.nasc.weike.webcontrol.model.WeikePiecework;
import com.ict.nasc.weike.webcontrol.tools.DbConnectionTool;
import com.ict.nasc.weike.webcontrol.tools.PieceworkTool;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: WeiboDetailCrawler.java, v 0.1 2015-11-18 下午4:22:31  Exp $
 */
public class WeikePieceworkCrawler extends DeepCrawler {
    /**日志*/
    private static Log logger     = LogFactory.getLog("PIECEWORK");

    /**日志*/
    private static Log logger_err = LogFactory.getLog("PIECEWORK-ERR");

    /**
     * 数据库连接
     */
    private Statement  stmt;

    /**
     * @param crawlPath
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public WeikePieceworkCrawler(String crawlPath) throws ClassNotFoundException, SQLException {
        super(crawlPath);
        HttpRequesterImpl myRequester = (HttpRequesterImpl) this.getHttpRequester();
        myRequester
            .setCookie("__utma=149590013.933053285.1447139339.1447139339.1447139339.1; __utmb=149590013.4.10.1447139339; __utmc=149590013; __utmz=149590013.1447139339.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; Hm_lvt_20e969969d6befc1cc3a1bf81f6786ef=1447139339; Hm_lpvt_20e969969d6befc1cc3a1bf81f6786ef=1447139435; uniqid=4f54ae15df6f37d1ecbf1f0fecda7f9d; _analysis=74cf%2Fb3IzGoqDE3b252Nc5ZXkNbze88mnyffhnyJeMpEBRG3wOKQ6w3vAcQ4kNROol6GVM8Uy4lC3f6JbAOVKjRd231O6R09%2F3zFARaPtSULIlZRDUWg9pBgrN7b1zWOEZ3DcksXG6yBfKjV5dAqgPFypKvdboJrzmtFb4MnrSlua2xP8mowX%2F8SnLeHJhKIYB8fjSytyRDqLsqbn23IDrxFRjLNsUyKvEP%2BE0cgdFeF2GUxn1tOueUMX0fBuWNNxQ; fvtime=b97dGOq7lIuAkS8tKB6adz315xV6YgwBoSzbFwsmoDbebV1ixcYG; _uq=a25207e19ba0a417e83d12075e1dbc12; footerBarStateTask=0; _uv=2; userkey=7f17Pa2UMYVPkq4TXiQ6AHEYJRhz9%2B4x33zVMLT2DOp3vzrrL3I32lCLGecgsZIzlUssv3JVRCDve4Z3B8Odn2mZus4cjAsI8prdKyoo3aY4VEC4%2FNOyDF8AteyyMRvpq7wSxgRBaVMSeaqd%2BE%2FN43IR97YehGPpMFryw58dMxWmzwYEMGDye0%2BSY267BSY%2BMwPnrykOr5Q40hKz%2BIMSqV1t3rHrmYQxC9H%2B4McJQaRUMtAzUaIARFURGXzNEeZKhCWuKuIKz4qT; userid=13870023; nickname=crowdextract; brandname=crowdextract; viewed_task=13870023%3A6452414; _zbj_chat_=102; webimMainPage=go7lc025k1f");
        setStmt(DbConnectionTool.getDbStatement());
    }

    /** 
     * @see cn.edu.hfut.dmic.webcollector.fetcher.Visitor#visitAndGetNextLinks(cn.edu.hfut.dmic.webcollector.model.Page)
     */
    @Override
    public Links visitAndGetNextLinks(Page page) {
        try {
            fileOutput(page);
        } catch (Exception e) {
            logger_err.error(page.getUrl() + "[外围处理]", e);
        }

        return null;

    }

    /**
     * 
     * @param page
     */
    private void fileOutput(Page page) {
        Elements dls = page.getDoc().select("dl.js-alert");
        boolean finished = true;
        int pieceworkCount = 0;
        String curTaskUrl = page.getUrl();
        for (Element dl : dls) {
            pieceworkCount++;
            WeikePiecework piecework = new WeikePiecework();
            piecework.setCurTaskUrl(curTaskUrl);
            String[] strList = curTaskUrl.split("/");
            if (strList != null && strList.length >= 4) {
                piecework.setTaskId(strList[3]);
            }
            try {
                PieceworkTool.deliveryElement(dl, piecework);
                PieceworkTool.insert(piecework, stmt);
            } catch (Exception e) {
                finished = false;
                String errSql = "insert into mid_piecework_err ( task_id, piecework_id, cur_task_url )"
                                + "values ('"
                                + piecework.getTaskId()
                                + "','"
                                + piecework.getPieceworkId()
                                + "','"
                                + piecework.getCurTaskUrl()
                                + "')".replace("'null'", "null");
                try {
                    stmt.executeUpdate(errSql);
                } catch (Exception e1) {
                    logger_err.error(curTaskUrl + "【子任务异常记录失败】" + errSql, e1);
                }
            }
        }
        try {
            stmt.executeUpdate("insert into mid_piecework_record(cur_task_url,count,finished) values('"
                               + curTaskUrl + "'," + pieceworkCount + "," + finished + ")");
        } catch (Exception e) {
            logger_err.error(curTaskUrl + "【任务结果记录失败】", e);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            WeikePieceworkCrawler crawler = new WeikePieceworkCrawler(
                "/Users/alibaba/Desktop/zhubajie");
            crawler.setThreads(60);
            String sql = "select distinct a.cur_task_url from sub_task a "
                         + "left join mid_piecework_record b on a.cur_task_url=b.cur_task_url "
                         + "where  "
                         + "b.Cur_task_url is null order by task_id asc";
            //+ "where cur_task_url not in  (select cur_task_url from mid_piecework_record ) limit 10000";
            ResultSet rs = crawler.getStmt().executeQuery(sql);
            while (rs.next()) {
                String url = rs.getString("cur_task_url");
                crawler.addSeed(url);
            }
            crawler.start(1);
        } catch (Exception e) {
            logger.error("框架异常", e);
        }
    }

    /**
    Integer count = rs.getInt("sub_Count");
    if (0 == count) {
        crawler.addSeed(url);
    } else if (count > 0) {
        crawler.addSeed(url + count + ".html");
    }**/

    /**
     * Getter method for property <tt>stmt</tt>.
     * 
     * @return property value of stmt
     */
    public Statement getStmt() {
        return this.stmt;
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
