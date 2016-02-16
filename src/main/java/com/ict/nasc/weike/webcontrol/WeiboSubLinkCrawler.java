/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ict.nasc.weike.webcontrol.tools.WeiboSubLinkCrawlerAddTool;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: zhubajie.java, v 0.1 2015-11-10 上午10:01:03  Exp $
 */
public class WeiboSubLinkCrawler extends DeepCrawler {

    /**
     * subListFile
     */
    private List<String> subList;

    /**
     * 构造类
     * @param crawlPath
     * @throws Exception
     */
    public WeiboSubLinkCrawler(String crawlPath) throws Exception {
        super(crawlPath);
        subList = new ArrayList<String>();
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
        fileOutput(page);
        return null;
    }

    /**
     * 
     * @param page
     * @param output 输出流
     * @return
     * @throws IOException 
     */
    private Links fileOutput(Page page) {
        Elements dls = page.getDoc().select("div.pagination").select("a");
        if (!CollectionUtils.isEmpty(dls)) {
            Element dl = dls.get(dls.size() - 2);
            subList.add("http://task.zhubajie.com/" + dl.attr("href") + "; " + dl.text());
        } else {
            subList.add(page.getUrl() + ";" + "0");
        }
        System.out.println("page：" + page.getUrl() + " finished");
        return null;
    }

    public static void main(String[] args) {
        BufferedWriter output = null;
        try {
            WeiboSubLinkCrawler crawler = new WeiboSubLinkCrawler("/Users/alibaba/Desktop/zhubajie");
            crawler.setThreads(20);
            WeiboSubLinkCrawlerAddTool.addCrawler(crawler);
            crawler.start(1);

            File f = new File("/Users/alibaba/Desktop/zhubajie-data/" + "subLink.txt");
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
            if (!CollectionUtils.isEmpty(crawler.getSubList())) {
                for (String subLink : crawler.getSubList()) {
                    output.write(subLink);
                    output.newLine();
                }
            }

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

    }

    /**
     * Getter method for property <tt>subList</tt>.
     * 
     * @return property value of subList
     */
    public List<String> getSubList() {
        return subList;
    }

    /**
     * Setter method for property <tt>subList</tt>.
     * 
     * @param subList value to be assigned to property subList
     */
    public void setSubList(List<String> subList) {
        this.subList = subList;
    }

}
