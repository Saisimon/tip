package net.saisimon.qlcoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * @author Saisimon
 * 
 * 	这里有一个1000行的文本文件，文件的每一行有2个浮点数，代表一个点的坐标(x,y)。（所有点都在第一象限）
 *	请找出一条直线 Y=a*X+b，使文件中所有点和该直线的距离的和最短。
 *	你提交的答案包含2个浮点数a,b,中间用-分隔。（举例:3.14-5.18）
 *	通过条件：提交答案所得的各点到直线的最短距离的和 <= 1300时，即为通过
 *
 *	需要 commons-math.jar
 *
 */
public class Fitting {
	
	public void fit(String path) throws IOException {
		SimpleRegression sr = new SimpleRegression();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
			String line = br.readLine();
			while (line != null) {
				String[] point = line.split(" ");
				sr.addData(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
				line = br.readLine();
			}
		} catch (IOException e) {
			throw e;
		}
		System.out.println(sr.getSlope() + "-" + sr.getIntercept());
	}
	
	public static void main(String[] args) {
		Fitting f = new Fitting();
		String path = "d:/tmp/fitting.txt";
		try {
			f.fit(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
