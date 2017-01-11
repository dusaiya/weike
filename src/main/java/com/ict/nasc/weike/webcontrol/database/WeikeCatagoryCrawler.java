package com.ict.nasc.weike.webcontrol.database;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

import com.ict.nasc.weike.webcontrol.model.FirstCatagory;

/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */

/**
 * 
 * @author xueye.duanxy
 * @version $Id: WeikeCatagoryCrawler.java, v 0.1 2016-3-16 下午8:42:52  Exp $
 */
public class WeikeCatagoryCrawler extends DeepCrawler {
    /**
     * 构造类
     * @param crawlPath
     * @throws Exception
     */
    public WeikeCatagoryCrawler(String crawlPath) throws Exception {
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
        try {
            //实际处理
            fileOutput(page);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * 
     * @param page
     * @return
     */
    private Links fileOutput(Page page) {
        try {
            String taskLink = page.getUrl();
            Elements dls = page.getDoc().select("div.ui-dropdown-level2").select("a");
            //System.out.println(taskLink + ";catagory:  " + FirstCatagory.getByUrl(taskLink));
            //StringBuilder sql = new StringBuilder();
            //sql.append("update task set first_catagory = '" + FirstCatagory.getByUrl(taskLink)
            //           + "' where second_catagory in (");
            for (Element dl : dls) {
                //sql.append("'" + dl.text() + "',");
                System.out.println(dl.text() + "(\"" + dl.text() + "\", \""
                                   + FirstCatagory.getByUrl(taskLink) + "\", FirstCatagory."
                                   + FirstCatagory.getByUrl(taskLink) +"),");
            }
            //sql.append("'');");
            //System.out.println(sql.toString());
        } catch (Exception e) {
            System.out.println(page.getUrl() + "【处理异常】" + e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        try {
            WeikeCatagoryCrawler crawler = new WeikeCatagoryCrawler(
                "/Users/alibaba/Desktop/zhubajie");
            crawler.setThreads(4);
            for (FirstCatagory catagory : FirstCatagory.values()) {
                crawler.addSeed(catagory.getUrl());
            }
            crawler.start(1);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
