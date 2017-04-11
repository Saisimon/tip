package net.saisimon.main.concurrent;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionTask extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	
	private List<Product> products;
	private int first;
	private int last;
	private double increment;
	
	public RecursiveActionTask(List<Product> products, int first, int last, double increment) {
		super();
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		// 分而治之
		if (last - first < 10) {
			updatePrices();
		} else {
			int middle = (last + first) / 2;
			System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
			RecursiveActionTask t1 = new RecursiveActionTask(products, first, middle + 1, increment);
			RecursiveActionTask t2 = new RecursiveActionTask(products, middle + 1, last, increment);
			invokeAll(t1, t2);
		}
	}

	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

}
