package net.saisimon.design.observer;

import java.util.Observable;

/**
 * @author Saisimon
 *	
 *	被观察者
 *
 */
public class DataSubject extends Observable {
	
	private int width;
	private int height;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
		// 长度改变时，设置状态为改变。不改变状态，观察者不会起作用
		this.setChanged();
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
		// 高度改变时，设置状态为改变。不改变状态，观察者不会起作用
		this.setChanged();
	}
	
}
