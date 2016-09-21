package net.saisimon.qlcoder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Saisimon
 * 
 * 多个逻辑图片文件共享一个物理文件: 
 * 	1. 1字节的标记位。0代表接下来的照片仍然可用，1代表接下来的照片已经被删除，2代表该物理文件接下来已经没有图片了。 
 *  2. 4字节的size。标记照片的大小x。 
 *  3. x字节，照片文件本身。
 */
public class FileSystem {

	public void read(String path, String outDic) throws IOException {
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path)))) {
			File out = new File(outDic);
			if (!out.exists()) {
				out.mkdirs();
			}
			int count = 0;
			while (true) {
				int bit = bis.read();
				byte[] sizeBytes = new byte[4];
				if (bit == 0) {
					bis.read(sizeBytes);
					int length = byteArrayToInt(sizeBytes);
					byte[] content = new byte[length];
					bis.read(content, 0, length);
					FileOutputStream fos = new FileOutputStream(new File(outDic + "/" + count + ".jpg"));
					fos.write(content);
					fos.flush();
					if (fos != null) {
						fos.close();
					}
					count++;
				} else if (bit == 1) {
					continue;
				} else if (bit == 2) {
					break;
				} else {
					throw new IOException("head byte is wrong!");
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	private int byteArrayToInt(byte[] bytes) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (bytes[i] & 0x000000FF) << shift;
		}
		return value;
	}

	public static void main(String[] args) {
		FileSystem fs = new FileSystem();
		String path = "d:/tmp/rf.data";
		String outDic = "d:/tmp/out";
		try {
			fs.read(path, outDic);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
