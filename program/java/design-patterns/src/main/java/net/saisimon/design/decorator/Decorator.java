package net.saisimon.design.decorator;

public abstract class Decorator implements Widget {
	
	private Widget widget;
	
	public Decorator(Widget widget) {
		super();
		this.widget = widget;
	}

	@Override
	public void draw() {
		widget.draw();
	}

}
