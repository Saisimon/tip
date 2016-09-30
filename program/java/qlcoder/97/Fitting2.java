package net.saisimon.qlcoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

/**
 * @author Saisimon
 * 
 * 	这里有一个10000行的文本文件，文件的每一行有2个浮点数，代表一个点的坐标(x,y)。（所有点都在第一象限）
 *	请找出一个多项式曲线 Y=a*X^5+b*X^4+c*X^3+d*X^2+e*X+f，使文件中所有点和该曲线的垂直距离的平方的和最短。
 *	一个点(x,y)到该曲线的垂直距离为abs(a*x^5+b*x^4+c*x^3+d*x^2+e*x+f-y)。
 *	你提交的答案包含6个浮点数a,b,c,d,e,f 。中间用:分隔。（举例:-3.14:5.18:3.15:-3.16:-3.17:5.31）
 *	通过条件：提交答案所得的各点到曲线的垂直距离的平方和 <=8195000 时，即为通过。
 *
 *	需要 commons-math3.jar
 *
 */
public class Fitting2 {
	
	public void fit(String path) throws IOException {
		final WeightedObservedPoints obs = new WeightedObservedPoints();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
			String line = br.readLine();
			while (line != null) {
				String[] point = line.split(" ");
				double x = Double.parseDouble(point[0]);
				double y = Double.parseDouble(point[1]);
				obs.add(x, y);
				line = br.readLine();
			}
		} catch (IOException e) {
			throw e;
		}
		final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(5);
		final double[] coeff = fitter.fit(obs.toList());
		StringBuilder res = new StringBuilder();
		for (int i = coeff.length - 1; i >= 0; i--) {
			res.append(coeff[i]).append(":");
		}
		System.out.println(res.substring(0, res.length() - 1));
	}
	
	public static void main(String[] args) {
		Fitting2 f = new Fitting2();
		String path = "d:/tmp/fitting2.txt";
		try {
			f.fit(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
