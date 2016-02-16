/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/** 
 * 任务模型
 * @author xueye.duanxy
 * @version $Id: WeikeTask.java, v 0.1 2015-12-1 下午8:31:24  Exp $
 */
public class WeikeTask {
    /** 任务id **/
    private String  taskId;
    /** 任务标题 **/
    private String  taskTitle;
    /** 任务发布时间 **/
    private String  taskReleaseDate;
    /** 任务连接 **/
    private String  taskLink;
    /** 发布人名称 **/
    private String  releaseUserName;
    /** 发布人id **/
    private String  releaseUserId;
    /** 发布人连接 **/
    private String  releaseUserUrl;
    /** 总金额(分) **/
    private Integer totalAmt;
    /** 托管金额(分) **/
    private Integer guaranteedAmt;
    /** 任务模式 {@link WeikeTaskMode} **/
    private String  taskMode;
    /** 特殊标签 八戒悬赏/ 八戒众帮 **/
    private String  specialTag;
    /** 支付模式 **/
    private String  paymentType;
    /** 交易模式细则 **/
    private String  tradeModeDetail;
    /** 发布省份 **/
    private String  releaseProvince;
    /** 发布城市 **/
    private String  releaseCity;
    /** 发布来源 **/
    private String  releaseSource;
    /** 任务状态 **/
    private String  taskStatus;
    /** 【服务商要求】是否要求手机认证 **/
    private boolean mobileAuthen         = false;
    /** 【服务商要求】是否要求实名认证 **/
    private boolean realNameAuthen       = false;
    /** 【服务商要求】是否要求保证完成 **/
    private boolean completementSecurity = false;
    /** 【服务商要求】是否要求保证原创 **/
    private boolean originWork           = false;
    /** 备注 **/
    private String  remark;
    /** 子URL **/
    private String  subUrlPrefix;
    /** 子url训练集, 如果count=0 表示没有子页面, subUrl = taskUrl **/
    private Integer subCount;
    /** 交易细则 **/
    private String  paymentDetail;
    /** --------------------------------------任务归属类目  START----------------------------------------------*/
    /** 一级类目 **/
    private String  firstCatagory;
    /** 二级类目 **/
    private String  secondCatagory;
    /** --------------------------------------任务归属类目  END  ----------------------------------------------*/
    /** --------------------------------------计件类型属性  START----------------------------------------------*/
    /** 计件价格(分) **/
    private Integer pieceworkAmt;
    /** 已选数量 **/
    private Integer selectedCount;
    /** 还需数量 **/
    private Integer needCount;
    /** 每名服务商最多交数量 **/
    private Integer countPerWorker;
    /** --------------------------------------计件类型属性  END  ----------------------------------------------*/
    /** --------------------------------------任务流程属性  START----------------------------------------------*/
    /** 完整任务信息 **/
    private String  processInfo;
    //通用流程
    /** 发布需求状态 **/
    private String  pReleaseStatus;
    /** 发布需求日期 **/
    private String  pReleaseDate;
    /** 服务商(群众)提交信息/工作过程状态 **/
    private String  pCrowdSubmitStatus;
    /** 服务商(群众)提交信息/工作 时间 **/
    private String  pCrowdSubmitDate;
    /** 雇主选择成果或设置计件类型标准或招标团队 的状态 **/
    private String  pEmployerChooseStatus;
    /** 雇主选择成果或设置计件类型标准或招标团队 的时间 **/
    private String  pEmployerChooseDate;
    /** 酬劳支付(交易完成或验收付款) 状态 **/
    private String  pPaymentCompleteStatus;
    /** 酬劳支付(交易完成或验收付款) 日期 **/
    private String  pPaymentCompleteDate;
    //中标信息
    /** 中标公示 状态 **/
    private String  pAnnounceChoiceStatus;
    /** 中标公示 时间 **/
    private String  pAnnounceChoiceDate;
    /** 招标模式下 指定的工作团队的工作 状态 **/
    private String  pAppointWorkStatus;
    /** 招标模式下 指定的工作团队的工作 时间 **/
    private String  pAppointWorkDate;
    /** 招标、比稿模式下 评价 状态 **/
    private String  pEvaluationStatus;
    /** 招标、比稿模式下 评价 时间 **/
    private String  pEvaluationDate;

