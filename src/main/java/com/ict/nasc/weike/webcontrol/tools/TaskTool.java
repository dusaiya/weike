/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.tools;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.Page;

import com.ict.nasc.weike.webcontrol.model.WeikePaymentType;
import com.ict.nasc.weike.webcontrol.model.WeikeTask;
import com.ict.nasc.weike.webcontrol.model.WeikeTaskMode;

/**
 * 任务信息处理
 * <ul>
 * <li>{@link #weikeModeAnalyze(Elements, WeikeTask)} 处理任务属性及其酬劳方式</li>
 * <li>{@link #releaseInfo(WeikeTask, Elements)} 发布人信息编辑</li> 
 * <li>{@link #paymentInfo(Elements, WeikeTask)} 任务酬劳</li>
 * <li>{@link #subUrlInfo(Page, WeikeTask, Elements)} 子页面连接处理</li>
 * <li>{@link #genProcessInfo(WeikeTask, Element, WeikeTaskMode)} 任务进度处理</li>
 * </ul>
 * @author xueye.duanxy
 * @version $Id: TaskTool.java, v 0.1 2015-12-2 上午11:23:29  Exp $
 */
public class TaskTool {

    /**
     * 根据不同交易类型处理交易信息
     * @param taskModeBlock
     * @param task
     */
    public static void weikeModeAnalyze(Elements taskModeBlock, WeikeTask task) {
        // 
        Element modedoc = taskModeBlock.select("div.header").get(0);
        String modestr = modedoc.select("em.gray6").get(0).text();
        WeikeTaskMode taskMode = genTaskMode(modestr);
        //修饰任务类型的提示(八戒悬赏，及项目适合的种类信息)
        taskMode(task, modedoc, taskMode);
        //修饰payment
        genPaymentInfo(task, modedoc, taskMode);
        //修饰流程信息process
        modedoc = taskModeBlock.select("div.modecont").get(0);
        genProcessInfo(task, modedoc, taskMode);

    }

    /**
     * 
     * @param task
     * @param modedoc
     * @param taskMode
     */
    public static void genProcessInfo(WeikeTask task, Element modedoc, WeikeTaskMode taskMode) {
        task.setProcessInfo(modedoc.text());
        Elements dls = modedoc.select("li");
        for (Element dl : dls) {
            String processMsg = dl.select("p").get(0).text();
            String status = "未进行";
            if ("cur".equals(dl.attr("class"))) {
                status = "进行中";
            } else if (CollectionUtils.isNotEmpty(dl.select("span.gou"))) {
                status = "完成";
            }
            //进程完成细节
            processDetail(task, processMsg, status);

        }

    }

    /**
     * 进程完成细节
     * @param task
     * @param processMsg
     * @param status
     */
    private static void processDetail(WeikeTask task, String processMsg, String status) {
        String[] strlist = processMsg.split(" ");
        String dateStr = null;
        if (strlist.length > 1) {
            dateStr = strlist[1];
            dateStr = dateStr.replace(" ", "");
            if (StringUtils.isNotBlank(dateStr)) {
                dateStr = dateStr.replace(".", "-");
            } else {
                dateStr = null;
            }
        }

        int process = 0;
        if (processMsg.contains("发布需求")) {
            process = 1;
        } else if (processMsg.contains("服务商交稿") || processMsg.contains("匹配服务商")) {
            process = 2;
        } else if (processMsg.contains("雇主设置合格稿件") || processMsg.contains("雇主选稿")
                   || processMsg.contains("选择服务商")) {
            process = 3;
        } else if (processMsg.contains("中标公示")) {
            process = 4;
        } else if (processMsg.contains("服务商工作")) {
            process = 5;
        } else if (processMsg.contains("交易成功") || processMsg.contains("验收并付款")) {
            process = 6;
        } else if (processMsg.contains("评价") || processMsg.contains("双方已评")) {
            process = 7;
        }
        switch (process) {
            case 1:
                task.setpReleaseStatus(status);
                task.setpReleaseDate(dateStr);
                break;
            case 2:
                task.setpCrowdSubmitStatus(status);
                task.setpCrowdSubmitDate(dateStr);
                break;
            case 3:
                task.setpEmployerChooseStatus(status);
                task.setpEmployerChooseDate(dateStr);
                break;
            case 4:
                task.setpAnnounceChoiceStatus(status);
                task.setpAnnounceChoiceDate(dateStr);
                break;
            case 5:
                task.setpAppointWorkStatus(status);
                task.setpAppointWorkDate(dateStr);
                break;
            case 6:
                task.setpPaymentCompleteStatus(status);
                task.setpPaymentCompleteDate(dateStr);
                break;
            case 7:
                task.setpEvaluationStatus(status);
                task.setpEvaluationDate(dateStr);
                break;
            default:
                System.out.println(task.getTaskLink() + "【进程转换异常】" + task.getProcessInfo());
                break;
        }
    }

