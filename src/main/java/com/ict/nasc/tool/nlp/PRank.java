/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.tool.nlp;

/**
 * 二维input 的 PRANK
 * @author xueye.duanxy
 * @version $Id: PRank.java, v 0.1 2016-2-23 上午10:32:46  Exp $
 */
public class PRank {

    /**训练输入**/
    private double[][] x;
    /** 权值*/
    private double[]   w;
    /**训练输出**/
    private int[]      y;
    /**偏移*/
    private double[]   bias;
    /**类别数量 r = 1,2,3,...l */
    private int        r;
    /**训练中修正值*/
    private int[]      z;
    /**feature count 特征数量*/
    private int        fc;

    /**
     * @param x
     * @param y
     * @param r
     * @param fc 特征数量
     */
    public PRank(double[][] x, int[] y, int r, int fc) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.w = new double[fc];
        for (int i = 0; i < fc; i++) {
            w[i] = 0; //初始化为0
        }
        this.bias = new double[r];
        for (int i = 0; i < r - 1; i++) {
            bias[i] = 0;
        }
        bias[r - 1] = Double.POSITIVE_INFINITY;//最后一个分类的bias为正无穷
    }

    /**
     * 训练模型
     */
    public void trainModel() {
        for (int t = 0; t < x.length; t++) {
            trainRound(x[t], y[t]);
        }
    }

    /**
     * 
     * @param roundInput
     * @param output
     */
    private void trainRound(double[] roundInput, int output) {
        int predictY = runModel(roundInput);
        if (predictY != output) {
            this.z = new int[r];
            double[] correctR = new double[r];
            //For r=1,···,l−1:if yt ≤r then zr =−1,else zr =+1
            for (int i = 1; i < r; i++) {
                if (output <= i) {
                    z[i - 1] = -1;
                } else {
                    z[i - 1] = 1;
                }
            }
            //For r=1,···,l−􏰇1:if zr(⟨w,xt⟩−br)≤0 then ζr=zr , elseζr =0
            for (int i = 0; i < r - 1; i++) {
                //判断对具体的某类 是正 predict 还是负predict 正predict就不需要修正
                if (z[i] * modelValue(roundInput, this.w, bias[i], fc) <= 0) {
                    correctR[i] = z[i];
                } else {
                    correctR[i] = 0;
                }
            }
            correctR[r - 1] = 0;
            //Update w=w+( ζr)x
            //For r = 1, · · · , l − 1 update br = br − ζr
            updateWeightBias(correctR, roundInput);
        }
    }

    /**
     * 
     * @param correctR
     * @param roundInput
     */
    private void updateWeightBias(double[] correctR, double[] roundInput) {
        for (int i = 0; i < this.r; i++) {
            bias[i] = bias[i] - correctR[i];
            for (int j = 0; j < this.fc; j++) {
                this.w[j] = this.w[j] + correctR[i] * roundInput[j];
            }
        }
    }

    /**
     * 执行算法
     * 可以是以前的值，也可以是新的值
     * @param inputX
     * @return 分类结果
     */
    public int runModel(double[] inputX) {
        for (int i = 0; i < r - 1; i++) {
            if (isThisType(i, inputX, w)) {
                return i + 1;
            }
        }
        return r;
    }

    /**
     * 判断当前是否为r分类
     * @param i
     * @param inputX
     * @param w2
     * @return
     */
    private boolean isThisType(int i, double[] inputX, double[] w2) {
        if (modelValue(inputX, w2, this.bias[i], this.fc) >= 0
            && modelValue(inputX, w2, this.bias[i], this.fc) < 0) {
            return true;
        }
        return false;
    }

    /**
     * Perceptrons 加权+bias后得到的分类值
     * @param inputX
     * @param w2
     * @param bias
     * @param fc2
     * @return
     */
    private double modelValue(double[] inputX, double[] w2, double bias, int fc2) {
        double sum = 0;
        for (int i = 0; i < fc2; i++) {
            sum = inputX[i] * w2[i];
        }
        return sum - bias;
    }

    /**
     * Getter method for property <tt>x</tt>.
     * 
     * @return property value of x
     */
    public double[][] getX() {
        return x;
    }

    /**
     * Getter method for property <tt>w</tt>.
     * 
     * @return property value of w
     */
    public double[] getW() {
        return w;
    }

    /**
     * Getter method for property <tt>y</tt>.
     * 
     * @return property value of y
     */
    public int[] getY() {
        return y;
    }

    /**
     * Getter method for property <tt>bias</tt>.
     * 
     * @return property value of bias
     */
    public double[] getBias() {
        return bias;
    }

    /**
     * Setter method for property <tt>x</tt>.
     * 
     * @param x value to be assigned to property x
     */
    public void setX(double[][] x) {
        this.x = x;
    }

    /**
     * Setter method for property <tt>w</tt>.
     * 
     * @param w value to be assigned to property w
     */
    public void setW(double[] w) {
        this.w = w;
    }

    /**
     * Setter method for property <tt>y</tt>.
     * 
     * @param y value to be assigned to property y
     */
    public void setY(int[] y) {
        this.y = y;
    }

    /**
     * Setter method for property <tt>bias</tt>.
     * 
     * @param bias value to be assigned to property bias
     */
    public void setBias(double[] bias) {
        this.bias = bias;
    }

    /**
     * Getter method for property <tt>r</tt>.
     * 
     * @return property value of r
     */
    public int getR() {
        return r;
    }

    /**
     * Setter method for property <tt>r</tt>.
     * 
     * @param r value to be assigned to property r
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * Getter method for property <tt>z</tt>.
     * 
     * @return property value of z
     */
    public int[] getZ() {
        return z;
    }

    /**
     * Getter method for property <tt>fc</tt>.
     * 
     * @return property value of fc
     */
    public int getFc() {
        return fc;
    }

    /**
     * Setter method for property <tt>z</tt>.
     * 
     * @param z value to be assigned to property z
     */
    public void setZ(int[] z) {
        this.z = z;
    }

    /**
     * Setter method for property <tt>fc</tt>.
     * 
     * @param fc value to be assigned to property fc
     */
    public void setFc(int fc) {
        this.fc = fc;
    }

}
