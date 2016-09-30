package net.saisimon.qlcoder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Visualization {
	
	public void writeImage(String inPath, String outPath) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inPath))));
				FileOutputStream fos = new FileOutputStream(new File(outPath))) {
			BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.getGraphics();
			String line = br.readLine();
			Color c = new Color(255,255,255);
			while (line != null) {
				String[] temp = line.split(" ");
				g.setColor(c);
				g.drawOval(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), 5, 5);
				line = br.readLine();
			}
			ImageIO.write(bi, "jpeg", fos);
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static void main(String[] args) {
		Visualization v = new Visualization();
		String inPath = "d:/tmp/show.txt";
		String outPath = "d:/tmp/show.jpeg";
		try {
			v.writeImage(inPath, outPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