    /**
     * 
     * @param task
     * @param modedoc
     * @param taskMode
     */
    private static void taskMode(WeikeTask task, Element modedoc, WeikeTaskMode taskMode) {
        task.setTaskMode(taskMode.getDesc());
        if (taskMode != WeikeTaskMode.inviteTender) {
            task.setSpecialTag(modedoc.select("a").get(0).text());//TODO a的第一个, 没有class 很有可能后期需要修改
        }
        task.setTradeModeDetail(modedoc.select("span.zhaobiao-summary-qa").get(0).attr("tool-text"));
    }

    /**
     * 
     * @param task
     * @param modedoc
     * @param taskMode
     */
    private static void genPaymentInfo(WeikeTask task, Element modedoc, WeikeTaskMode taskMode) {
        switch (taskMode) {
            case count:
                countPayment(task, modedoc.select("div.taskmode-inline"));
                break;
            case competition:
                competitionPayment(task, modedoc.select("div.taskmode-inline"));
                break;
            case inviteTender:
                task.setPaymentType(WeikePaymentType.topOnly.getDesc());//TODO 招标类型的没有，所以无需处理
                break;
            default:
                break;
        }
    }

    /**
     * 
     * @param task
     * @param taskModeBlock
     */
    private static void competitionPayment(WeikeTask task, Elements taskModeBlock) {
        String paymentStr = taskModeBlock.get(0).text();
        task.setPaymentDetail(paymentStr);
        if (paymentStr.contains("一人独享")) {
            task.setPieceworkAmt(task.getTotalAmt());
            task.setPaymentType(WeikePaymentType.topOnly.getDesc());
        } else if (paymentStr.contains("比赛")) {
            int start = paymentStr.indexOf("已选");
            int end = paymentStr.indexOf("个，");
            task.setSelectedCount(Integer.parseInt(paymentStr.substring(start + 2, end)));
            paymentStr = paymentStr.substring(end + 2);
            start = paymentStr.indexOf("需要");
            end = paymentStr.indexOf("个");
            task.setNeedCount(Integer.parseInt(paymentStr.substring(start + 2, end)));
        } else {
            System.out.println(task.getTaskLink() + "【赏金分配异常】:" + paymentStr);
        }
    }

    /**
     * Payment 类型
     * @param task
     * @param taskModeBlock
     */
    private static void countPayment(WeikeTask task, Elements taskModeBlock) {
        task.setPaymentType(WeikePaymentType.piece.getDesc());
        task.setPaymentDetail(taskModeBlock.get(0).text());
        String paymentStr = taskModeBlock.select("em").get(0).text();
        int start = paymentStr.indexOf("￥");
        int end = paymentStr.indexOf("，已");
        if (end < 0) {
            task.setPieceworkAmt(Integer.parseInt(paymentStr.substring(start + 1).replace(".", "")));
        } else {
            task.setPieceworkAmt(Integer.parseInt(paymentStr.substring(start + 1, end).replace(".",
                "")));
            paymentStr = paymentStr.substring(end + 1);
            start = paymentStr.indexOf("已选");
            end = paymentStr.indexOf("个，");
            if (start > 1) {
                task.setSelectedCount(Integer.parseInt(paymentStr.substring(start + 2, end)));
            }
            paymentStr = paymentStr.substring(end + 2);
            start = paymentStr.indexOf("需要");
            end = paymentStr.indexOf("个");
            if (start > 1) {
                task.setNeedCount(Integer.parseInt(paymentStr.substring(start + 2, end)));
            }
        }
        Elements limitDoc = taskModeBlock.select("em");
        if (CollectionUtils.isNotEmpty(limitDoc) && limitDoc.size() > 1) {
            task.setCountPerWorker(Integer.parseInt(taskModeBlock.select("em").get(1).text()));
        }
    }

