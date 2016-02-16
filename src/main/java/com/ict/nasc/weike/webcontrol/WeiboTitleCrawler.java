/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: zhubajie.java, v 0.1 2015-11-10 上午10:01:03  Exp $
 */
public class WeiboTitleCrawler extends DeepCrawler {

    /**
     * 内部变量
     */
    private String filePath;

    /**
     * 文件名称
     */
    private String fileTitle;

    /**
     * 构造类
     * @param crawlPath
     * @throws Exception
     */
    public WeiboTitleCrawler(String crawlPath) throws Exception {
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
    }

    @Override
    public Links visitAndGetNextLinks(Page page) {
        BufferedWriter output = null;
        try {
            File f = new File(filePath + fileTitle);

            if (f.exists()) {
                System.out.println("文件存在");
            } else {
                System.out.println("文件不存在，正在创建...");
                if (f.createNewFile()) {
                    System.out.println("文件创建成功！");
                } else {
                    System.out.println("文件创建失败！");
                }
            }
            output = new BufferedWriter(new FileWriter(f, true));
            fileOutput(page, output);
        } catch (Exception e) {
            System.out.println();
        } finally {
            if (output == null) {
                System.out.println(output);
            } else {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("关闭文件流异常" + e.getMessage());
                }
            }
        }

        return null;
    }

    /**
     * 
     * @param page
     * @param output 输出流
     * @return
     * @throws IOException 
     */
    private Links fileOutput(Page page, BufferedWriter output) throws IOException {
        System.out.println("任务连接\t" + page.getUrl());
        Elements dls = page.getDoc().select("div.success-task-list").select("a.task-item-title");
        for (Element dl : dls) {
            output.write(dl.attr("href"));
            output.newLine();
        }
        //如果要爬取评论，这里可以抽取评论页面的URL，返回
        return null;
    }

    public static void main(String[] args) throws Exception {

        try {
            WeiboTitleCrawler crawler = new WeiboTitleCrawler("/Users/alibaba/Desktop/zhubajie");
            crawler.setFilePath("/Users/alibaba/Desktop/zhubajie-data/");
            crawler.setFileTitle("title.txt");
            crawler.setThreads(25);
            //crawler.addSeed("http://task.zhubajie.com/6452414/");
            crawler.addSeed("http://task.zhubajie.com/o-wlyx/");
            String prefix = "http://task.zhubajie.com/o-wlyx/p";
            Integer endPage = 89;
            for (Integer i = 2; i <= endPage; i++) {
                crawler.addSeed(prefix + i + ".html");
            }

            crawler.start(1);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Getter method for property <tt>filePath</tt>.
     * 
     * @return property value of filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter method for property <tt>filePath</tt>.
     * 
     * @param filePath value to be assigned to property filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Getter method for property <tt>fileTitle</tt>.
     * 
     * @return property value of fileTitle
     */
    public String getFileTitle() {
        return fileTitle;
    }

    /**
     * Setter method for property <tt>fileTitle</tt>.
     * 
     * @param fileTitle value to be assigned to property fileTitle
     */
    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

}
