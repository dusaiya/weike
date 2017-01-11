package com.ict.nasc.weike.webcontrol.model;
/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */

/**
 * 
 * @author xueye.duanxy
 * @version $Id: FirstCatagory.java, v 0.1 2016-3-16 下午8:26:18  Exp $
 */
public enum FirstCatagory {

    /****/
    品牌设计("品牌设计", "http://task.zbj.com/o-ppsj/"),
    /****/
    网站建设("网站建设", "http://task.zbj.com/o-wzkf/"),
    /****/
    营销推广("营销推广", "http://task.zbj.com/o-yxtg/"),
    /****/
    电商服务("电商服务", "http://task.zbj.com/o-wdfw/"),
    /****/
    移动应用开发("移动应用开发", "http://task.zbj.com/o-yidongkf/"),
    /****/
    文案策划("文案策划", "http://task.zbj.com/o-paperwork/"),
    /****/
    起名取名("起名取名", "http://task.zbj.com/o-cyqm/"),
    /****/
    软件开发("软件开发", "http://task.zbj.com/o-rjkf/"),
    /****/
    UI设计("UI设计", "http://task.zbj.com/o-uisheji/"),
    /****/
    装修服务("装修服务", "http://task.zbj.com/o-zhuangxiu/"),
    /****/
    动画漫画("动画漫画", "http://task.zbj.com/o-dhmh/"),
    /****/
    工业设计("工业设计", "http://task.zbj.com/o-gongyesj/"),
    /****/
    影视制作("影视制作", "http://task.zbj.com/o-video/"),
    /****/
    摄影摄像("摄影摄像", "http://task.zbj.com/o-sysx/"),
    /****/
    游戏("游戏", "http://task.zbj.com/o-game/"),
    /****/
    工程设计咨询("工程设计咨询", "http://task.zbj.com/o-gc/"),
    /****/
    服装配饰设计("服装配饰设计", "http://task.zbj.com/o-fzpssj/"),
    /****/
    真人服务("真人服务", "http://task.zbj.com/o-zrfw/"),
    /****/
    企业服务("企业服务", "http://task.zbj.com/o-qyfw/"),
    /****/
    咨询问答("咨询问答", "http://task.zbj.com/o-consult/"),
    /****/
    技术服务("技术服务", "http://task.zbj.com/o-jsfw/"),
    /****/
    市场调查("市场调查", "http://task.zbj.com/o-shichangdc/"),
    /****/
    印刷服务("印刷服务", "http://task.zbj.com/o-ys/"),
    /****/
    音乐制作("音乐制作", "http://task.zbj.com/o-music/");
    /****/
    private String catagory;
    /****/
    private String url;

    /**
     * 构造函数
     * @param catagory
     * @param url
     */
    private FirstCatagory(String catagory, String url) {
        this.catagory = catagory;
        this.url = url;
    }

    /**
     * 根据url
     * 
     * @param url
     * @return FirstCatagory
     */
    public static FirstCatagory getByUrl(String url) {
        for (FirstCatagory catagory : values()) {
            if (catagory.getUrl().equals(url)) {
                return catagory;
            }
        }
        return null;
    }

    /**
     * 根据catagory
     * 
     * @param catagoryStr
     * @return FirstCatagory
     */
    public static FirstCatagory getByCatagory(String catagoryStr) {
        for (FirstCatagory catagory : values()) {
            if (catagory.getCatagory().equals(catagoryStr)) {
                return catagory;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>catagory</tt>.
     * 
     * @return property value of catagory
     */
    public String getCatagory() {
        return catagory;
    }

    /**
     * Getter method for property <tt>url</tt>.
     * 
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>catagory</tt>.
     * 
     * @param catagory value to be assigned to property catagory
     */
    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    /**
     * Setter method for property <tt>url</tt>.
     * 
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
