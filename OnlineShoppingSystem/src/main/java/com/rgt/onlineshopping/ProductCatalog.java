package com.rgt.onlineshopping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCatalog {
	private Map<String, Product> products = new HashMap<>();

	public void addProduct(Product product) {
		products.put(product.getName(), product);
	}

	public List<Product> getAllProducts() {
		return new ArrayList<>(products.values());
	}

	public Product getProduct(String name) {
		return products.get(name);
	}

	@SuppressWarnings("unchecked")
	public void loadProducts(String filename) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(filename);
			objectInputStream = new ObjectInputStream(fileInputStream);
			products = (Map<String, Product>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void saveProducts(String filename) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			fileOutputStream = new FileOutputStream(filename);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(products);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
