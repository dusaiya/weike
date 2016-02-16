/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ict.nasc.weike.webcontrol.tools.DetailCrawlerAddTool;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: WeiboDetailCrawler.java, v 0.1 2015-11-18 下午4:22:31  Exp $
 */
public class WeiboDetailCrawler extends DeepCrawler {

    /**
     * 内部变量
     */
    private String filePath;

    /**
     * 文件名称
     */
    private String fileTitle;

    /**
     * @param crawlPath
     * @param filePath
     */
    public WeiboDetailCrawler(String crawlPath, String filePath) {
        super(crawlPath);
        this.filePath = filePath;
        HttpRequesterImpl myRequester = (HttpRequesterImpl) this.getHttpRequester();
        myRequester
            .setCookie("__utma=149590013.933053285.1447139339.1447139339.1447139339.1; __utmb=149590013.4.10.1447139339; __utmc=149590013; __utmz=149590013.1447139339.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; Hm_lvt_20e969969d6befc1cc3a1bf81f6786ef=1447139339; Hm_lpvt_20e969969d6befc1cc3a1bf81f6786ef=1447139435; uniqid=4f54ae15df6f37d1ecbf1f0fecda7f9d; _analysis=74cf%2Fb3IzGoqDE3b252Nc5ZXkNbze88mnyffhnyJeMpEBRG3wOKQ6w3vAcQ4kNROol6GVM8Uy4lC3f6JbAOVKjRd231O6R09%2F3zFARaPtSULIlZRDUWg9pBgrN7b1zWOEZ3DcksXG6yBfKjV5dAqgPFypKvdboJrzmtFb4MnrSlua2xP8mowX%2F8SnLeHJhKIYB8fjSytyRDqLsqbn23IDrxFRjLNsUyKvEP%2BE0cgdFeF2GUxn1tOueUMX0fBuWNNxQ; fvtime=b97dGOq7lIuAkS8tKB6adz315xV6YgwBoSzbFwsmoDbebV1ixcYG; _uq=a25207e19ba0a417e83d12075e1dbc12; footerBarStateTask=0; _uv=2; userkey=7f17Pa2UMYVPkq4TXiQ6AHEYJRhz9%2B4x33zVMLT2DOp3vzrrL3I32lCLGecgsZIzlUssv3JVRCDve4Z3B8Odn2mZus4cjAsI8prdKyoo3aY4VEC4%2FNOyDF8AteyyMRvpq7wSxgRBaVMSeaqd%2BE%2FN43IR97YehGPpMFryw58dMxWmzwYEMGDye0%2BSY267BSY%2BMwPnrykOr5Q40hKz%2BIMSqV1t3rHrmYQxC9H%2B4McJQaRUMtAzUaIARFURGXzNEeZKhCWuKuIKz4qT; userid=13870023; nickname=crowdextract; brandname=crowdextract; viewed_task=13870023%3A6452414; _zbj_chat_=102; webimMainPage=go7lc025k1f");
    }