    /**
     * 模式比较 获取任务类型
     * @param modestr
     * @return
     */
    private static WeikeTaskMode genTaskMode(String modestr) {
        WeikeTaskMode taskMode = WeikeTaskMode.unknow;
        if (modestr.contains("比稿")) {
            taskMode = WeikeTaskMode.competition;
        } else if (modestr.contains("计件")) {
            taskMode = WeikeTaskMode.count;
        } else if (modestr.contains("招标")) {
            taskMode = WeikeTaskMode.inviteTender;
        } else {
            System.out.println("【模式异常】:" + modestr);
        }
        return taskMode;
    }

    /**
     * 发布者信息
     * @param task
     * @param dls
     */
    public static void releaseInfo(WeikeTask task, Elements dls) {
        if (!CollectionUtils.isEmpty(dls)) {
            //发布者姓名
            task.setReleaseUserName(dls.get(0).text());
            String userUrl = dls.get(0).attr("href");
            //发布者主页
            task.setReleaseUserUrl(userUrl);
            String[] strList = userUrl.split("/");
            if (strList != null && strList.length >= 4) {
                //发布者id
                task.setReleaseUserId(strList[3]);
            }
        }

    }

    /**
     * 
     * @param payment
     * @param task
     * @throws NumberFormatException
     */
    public static void paymentInfo(Elements payment, WeikeTask task) throws NumberFormatException {
        Elements dls = null;
        dls = payment.select("u").select("span");
        for (Element dl : dls) {
            task.setTotalAmt(Integer.parseInt(dl.text().replace(".", "")));
        }
        dls = payment.select("span.ydanbao").select("i.orange1");
        for (Element dl : dls) {
            task.setGuaranteedAmt(Integer.parseInt(dl.text().replace(".", "").replace("￥", "")));
        }
    }

    /**
     * 
     * @param page
     * @param task
     * @param dls
     * @throws NumberFormatException
     */
    public static void subUrlInfo(Page page, WeikeTask task, Elements dls)
                                                                          throws NumberFormatException {
        if (!CollectionUtils.isEmpty(dls)) {
            Element dl = dls.get(dls.size() - 2);
            String subUrl = "http://task.zhubajie.com" + dl.attr("href");
            String countStr = dl.text();
            int length = subUrl.length();
            task.setSubUrlPrefix(subUrl.substring(0, length - countStr.length() - ".html".length()));
            task.setSubCount(Integer.parseInt(countStr));
        } else {
            task.setSubUrlPrefix(page.getUrl());
            task.setSubCount(0);
        }
    }

