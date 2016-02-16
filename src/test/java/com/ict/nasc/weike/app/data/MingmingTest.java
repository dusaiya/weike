/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import junit.framework.TestCase;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: MingmingTest.java, v 0.1 2016-1-4 下午4:30:20  Exp $
 */
public class MingmingTest extends TestCase {
    /**
     * asd
     */
    private String 中文名变量 = "123";

    /**
     * 123
     */
    public void testJiubuyongMain() {
        System.out.println(中文名变量);
    }

    /**
     * test中文方法名
     */
    public void test中文方法名() {
        System.out.println(中文名变量 + "test中文方法名");
    }
}
