package net.saisimon.design.decorator;

public class Design {
	
	public static void main(String[] args) {
		Widget w1 = new ScrollDecorator(new BorderDecorator(new TextField(100, 200)));
		System.out.println("W1 :");
		w1.draw();
		System.out.println();
		Widget w2 = new ScrollDecorator(new TextField(100, 200));
		System.out.println("W2 :");
		w2.draw();
		System.out.println();
		Widget w3 = new BorderDecorator(new TextField(100, 200));
		System.out.println("W3 :");
		w3.draw();
	}
	
}
