package com.rgt.onlineshopping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
	private List<Order> orders = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public void loadOrderHistory(String filename) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(filename);
			objectInputStream = new ObjectInputStream(fileInputStream);
			orders = (List<Order>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveOrderHistory(String filename) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filename);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(orders);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}