    /** --------------------------------------任务流程属性  END  ----------------------------------------------*/

    /** 
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
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
     * Getter method for property <tt>taskTitle</tt>.
     * 
     * @return property value of taskTitle
     */
    public String getTaskTitle() {
        return taskTitle;
    }

    /**
     * Getter method for property <tt>taskReleaseDate</tt>.
     * 
     * @return property value of taskReleaseDate
     */
    public String getTaskReleaseDate() {
        return taskReleaseDate;
    }

    /**
     * Getter method for property <tt>taskLink</tt>.
     * 
     * @return property value of taskLink
     */
    public String getTaskLink() {
        return taskLink;
    }

    /**
     * Getter method for property <tt>releaseUserName</tt>.
     * 
     * @return property value of releaseUserName
     */
    public String getReleaseUserName() {
        return releaseUserName;
    }

    /**
     * Getter method for property <tt>releaseUserId</tt>.
     * 
     * @return property value of releaseUserId
     */
    public String getReleaseUserId() {
        return releaseUserId;
    }

    /**
     * Getter method for property <tt>releaseUserUrl</tt>.
     * 
     * @return property value of releaseUserUrl
     */
    public String getReleaseUserUrl() {
        return releaseUserUrl;
    }

    /**
     * Getter method for property <tt>totalAmt</tt>.
     * 
     * @return property value of totalAmt
     */
    public Integer getTotalAmt() {
        return totalAmt;
    }

    /**
     * Getter method for property <tt>guaranteedAmt</tt>.
     * 
     * @return property value of guaranteedAmt
     */
    public Integer getGuaranteedAmt() {
        return guaranteedAmt;
    }

    /**
     * Getter method for property <tt>taskMode</tt>.
     * 
     * @return property value of taskMode
     */
    public String getTaskMode() {
        return taskMode;
    }

    /**
     * Getter method for property <tt>specialTag</tt>.
     * 
     * @return property value of specialTag
     */
    public String getSpecialTag() {
        return specialTag;
    }

    /**
     * Getter method for property <tt>paymentType</tt>.
     * 
     * @return property value of paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Getter method for property <tt>tradeModeDetail</tt>.
     * 
     * @return property value of tradeModeDetail
     */
    public String getTradeModeDetail() {
        return tradeModeDetail;
    }

    /**
     * Getter method for property <tt>releaseProvince</tt>.
     * 
     * @return property value of releaseProvince
     */
    public String getReleaseProvince() {
        return releaseProvince;
    }

    /**
     * Getter method for property <tt>releaseCity</tt>.
     * 
     * @return property value of releaseCity
     */
    public String getReleaseCity() {
        return releaseCity;
    }

    /**
     * Getter method for property <tt>releaseSource</tt>.
     * 
     * @return property value of releaseSource
     */
    public String getReleaseSource() {
        return releaseSource;
    }

    /**
     * Getter method for property <tt>taskStatus</tt>.
     * 
     * @return property value of taskStatus
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * Getter method for property <tt>mobileAuthen</tt>.
     * 
     * @return property value of mobileAuthen
     */
    public boolean isMobileAuthen() {
        return mobileAuthen;
    }

    /**
     * Getter method for property <tt>realNameAuthen</tt>.
     * 
     * @return property value of realNameAuthen
     */
    public boolean isRealNameAuthen() {
        return realNameAuthen;
    }

    /**
     * Getter method for property <tt>completementSecurity</tt>.
     * 
     * @return property value of completementSecurity
     */
    public boolean isCompletementSecurity() {
        return completementSecurity;
    }