    /** 
     * @see cn.edu.hfut.dmic.webcollector.fetcher.Visitor#visitAndGetNextLinks(cn.edu.hfut.dmic.webcollector.model.Page)
     */
    @Override
    public Links visitAndGetNextLinks(Page page) {
        BufferedWriter output = null;
        try {
            String totalString = filePath
                                 + fileTitle
                                 + page.getUrl().substring(25).replace("/", "-")
                                     .replace(".html", "") + ".txt";

            File f = new File(totalString);
            System.out.println(totalString);
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
     * @param output
     * @throws IOException 
     */
    private void fileOutput(Page page, BufferedWriter output) throws IOException {
        Elements dls = page.getDoc().select("h1");
        dls = page.getDoc().select("dl.js-alert");
        for (Element dl : dls) {
            output.write(page.getUrl() + "\t");
            deliveryElement(dl, output);
        }
    }

    /**
     * 
     * @param dl
     * @param output
     * @throws IOException 
     */
    private void deliveryElement(Element dl, BufferedWriter output) throws IOException {
        //System.out.println("用户\t" + "用户姓名\t" + "能力\t" + "交稿时间\t" + "参与编号\t" + "来源\t" + "雇主浏览状况\t"
        //                   + "是否合格\t" );

        Elements aEles = dl.select("a.user-card");
        for (Element aEle : aEles) {

            String answerUser = aEle.attr("href");
            output.write(answerUser + "\t");
            break;//只要第一个用户的信息
        }
        aEles = dl.select("img.touxiangall");
        for (Element aEle : aEles) {

            String answerUser = aEle.attr("alt");
            output.write(answerUser + "\t");

            break;//只要第一个用户的信息
        }
        aEles = dl.select("span.titlelinks");
        Elements subEles = aEles.select("img");
        if (CollectionUtils.isEmpty(subEles)) {
            output.write("无标注" + "\t");
        } else {
            for (Element subEle : subEles) {

                String title = subEle.attr("title");
                output.write(title + "\t");
                break;//只要第一个用户的信息
            }
        }
        //交稿信息
        aEles = dl.select("span.time");
        for (Element aEle : aEles) {
            String answerUser = aEle.attr("title");
            output.write(answerUser + "\t");
            break;//只要第一个用户的信息
        }
        aEles = dl.select("a.bidid");
        for (Element aEle : aEles) {

            String answerUser = aEle.text();
            output.write(answerUser + "\t");

            break;//只要第一个用户的信息
        }
        aEles = dl.select("a.likt");
        for (Element aEle : aEles) {

            String answerUser = aEle.text();
            output.write(answerUser + "\t");

            break;//只要第一个用户的信息
        }
        aEles = dl.select("span.browse");
        if (CollectionUtils.isEmpty(aEles)) {
            aEles = dl.select("span.nobrowse");
        }
        for (Element aEle : aEles) {
            String answerUser = aEle.text();
            output.write(answerUser + "\t");
            break;//只要第一个用户的信息
        }
        /**
        aEles = dl.select("u");
        for (Element aEle : aEles) {
            String answerUser = aEle.text();
            output.write(answerUser + "\t");
            break;//只要第一个用户的信息
        }**/
        aEles = dl.select("div.hege");
        if (CollectionUtils.isNotEmpty(aEles)) {
            output.write("合格" + "\t");
        } else {
            aEles = dl.select("div.buhege");
            if (CollectionUtils.isNotEmpty(aEles)) {
                output.write("不合格" + "\t");
            } else {
                output.write("未知" + "\t");
            }
        }
        output.newLine();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //获取全部url
        List<String> detailUrlList = DetailCrawlerAddTool.addSubCrawler();
        try {
            WeiboDetailCrawler crawler = new WeiboDetailCrawler("/Users/alibaba/Desktop/zhubajie",
                "/Users/alibaba/Desktop/zhubajie/detail");
            crawler.setFilePath("/Users/alibaba/Desktop/zhubajie-data/piecework/");
            crawler.setFileTitle("piecework");
            crawler.setThreads(30);
            //System.out.println("用户\t" + "用户姓名\t" + "能力\t" + "交稿时间\t" + "参与编号\t" + "来源\t"+ "雇主浏览状况\t" + "是否合格\t");
            for (String detailUrl : detailUrlList) {
                String[] strlist = detailUrl.split(";");
                String url = strlist[0];
                Integer count = Integer.parseInt(strlist[1]);
                String prefix = "/Users/alibaba/Desktop/zhubajie-data/piecework/" + "piecework";

                if (0 == count) {
                    String thisurl = url;
                    String totalString = prefix
                                         + thisurl.substring(25).replace("/", "-")
                                             .replace(".html", "") + ".txt";
                    File f = new File(totalString);
                    if (f.exists()) {
                        System.out.println("文件存在");
                        continue;
                    }
                    System.out.println("单页:"+totalString);
                    crawler.addSeed(thisurl);
                } else {
                    for (int i = 1; i <= count; i++) {
                        String thisurl = url + i + ".html";
                        String totalString = prefix
                                             + thisurl.substring(25).replace("/", "-")
                                                 .replace(".html", "") + ".txt";
                        File f = new File(totalString);
                        if (f.exists()) {
                            System.out.println("文件存在");
                            continue;
                        }
                        System.out.println("多页:" + totalString);
                        crawler.addSeed(thisurl);
                    }
                }

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
     * Getter method for property <tt>fileTitle</tt>.
     * 
     * @return property value of fileTitle
     */
    public String getFileTitle() {
        return fileTitle;
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
     * Setter method for property <tt>fileTitle</tt>.
     * 
     * @param fileTitle value to be assigned to property fileTitle
     */
    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

}
