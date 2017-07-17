package net.saisimon.design.observer;

import javax.swing.JFrame;

public class Design {
	
	public static void main(String[] args) {
		FrameSubject fs = new FrameSubject("FrameSubject");
		FrameObserver fo = new FrameObserver("FrameObserver");
		
		fs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 注册一个观察者对象
		fs.registerObserver(fo);
		fs.listener();
		fs.setSize(200, 200);
		fs.setLocation(250, 0);
		fs.setVisible(true);
		
		fo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fo.setSize(200, 200);
		fo.setVisible(true);
	}
	
}
