package net.saisimon.design.decorator;

public class TextField implements Widget {
	
	private int width;
	private int height;
	
	public TextField(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw() {
		System.out.println("TextField: " + width + ", " + height);
	}

}
