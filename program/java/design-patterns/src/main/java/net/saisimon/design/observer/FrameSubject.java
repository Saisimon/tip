package net.saisimon.design.observer;

import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observer;

import javax.swing.JFrame;

public class FrameSubject extends JFrame {

	private static final long serialVersionUID = -1753928086085527163L;
	
	/**
	 * 被观察对象
	 */
	private DataSubject subject = new DataSubject();
	
	public FrameSubject(String title) throws HeadlessException {
		super(title);
	}

	/**
	 * 注册一个观察者对象
	 * 
	 * @param observer 观察者
	 */
	public void registerObserver(Observer observer) {
		subject.addObserver(observer);
	}
	
	public void listener() {
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				subject.setWidth(e.getComponent().getWidth());
				subject.setHeight(e.getComponent().getHeight());
				subject.notifyObservers();
			}
			
		});
	}
	
}
