/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.model;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: WeikePaymentType.java, v 0.1 2015-12-2 下午8:30:14  Exp $
 */
public enum WeikePaymentType {

    /** 计件 **/
    piece("piece", "计件"),

    /**微博粉丝数*/
    weiboFans("weiboFans", "微博粉丝数"),

    /** 比稿 比赛 **/
    competition("competition", "比赛"),

    /** 比稿 一人独享模式 头名独得**/
    topOnly("topOnly", "一人独得赏金"),

    /** 招标 一人做一人分 **/
    solo("solo", "独自完成");

    /**code**/
    private String code;

    /**描述**/
    private String desc;

    /**
     * 构造函数
     * @param code
     * @param desc
     */
    WeikePaymentType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     * 
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>code</tt>.
     * 
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Setter method for property <tt>desc</tt>.
     * 
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