    /**
     * 执行插入Sql
     * @param task
     * @param stmt
     */
    public static void insertSql(WeikeTask task, Statement stmt) {
        String sql = "insert into task("
                     + "task_Id, task_Title, task_Release_Date, task_Link, release_User_Name, release_User_Id, release_User_Url, "
                     + "total_Amt, guaranteed_Amt, task_Mode, special_Tag, payment_Type, trade_Mode_Detail, "
                     + "release_Province, release_City, release_Source, task_Status, "
                     + "mobile_Authen, realName_Authen, completement_Security, origin_Work, "
                     + "remark, sub_Url_Prefix, sub_Count, payment_Detail, "
                     + "first_Catagory, second_Catagory, piecework_Amt, selected_Count, need_Count, count_Per_Worker, "
                     + "process_Info, p_Release_Status, p_Release_Date, p_Crowd_Submit_Status, p_Crowd_Submit_Date, "
                     + "p_Employer_Choose_Status, p_Employer_Choose_Date, p_Payment_Complete_Status, p_Payment_Complete_Date, "
                     + "p_Announce_Choice_Status, p_Announce_ChoiceDate, p_Appoint_Work_Status, p_Appoint_Work_Date, "
                     + "p_Evaluation_Status, p_Evaluation_Date) values ('"
                     + task.getTaskId()
                     + "','"
                     + task.getTaskTitle()
                     + "','"
                     + task.getTaskReleaseDate()
                     + "','"
                     + task.getTaskLink()
                     + "','"
                     + task.getReleaseUserName()
                     + "','"
                     + task.getReleaseUserId()
                     + "','"
                     + task.getReleaseUserUrl()
                     + "',"
                     + task.getTotalAmt()
                     + ","
                     + task.getGuaranteedAmt()
                     + ",'"
                     + task.getTaskMode()
                     + "','"
                     + task.getSpecialTag()
                     + "','"
                     + task.getPaymentType()
                     + "','"
                     + task.getTradeModeDetail()
                     + "','"
                     + task.getReleaseProvince()
                     + "','"
                     + task.getReleaseCity()
                     + "','"
                     + task.getReleaseSource()
                     + "','"
                     + task.getTaskStatus()
                     + "',"
                     + task.isMobileAuthen()
                     + ","
                     + task.isRealNameAuthen()
                     + ","
                     + task.isCompletementSecurity()
                     + ","
                     + task.isOriginWork()
                     + ",'"
                     + task.getRemark()
                     + "','"
                     + task.getSubUrlPrefix()
                     + "','"
                     + task.getSubCount()
                     + "','"
                     + task.getPaymentDetail()
                     + "','"
                     + task.getFirstCatagory()
                     + "','"
                     + task.getSecondCatagory()
                     + "',"
                     + task.getPieceworkAmt()
                     + ","
                     + task.getSelectedCount()
                     + ","
                     + task.getNeedCount()
                     + ","
                     + task.getCountPerWorker()
                     + ",'"
                     + task.getProcessInfo()
                     + "','"
                     + task.getpReleaseStatus()
                     + "','"
                     + task.getpReleaseDate()
                     + "','"
                     + task.getpCrowdSubmitStatus()
                     + "','"
                     + task.getpCrowdSubmitDate()
                     + "','"
                     + task.getpEmployerChooseStatus()
                     + "','"
                     + task.getpEmployerChooseDate()
                     + "','"
                     + task.getpPaymentCompleteStatus()
                     + "','"
                     + task.getpPaymentCompleteDate()
                     + "','"
                     + task.getpAnnounceChoiceStatus()
                     + "','"
                     + task.getpAnnounceChoiceDate()
                     + "','"
                     + task.getpAppointWorkStatus()
                     + "','"
                     + task.getpAppointWorkDate()
                     + "','"
                     + task.getpEvaluationStatus()
                     + "','" + task.getpEvaluationDate() + "')";
        ;

        System.out.println("插入语句"
                           + sql.replace("\r", " ").replace("\n", " ").replace("'null'", "null"));
        try {
            int rs = stmt.executeUpdate(sql);
            if (rs == 1) {
                System.out.println(task.getTaskId() + "【插入数据库成功】");
            } else {
                System.out.println(task.getTaskId() + "【插入数据库失败】");
            }
        } catch (SQLException e) {
            System.out.println("插入Sql【数据库异常】" + e);
        } catch (Exception e) {
            System.out.println("插入Sql【其它异常】" + e);
        }
    }

    /**
     * 
     * @param taskId
     * @param taskLink
     * @param cont
     * @param ext
     * @param stmt
     */
    public static void insertExtSql(String taskId, String taskLink, String cont, String ext,
                                    Statement stmt) {
        String sql = "insert into task_ext (task_id, task_link, user_content, user_add)"
                     + "values ('" + taskId + "', '" + taskLink + "', '" + cont + "', '" + ext
                     + "')";
        try {
            int rs = stmt.executeUpdate(sql.replace("\r", " ").replace("\n", " ")
                .replace("'null'", "null"));
            if (rs == 1) {
                System.out.println(taskId + "【插入数据库成功】");
            } else {
                System.out.println(taskId + "【插入数据库失败】");
            }
        } catch (SQLException e) {
            System.out.println("插入Sql【数据库异常】" + e);
        } catch (Exception e) {
            System.out.println("插入Sql【其它异常】" + e);
        }
    }
}
