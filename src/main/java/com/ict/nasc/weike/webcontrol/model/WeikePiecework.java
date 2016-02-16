/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 交稿内容
 * @author xueye.duanxy
 * @version $Id: WeikePiecework.java, v 0.1 2015-12-4 上午9:49:03  Exp $
 */
public class WeikePiecework {
    /**稿件id**/
    private String  pieceworkId;
    /**当前作业提交页面**/
    private String  curTaskUrl;
    /**任务Id**/
    private String  taskId;
    /**用户id**/
    private String  userId;
    /**用户姓名**/
    private String  userName;
    /**用户连接**/
    private String  userUrl;
    /**用户等级**/
    private String  abilityLevel;
    /**能力值**/
    private String  abilityValue;
    /**能力字符串**/
    private String  abilityStr;
    /**提交时间**/
    private String  summitTime;
    /**用户来源**/
    private String  userSource;
    /**雇主浏览状态**/
    private String  readStatus;
    /**质量情况**/
    private String  workQuality;
    /**内容**/
    private String  content;

    /** --------------------------------------用户标示  START----------------------------------------------*/
    /**皇冠服务商、 钻石服务商、金牌服务商、银牌服务商、通用服务商 **/
    private String  specialCustomerType;
    /**服务商保障计划**/
    private boolean fuwubao         = false;
    /**推荐服务商**/
    private String  recommendCustomer;
    /**推荐雇佣标签**/
    private boolean recommentEmploy = false;

    /** --------------------------------------用户标示  END  ----------------------------------------------*/
    /** 
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * Getter method for property <tt>pieceworkId</tt>.
     * 
     * @return property value of pieceworkId
     */
    public String getPieceworkId() {
        return pieceworkId;
    }

    /**
     * Getter method for property <tt>curTaskUrl</tt>.
     * 
     * @return property value of curTaskUrl
     */
    public String getCurTaskUrl() {
        return curTaskUrl;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     * 
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Getter method for property <tt>userUrl</tt>.
     * 
     * @return property value of userUrl
     */
    public String getUserUrl() {
        return userUrl;
    }

    /**
     * Getter method for property <tt>abilityLevel</tt>.
     * 
     * @return property value of abilityLevel
     */
    public String getAbilityLevel() {
        return abilityLevel;
    }

    /**
     * Getter method for property <tt>abilityValue</tt>.
     * 
     * @return property value of abilityValue
     */
    public String getAbilityValue() {
        return abilityValue;
    }

    /**
     * Getter method for property <tt>summitTime</tt>.
     * 
     * @return property value of summitTime
     */
    public String getSummitTime() {
        return summitTime;
    }

    /**
     * Getter method for property <tt>userSource</tt>.
     * 
     * @return property value of userSource
     */
    public String getUserSource() {
        return userSource;
    }

    /**
     * Getter method for property <tt>readStatus</tt>.
     * 
     * @return property value of readStatus
     */
    public String getReadStatus() {
        return readStatus;
    }

    /**
     * Getter method for property <tt>workQuality</tt>.
     * 
     * @return property value of workQuality
     */
    public String getWorkQuality() {
        return workQuality;
    }

    /**
     * Getter method for property <tt>content</tt>.
     * 
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter method for property <tt>fuwubao</tt>.
     * 
     * @return property value of fuwubao
     */
    public boolean getFuwubao() {
        return fuwubao;
    }

    /**
     * Getter method for property <tt>recommendCustomer</tt>.
     * 
     * @return property value of recommendCustomer
     */
    public String getRecommendCustomer() {
        return recommendCustomer;
    }

    /**
     * Setter method for property <tt>pieceworkId</tt>.
     * 
     * @param pieceworkId value to be assigned to property pieceworkId
     */
    public void setPieceworkId(String pieceworkId) {
        this.pieceworkId = pieceworkId;
    }

    /**
     * Setter method for property <tt>curTaskUrl</tt>.
     * 
     * @param curTaskUrl value to be assigned to property curTaskUrl
     */
    public void setCurTaskUrl(String curTaskUrl) {
        this.curTaskUrl = curTaskUrl;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     * 
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Setter method for property <tt>userUrl</tt>.
     * 
     * @param userUrl value to be assigned to property userUrl
     */
    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    /**
     * Setter method for property <tt>abilityLevel</tt>.
     * 
     * @param abilityLevel value to be assigned to property abilityLevel
     */
    public void setAbilityLevel(String abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    /**
     * Setter method for property <tt>abilityValue</tt>.
     * 
     * @param abilityValue value to be assigned to property abilityValue
     */
    public void setAbilityValue(String abilityValue) {
        this.abilityValue = abilityValue;
    }

    /**
     * Setter method for property <tt>summitTime</tt>.
     * 
     * @param summitTime value to be assigned to property summitTime
     */
    public void setSummitTime(String summitTime) {
        this.summitTime = summitTime;
    }

    /**
     * Setter method for property <tt>userSource</tt>.
     * 
     * @param userSource value to be assigned to property userSource
     */
    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    /**
     * Setter method for property <tt>readStatus</tt>.
     * 
     * @param readStatus value to be assigned to property readStatus
     */
    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    /**
     * Setter method for property <tt>workQuality</tt>.
     * 
     * @param workQuality value to be assigned to property workQuality
     */
    public void setWorkQuality(String workQuality) {
        this.workQuality = workQuality;
    }

    /**
     * Setter method for property <tt>content</tt>.
     * 
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Setter method for property <tt>fuwubao</tt>.
     * 
     * @param fuwubao value to be assigned to property fuwubao
     */
    public void setFuwubao(boolean fuwubao) {
        this.fuwubao = fuwubao;
    }

    /**
     * Setter method for property <tt>recommendCustomer</tt>.
     * 
     * @param recommendCustomer value to be assigned to property recommendCustomer
     */
    public void setRecommendCustomer(String recommendCustomer) {
        this.recommendCustomer = recommendCustomer;
    }

    /**
     * Getter method for property <tt>specialCustomerType</tt>.
     * 
     * @return property value of specialCustomerType
     */
    public String getSpecialCustomerType() {
        return specialCustomerType;
    }

    /**
     * Setter method for property <tt>specialCustomerType</tt>.
     * 
     * @param specialCustomerType value to be assigned to property specialCustomerType
     */
    public void setSpecialCustomerType(String specialCustomerType) {
        this.specialCustomerType = specialCustomerType;
    }

    /**
     * Getter method for property <tt>userName</tt>.
     * 
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * 
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for property <tt>taskId</tt>.
     * 
     * @return property value of taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Setter method for property <tt>taskId</tt>.
     * 
     * @param taskId value to be assigned to property taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter method for property <tt>abilityStr</tt>.
     * 
     * @return property value of abilityStr
     */
    public String getAbilityStr() {
        return abilityStr;
    }

    /**
     * Setter method for property <tt>abilityStr</tt>.
     * 
     * @param abilityStr value to be assigned to property abilityStr
     */
    public void setAbilityStr(String abilityStr) {
        this.abilityStr = abilityStr;
    }

    /**
     * Getter method for property <tt>recommentEmploy</tt>.
     * 
     * @return property value of recommentEmploy
     */
    public boolean isRecommentEmploy() {
        return recommentEmploy;
    }

    /**
     * Setter method for property <tt>recommentEmploy</tt>.
     * 
     * @param recommentEmploy value to be assigned to property recommentEmploy
     */
    public void setRecommentEmploy(boolean recommentEmploy) {
        this.recommentEmploy = recommentEmploy;
    }

}