    /**
     * Getter method for property <tt>originWork</tt>.
     * 
     * @return property value of originWork
     */
    public boolean isOriginWork() {
        return originWork;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     * 
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Getter method for property <tt>subUrlPrefix</tt>.
     * 
     * @return property value of subUrlPrefix
     */
    public String getSubUrlPrefix() {
        return subUrlPrefix;
    }

    /**
     * Getter method for property <tt>subCount</tt>.
     * 
     * @return property value of subCount
     */
    public Integer getSubCount() {
        return subCount;
    }

    /**
     * Getter method for property <tt>paymentDetail</tt>.
     * 
     * @return property value of paymentDetail
     */
    public String getPaymentDetail() {
        return paymentDetail;
    }

    /**
     * Getter method for property <tt>firstCatagory</tt>.
     * 
     * @return property value of firstCatagory
     */
    public String getFirstCatagory() {
        return firstCatagory;
    }

    /**
     * Getter method for property <tt>secondCatagory</tt>.
     * 
     * @return property value of secondCatagory
     */
    public String getSecondCatagory() {
        return secondCatagory;
    }

    /**
     * Getter method for property <tt>pieceworkAmt</tt>.
     * 
     * @return property value of pieceworkAmt
     */
    public Integer getPieceworkAmt() {
        return pieceworkAmt;
    }

    /**
     * Getter method for property <tt>selectedCount</tt>.
     * 
     * @return property value of selectedCount
     */
    public Integer getSelectedCount() {
        return selectedCount;
    }

    /**
     * Getter method for property <tt>needCount</tt>.
     * 
     * @return property value of needCount
     */
    public Integer getNeedCount() {
        return needCount;
    }

    /**
     * Getter method for property <tt>countPerWorker</tt>.
     * 
     * @return property value of countPerWorker
     */
    public Integer getCountPerWorker() {
        return countPerWorker;
    }

    /**
     * Getter method for property <tt>processInfo</tt>.
     * 
     * @return property value of processInfo
     */
    public String getProcessInfo() {
        return processInfo;
    }

    /**
     * Getter method for property <tt>pReleaseStatus</tt>.
     * 
     * @return property value of pReleaseStatus
     */
    public String getpReleaseStatus() {
        return pReleaseStatus;
    }

    /**
     * Getter method for property <tt>pReleaseDate</tt>.
     * 
     * @return property value of pReleaseDate
     */
    public String getpReleaseDate() {
        return pReleaseDate;
    }

    /**
     * Getter method for property <tt>pCrowdSubmitStatus</tt>.
     * 
     * @return property value of pCrowdSubmitStatus
     */
    public String getpCrowdSubmitStatus() {
        return pCrowdSubmitStatus;
    }

    /**
     * Getter method for property <tt>pCrowdSubmitDate</tt>.
     * 
     * @return property value of pCrowdSubmitDate
     */
    public String getpCrowdSubmitDate() {
        return pCrowdSubmitDate;
    }

    /**
     * Getter method for property <tt>pEmployerChooseStatus</tt>.
     * 
     * @return property value of pEmployerChooseStatus
     */
    public String getpEmployerChooseStatus() {
        return pEmployerChooseStatus;
    }

    /**
     * Getter method for property <tt>pEmployerChooseDate</tt>.
     * 
     * @return property value of pEmployerChooseDate
     */
    public String getpEmployerChooseDate() {
        return pEmployerChooseDate;
    }

    /**
     * Getter method for property <tt>pPaymentCompleteStatus</tt>.
     * 
     * @return property value of pPaymentCompleteStatus
     */
    public String getpPaymentCompleteStatus() {
        return pPaymentCompleteStatus;
    }

    /**
     * Getter method for property <tt>pPaymentCompleteDate</tt>.
     * 
     * @return property value of pPaymentCompleteDate
     */
    public String getpPaymentCompleteDate() {
        return pPaymentCompleteDate;
    }

    /**
     * Getter method for property <tt>pAnnounceChoiceStatus</tt>.
     * 
     * @return property value of pAnnounceChoiceStatus
     */
    public String getpAnnounceChoiceStatus() {
        return pAnnounceChoiceStatus;
    }

    /**
     * Getter method for property <tt>pAnnounceChoiceDate</tt>.
     * 
     * @return property value of pAnnounceChoiceDate
     */
    public String getpAnnounceChoiceDate() {
        return pAnnounceChoiceDate;
    }

    /**
     * Getter method for property <tt>pAppointWorkStatus</tt>.
     * 
     * @return property value of pAppointWorkStatus
     */
    public String getpAppointWorkStatus() {
        return pAppointWorkStatus;
    }

    /**
     * Getter method for property <tt>pAppointWorkDate</tt>.
     * 
     * @return property value of pAppointWorkDate
     */
    public String getpAppointWorkDate() {
        return pAppointWorkDate;
    }

    /**
     * Getter method for property <tt>pEvaluationStatus</tt>.
     * 
     * @return property value of pEvaluationStatus
     */
    public String getpEvaluationStatus() {
        return pEvaluationStatus;
    }

    /**
     * Getter method for property <tt>pEvaluationDate</tt>.
     * 
     * @return property value of pEvaluationDate
     */
    public String getpEvaluationDate() {
        return pEvaluationDate;
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
     * Setter method for property <tt>taskTitle</tt>.
     * 
     * @param taskTitle value to be assigned to property taskTitle
     */
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    /**
     * Setter method for property <tt>taskReleaseDate</tt>.
     * 
     * @param taskReleaseDate value to be assigned to property taskReleaseDate
     */
    public void setTaskReleaseDate(String taskReleaseDate) {
        this.taskReleaseDate = taskReleaseDate;
    }

    /**
     * Setter method for property <tt>taskLink</tt>.
     * 
     * @param taskLink value to be assigned to property taskLink
     */
    public void setTaskLink(String taskLink) {
        this.taskLink = taskLink;
    }

    /**
     * Setter method for property <tt>releaseUserName</tt>.
     * 
     * @param releaseUserName value to be assigned to property releaseUserName
     */
    public void setReleaseUserName(String releaseUserName) {
        this.releaseUserName = releaseUserName;
    }

    /**
     * Setter method for property <tt>releaseUserId</tt>.
     * 
     * @param releaseUserId value to be assigned to property releaseUserId
     */
    public void setReleaseUserId(String releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    /**
     * Setter method for property <tt>releaseUserUrl</tt>.
     * 
     * @param releaseUserUrl value to be assigned to property releaseUserUrl
     */
    public void setReleaseUserUrl(String releaseUserUrl) {
        this.releaseUserUrl = releaseUserUrl;
    }

    /**
     * Setter method for property <tt>totalAmt</tt>.
     * 
     * @param totalAmt value to be assigned to property totalAmt
     */
    public void setTotalAmt(Integer totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * Setter method for property <tt>guaranteedAmt</tt>.
     * 
     * @param guaranteedAmt value to be assigned to property guaranteedAmt
     */
    public void setGuaranteedAmt(Integer guaranteedAmt) {
        this.guaranteedAmt = guaranteedAmt;
    }

    /**
     * Setter method for property <tt>taskMode</tt>.
     * 
     * @param taskMode value to be assigned to property taskMode
     */
    public void setTaskMode(String taskMode) {
        this.taskMode = taskMode;
    }

    /**
     * Setter method for property <tt>specialTag</tt>.
     * 
     * @param specialTag value to be assigned to property specialTag
     */
    public void setSpecialTag(String specialTag) {
        this.specialTag = specialTag;
    }

    /**
     * Setter method for property <tt>paymentType</tt>.
     * 
     * @param paymentType value to be assigned to property paymentType
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Setter method for property <tt>tradeModeDetail</tt>.
     * 
     * @param tradeModeDetail value to be assigned to property tradeModeDetail
     */
    public void setTradeModeDetail(String tradeModeDetail) {
        this.tradeModeDetail = tradeModeDetail;
    }

    /**
     * Setter method for property <tt>releaseProvince</tt>.
     * 
     * @param releaseProvince value to be assigned to property releaseProvince
     */
    public void setReleaseProvince(String releaseProvince) {
        this.releaseProvince = releaseProvince;
    }

    /**
     * Setter method for property <tt>releaseCity</tt>.
     * 
     * @param releaseCity value to be assigned to property releaseCity
     */
    public void setReleaseCity(String releaseCity) {
        this.releaseCity = releaseCity;
    }

    /**
     * Setter method for property <tt>releaseSource</tt>.
     * 
     * @param releaseSource value to be assigned to property releaseSource
     */
    public void setReleaseSource(String releaseSource) {
        this.releaseSource = releaseSource;
    }

    /**
     * Setter method for property <tt>taskStatus</tt>.
     * 
     * @param taskStatus value to be assigned to property taskStatus
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * Setter method for property <tt>mobileAuthen</tt>.
     * 
     * @param mobileAuthen value to be assigned to property mobileAuthen
     */
    public void setMobileAuthen(boolean mobileAuthen) {
        this.mobileAuthen = mobileAuthen;
    }

    /**
     * Setter method for property <tt>realNameAuthen</tt>.
     * 
     * @param realNameAuthen value to be assigned to property realNameAuthen
     */
    public void setRealNameAuthen(boolean realNameAuthen) {
        this.realNameAuthen = realNameAuthen;
    }

    /**
     * Setter method for property <tt>completementSecurity</tt>.
     * 
     * @param completementSecurity value to be assigned to property completementSecurity
     */
    public void setCompletementSecurity(boolean completementSecurity) {
        this.completementSecurity = completementSecurity;
    }

    /**
     * Setter method for property <tt>originWork</tt>.
     * 
     * @param originWork value to be assigned to property originWork
     */
    public void setOriginWork(boolean originWork) {
        this.originWork = originWork;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     * 
     * @param remark value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Setter method for property <tt>subUrlPrefix</tt>.
     * 
     * @param subUrlPrefix value to be assigned to property subUrlPrefix
     */
    public void setSubUrlPrefix(String subUrlPrefix) {
        this.subUrlPrefix = subUrlPrefix;
    }

    /**
     * Setter method for property <tt>subCount</tt>.
     * 
     * @param subCount value to be assigned to property subCount
     */
    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    /**
     * Setter method for property <tt>paymentDetail</tt>.
     * 
     * @param paymentDetail value to be assigned to property paymentDetail
     */
    public void setPaymentDetail(String paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    /**
     * Setter method for property <tt>firstCatagory</tt>.
     * 
     * @param firstCatagory value to be assigned to property firstCatagory
     */
    public void setFirstCatagory(String firstCatagory) {
        this.firstCatagory = firstCatagory;
    }

    /**
     * Setter method for property <tt>secondCatagory</tt>.
     * 
     * @param secondCatagory value to be assigned to property secondCatagory
     */
    public void setSecondCatagory(String secondCatagory) {
        this.secondCatagory = secondCatagory;
    }

    /**
     * Setter method for property <tt>pieceworkAmt</tt>.
     * 
     * @param pieceworkAmt value to be assigned to property pieceworkAmt
     */
    public void setPieceworkAmt(Integer pieceworkAmt) {
        this.pieceworkAmt = pieceworkAmt;
    }

    /**
     * Setter method for property <tt>selectedCount</tt>.
     * 
     * @param selectedCount value to be assigned to property selectedCount
     */
    public void setSelectedCount(Integer selectedCount) {
        this.selectedCount = selectedCount;
    }

    /**
     * Setter method for property <tt>needCount</tt>.
     * 
     * @param needCount value to be assigned to property needCount
     */
    public void setNeedCount(Integer needCount) {
        this.needCount = needCount;
    }

    /**
     * Setter method for property <tt>countPerWorker</tt>.
     * 
     * @param countPerWorker value to be assigned to property countPerWorker
     */
    public void setCountPerWorker(Integer countPerWorker) {
        this.countPerWorker = countPerWorker;
    }

    /**
     * Setter method for property <tt>processInfo</tt>.
     * 
     * @param processInfo value to be assigned to property processInfo
     */
    public void setProcessInfo(String processInfo) {
        this.processInfo = processInfo;
    }

    /**
     * Setter method for property <tt>pReleaseStatus</tt>.
     * 
     * @param pReleaseStatus value to be assigned to property pReleaseStatus
     */
    public void setpReleaseStatus(String pReleaseStatus) {
        this.pReleaseStatus = pReleaseStatus;
    }

    /**
     * Setter method for property <tt>pReleaseDate</tt>.
     * 
     * @param pReleaseDate value to be assigned to property pReleaseDate
     */
    public void setpReleaseDate(String pReleaseDate) {
        this.pReleaseDate = pReleaseDate;
    }

    /**
     * Setter method for property <tt>pCrowdSubmitStatus</tt>.
     * 
     * @param pCrowdSubmitStatus value to be assigned to property pCrowdSubmitStatus
     */
    public void setpCrowdSubmitStatus(String pCrowdSubmitStatus) {
        this.pCrowdSubmitStatus = pCrowdSubmitStatus;
    }

    /**
     * Setter method for property <tt>pCrowdSubmitDate</tt>.
     * 
     * @param pCrowdSubmitDate value to be assigned to property pCrowdSubmitDate
     */
    public void setpCrowdSubmitDate(String pCrowdSubmitDate) {
        this.pCrowdSubmitDate = pCrowdSubmitDate;
    }

    /**
     * Setter method for property <tt>pEmployerChooseStatus</tt>.
     * 
     * @param pEmployerChooseStatus value to be assigned to property pEmployerChooseStatus
     */
    public void setpEmployerChooseStatus(String pEmployerChooseStatus) {
        this.pEmployerChooseStatus = pEmployerChooseStatus;
    }

    /**
     * Setter method for property <tt>pEmployerChooseDate</tt>.
     * 
     * @param pEmployerChooseDate value to be assigned to property pEmployerChooseDate
     */
    public void setpEmployerChooseDate(String pEmployerChooseDate) {
        this.pEmployerChooseDate = pEmployerChooseDate;
    }

    /**
     * Setter method for property <tt>pPaymentCompleteStatus</tt>.
     * 
     * @param pPaymentCompleteStatus value to be assigned to property pPaymentCompleteStatus
     */
    public void setpPaymentCompleteStatus(String pPaymentCompleteStatus) {
        this.pPaymentCompleteStatus = pPaymentCompleteStatus;
    }

    /**
     * Setter method for property <tt>pPaymentCompleteDate</tt>.
     * 
     * @param pPaymentCompleteDate value to be assigned to property pPaymentCompleteDate
     */
    public void setpPaymentCompleteDate(String pPaymentCompleteDate) {
        this.pPaymentCompleteDate = pPaymentCompleteDate;
    }

    /**
     * Setter method for property <tt>pAnnounceChoiceStatus</tt>.
     * 
     * @param pAnnounceChoiceStatus value to be assigned to property pAnnounceChoiceStatus
     */
    public void setpAnnounceChoiceStatus(String pAnnounceChoiceStatus) {
        this.pAnnounceChoiceStatus = pAnnounceChoiceStatus;
    }

    /**
     * Setter method for property <tt>pAnnounceChoiceDate</tt>.
     * 
     * @param pAnnounceChoiceDate value to be assigned to property pAnnounceChoiceDate
     */
    public void setpAnnounceChoiceDate(String pAnnounceChoiceDate) {
        this.pAnnounceChoiceDate = pAnnounceChoiceDate;
    }

    /**
     * Setter method for property <tt>pAppointWorkStatus</tt>.
     * 
     * @param pAppointWorkStatus value to be assigned to property pAppointWorkStatus
     */
    public void setpAppointWorkStatus(String pAppointWorkStatus) {
        this.pAppointWorkStatus = pAppointWorkStatus;
    }

    /**
     * Setter method for property <tt>pAppointWorkDate</tt>.
     * 
     * @param pAppointWorkDate value to be assigned to property pAppointWorkDate
     */
    public void setpAppointWorkDate(String pAppointWorkDate) {
        this.pAppointWorkDate = pAppointWorkDate;
    }

    /**
     * Setter method for property <tt>pEvaluationStatus</tt>.
     * 
     * @param pEvaluationStatus value to be assigned to property pEvaluationStatus
     */
    public void setpEvaluationStatus(String pEvaluationStatus) {
        this.pEvaluationStatus = pEvaluationStatus;
    }

    /**
     * Setter method for property <tt>pEvaluationDate</tt>.
     * 
     * @param pEvaluationDate value to be assigned to property pEvaluationDate
     */
    public void setpEvaluationDate(String pEvaluationDate) {
        this.pEvaluationDate = pEvaluationDate;
    }

}
