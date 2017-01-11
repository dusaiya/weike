package com.ict.nasc.mathtest.mf;

import junit.framework.TestCase;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * http://www.blogjava.net/changedi/archive/2010/12/13/340441.html
 * LUP矩阵分解求解优化
 * http://blog.csdn.net/abcjennifer/article/details/30371615
 * 多个矩阵分解的包和算法(待验证)
 * @author alibaba
 *
 */
public class LupMfTest extends TestCase {

	public void testLUP() {
		double[][] testData = { { 1.0, 2.0, 3.0 }, { 2.0, 5.0, 3.0 }, { 1.0, 0.0, 8.0 } };

		RealMatrix matrix = MatrixUtils.createRealMatrix(testData);
		// LUP decomposition
		LUDecomposition LU = new LUDecomposition(matrix);
		RealMatrix l = LU.getL();
		RealMatrix u = LU.getU();
		RealMatrix p = LU.getP();
		System.out.println("L is : " + l);
		System.out.println("U is : " + u);
		System.out.println("P is : " + p);
		System.out.println("PA is " + (p.multiply(matrix)));
		System.out.println("LU is " + (l.multiply(u)));
		System.out.println("PA = LU is " + p.multiply(matrix).equals(l.multiply(u)));
		// matrix singular
		System.out.println("LU is not singular : " + LU.getSolver().isNonSingular());
		// matrix determinant
		System.out.println("matrix determinant is : " + LU.getDeterminant());
		// matrix solver
		RealMatrix b = MatrixUtils.createRealMatrix(new double[][] { { 1, 0 }, { 2, -5 }, { 3, 1 } });
		DecompositionSolver solver = LU.getSolver();
		System.out.println("solve Ax = b (when b is matrix) is x = " + solver.solve(b));
		System.out.println("solve Ax = b (when b is vector) is x = " + new ArrayRealVector(solver.solve(b.getColumnVector(0))));
		// matrix inverse
		System.out.println("matrix inverse is " + solver.getInverse());
	}

}
