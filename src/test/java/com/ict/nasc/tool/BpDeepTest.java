/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.tool;

import java.util.Arrays;

import junit.framework.TestCase;

import com.ict.nasc.tool.nlp.BpDeep;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: BpDeepTest.java, v 0.1 2016-2-18 下午3:16:32  Exp $
 */
public class BpDeepTest extends TestCase {
    /**
     * 
     */
    public void testBpDeep() {
        //初始化神经网络的基本配置
        //第一个参数是一个整型数组，表示神经网络的层数和每层节点数，比如{3,10,10,10,10,2}表示输入层是3个节点，输出层是2个节点，中间有4层隐含层，每层10个节点
        //第二个参数是学习步长，第三个参数是动量系数
        //new int[]{2,10,2}
        BpDeep bp = new BpDeep(new int[] { 2, 10, 2 }, 0.15, 0.8);

        //设置样本数据，对应上面的4个二维坐标数据
        double[][] data = new double[][] { { 1, 2 }, { 2, 2 }, { 1, 1 }, { 2, 1 } };
        //设置目标数据，对应4个坐标数据的分类
        double[][] target = new double[][] { { 1, 0 }, { 0, 1 }, { 0, 1 }, { 1, 0 } };
        //double[][] target = new double[][] { { 1 }, { 0}, { 0 }, { 1 } };
        //迭代训练5000次
        for (int n = 0; n < 10; n++)
            for (int i = 0; i < data.length; i++)
                bp.train(data[i], target[i]);

        //根据训练结果来检验样本数据
        for (int j = 0; j < data.length; j++) {
            double[] result = bp.computeOut(data[j]);
            System.out.println(Arrays.toString(data[j]) + ":" + Arrays.toString(result));
        }

        //根据训练结果来预测一条新数据的分类
        double[] x = new double[] { 3, 1 };
        double[] result = bp.computeOut(x);
        System.out.println(Arrays.toString(x) + ":" + Arrays.toString(result));
    }
}