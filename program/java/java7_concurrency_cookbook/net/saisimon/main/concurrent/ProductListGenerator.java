package net.saisimon.main.concurrent;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {
	
	public List<Product> generate(int size) {
		if (size < 0) {
			return null;
		}
		List<Product> results = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Product product = new Product();
			product.setName("Product " + i);
			product.setPrice(10);
			results.add(product);
		}
		return results;
	}
	
}
