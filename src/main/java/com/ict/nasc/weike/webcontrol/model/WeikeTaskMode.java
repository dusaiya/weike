/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.model;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: WeikeTaskMode.java, v 0.1 2015-12-2 上午11:32:18  Exp $
 */
public enum WeikeTaskMode {
    /**比稿**/
    competition("competition", "比稿"),

    /**计件**/
    count("count", "计件"),

    /**粉丝数计算**/
    weiboFans("weiboFans", "微博粉丝数"),

    /**招标**/
    inviteTender("inviteTender", "招标"),

    /** 未知类型**/
    unknow("unknow", "未知");

    /**code**/
    private String code;

    /**描述**/
    private String desc;

    /**
     * 构造函数
     * @param code
     * @param desc
     */
    WeikeTaskMode(String code, String desc) {
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
