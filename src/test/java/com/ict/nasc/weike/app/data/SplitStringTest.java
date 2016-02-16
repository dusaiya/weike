/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import junit.framework.TestCase;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: SplitStringTest.java, v 0.1 2015-12-2 下午9:23:14  Exp $
 */
public class SplitStringTest extends TestCase {
    /**
     * payment 
     **/
    private static final String payment = "计件，每个￥15.00，已选79个，还需要254个";

    /**
     * payment
     */
    public void testPaymentSplit_1() {
        String paymentStr = payment;
        System.out.println("【赏金分配】" + paymentStr);
        int start = paymentStr.indexOf("￥");
        int end = paymentStr.indexOf("，已");
        System.out.println(Integer.parseInt(paymentStr.substring(start + 1, end).replace(".", "")));
        paymentStr = paymentStr.substring(end + 1);
        System.out.println(paymentStr);
        start = paymentStr.indexOf("已选");
        end = paymentStr.indexOf("个，");
        System.out.println(Integer.parseInt(paymentStr.substring(start + 2, end)));
        paymentStr = paymentStr.substring(end + 2);
        System.out.println(paymentStr);
        start = paymentStr.indexOf("需要");
        end = paymentStr.indexOf("个");
        System.out.println(Integer.parseInt(paymentStr.substring(start + 2, end)));
    }

    /**
     * 
     */
    public void testSubstrPrefixSplit_1() {
        String subUrl = "http://task.zhubajie.com" + "/1469816/p17.html";
        String countStr = "17";
        int length = subUrl.length();
        System.out.println(subUrl.substring(0, length - countStr.length() - ".html".length()));
    }

    /**
     * 
     */
    public void testDateStr() {
        System.out.println(" ".equals(" "));
        System.out.println("123" + null + "123");
    }
}
