package net.saisimon.design.observer;

import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * @author Saisimon
 * 
 * 观察者
 *
 */
public class FrameObserver extends JFrame implements Observer {

	private static final long serialVersionUID = 2680420223070543537L;
	
	public FrameObserver(String title) throws HeadlessException {
		super(title);
	}

	/** 
	 * 被观察者状态改变时，观察者所做的操作
	 */
	@Override
	public void update(Observable o, Object arg) {
		DataSubject subject = (DataSubject) o;
		this.setSize(subject.getWidth(), subject.getHeight());
	}

}
