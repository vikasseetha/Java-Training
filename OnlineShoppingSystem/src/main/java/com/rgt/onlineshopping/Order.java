package com.rgt.onlineshopping;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int nextConfirmationNumber = 1;
	 
	private Integer confirmationNumber;

	private Map<Product, Integer> items = new HashMap<>();

	private Double totalPrice;

	public Order( Map<Product, Integer> items, Double totalPrice) 
	{
	    this.confirmationNumber=nextConfirmationNumber++;
		this.items = items;
		this.totalPrice = totalPrice;
	}

	public Integer getConfirmationNumber() {
		return confirmationNumber;
	}

	public Map<Product, Integer> getItems() {
		return items;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}
}
