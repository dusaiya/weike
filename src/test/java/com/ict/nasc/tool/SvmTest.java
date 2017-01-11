/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.tool;

import java.io.IOException;

import junit.framework.TestCase;
//import libsvm.*;
//
//import libsvm.svm;
//
//import libsvm.svm_model;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: SvmTest.java, v 0.1 2016-5-9 下午7:00:04  Exp $
 */
public class SvmTest extends TestCase {

    /***
     * 
     */
    public void testSvm() {
        /** */

        String[] argvTrain = {

        "C:\\Users\\baolong\\Desktop\\KDD\\other\\svm\\train\\TR1.data",// 训练文件

                "C:\\Users\\baolong\\Desktop\\KDD\\other\\svm\\model\\MO1.model"// 模型文件

        };

        String[] argvPredict = {

        "C:\\Users\\baolong\\Desktop\\KDD\\other\\svm\\predict\\PR1.data",// 预测文件

                "C:\\Users\\baolong\\Desktop\\KDD\\other\\svm\\model\\MO1.model", // 模型文件

                "C:\\Users\\baolong\\Desktop\\KDD\\other\\svm\\result\\RE1.out" // 预测结果文件

        };

        try {
            /**
            svm.svm_train(null, null);
            svm.svm_predict(null, null);
            double[] record = { -1, 12, 12, 78 };
            libsvm.svm_model model = svm
                .svm_load_model("C:\\Users\\baolong\\Desktop\\KDD\\other\\svm\\model\\MO1.model");
*/
            //System.out.println(svmp.predictPerRecord(record, model));
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
