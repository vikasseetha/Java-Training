package com.rgt.onlineshopping;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	private Map<Product, Integer> items = new HashMap<>();

	public Map<Product, Integer> getItems() {
		return items;
	}

	public void addItem(Product product, int quantity) {
		int currentQuantity = items.getOrDefault(product, 0);
		items.put(product, currentQuantity + quantity);
	}

	public void removeItem(Product product) {
		items.remove(product);
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (Map.Entry<Product, Integer> products : items.entrySet()) {
			Product product = products.getKey();
			int quantity = products.getValue();
			totalPrice += product.getPrice() * quantity;
		}
		return totalPrice;
	}

	public Map<Product, Integer> displayCart() {
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			System.out.println(product.getName() + " (Quantity: " + quantity + ")");
		}
		return items;
	}

	public Map<Product, Integer> getcart() {
		return items;
	}

	public void clearCart() {
		items.clear();
	}

}
