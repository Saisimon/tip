package net.saisimon.qlcoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Saisimon
 * 
 * 基于rgb分量的最低位的隐写术
 * https://zh.wikipedia.org/wiki/%E9%9A%90%E5%86%99%E6%9C%AF
 *
 */
public class Hide {
	
	public void parseHideImage(String inPath, String outPath) throws IOException {
		BufferedImage bi = ImageIO.read(new File(inPath));
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage newBi = new BufferedImage(width, height, bi.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = bi.getRGB(x, y);
				int r = (rgb & 0xff0000) >> 16;
				if (r % 2 == 1) {
					newBi.setRGB(x, y, getRGBInteger(255, 0, 0));
				} else {
					newBi.setRGB(x, y, getRGBInteger(0, 0, 0));
				}
			}
		}
		ImageIO.write(newBi, "png", new File(outPath));
	}
	
	private int getRGBInteger(int r, int g, int b) {
		int value = ((255 & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
		return value;
	}
	
	public static void main(String[] args) {
		Hide h = new Hide();
		try {
			h.parseHideImage("d:/tmp/lenna.png", "d:/tmp/lennaHide.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
