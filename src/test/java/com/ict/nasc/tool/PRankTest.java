/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.tool;

import com.ict.nasc.tool.nlp.PRank;

import junit.framework.TestCase;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: PRankTest.java, v 0.1 2016-3-16 下午1:39:38  Exp $
 */
public class PRankTest extends TestCase {

    /**
     * 分类器测试
     */
    public void testPRank() {
        double[][] data = new double[][] { { 1, 2 }, { 2, 2 }, { 1, 1 }, { 2, 1 } };
        int[] target = new int[] { 2, 1, 1, 2 };
        PRank prank = new PRank(data, target, 2, 2);
        for(int i=0;i<5000;i++){
            prank.trainModel();
        }
        
        System.out.println(prank.runModel(new double[]{1,2}));
        System.out.println(prank.runModel(new double[]{1,3}));
        

    }

}
