package net.saisimon.tomcat.shutdown;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SwingApp extends JFrame {

	private static final long serialVersionUID = -4391661817602636882L;
	
	JButton button = new JButton();
	JTextArea textArea = new JTextArea();
	String dir = System.getProperty("user.dir");
	String temp = "temp.txt";
	
	public SwingApp() {
		button.setText("退出");
		button.setBounds(new Rectangle(304, 248, 76, 37));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shutdown();
				System.exit(0);
			}
		});
		this.getContentPane().setLayout(null);
		textArea.setText("点击退出按钮关闭");
		textArea.setBounds(new Rectangle(9, 7, 371, 235));
		this.getContentPane().add(button);
		this.getContentPane().add(textArea);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0, 400, 330);
		this.setVisible(true);
		init();
	}
	
	private void init() {
		DeleteTempFileHook hook = new DeleteTempFileHook();
		Runtime.getRuntime().addShutdownHook(hook);
		
		File file = new File(dir, temp);
		try {
			System.out.println("Create temp file");
			file.createNewFile();
			System.out.println(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void shutdown() {
		File file = new File(dir, temp);
		if (file.exists()) {
			System.out.println("Delete temp file");
			file.delete();
		}
	}
	
	public static void main(String[] args) {
		SwingApp app = new SwingApp();
	}
	
	private class DeleteTempFileHook extends Thread {

		@Override
		public void run() {
			shutdown();
		}
		
	}

}
