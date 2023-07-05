package com.rgt.onlinebidding.service;

import com.rgt.onlinebidding.entity.Item;
import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.interfcae.BiddingStrategy;

public class IncrementalBiddingStrategy implements BiddingStrategy{

	Item item = new Item();
	public double bid(Item item, User user,Double bidAmount) 
	{
		double currentBid = item.getCurrentHighestBid();
		double newBid =currentBid+1.0;
	    item.placeBid(user, newBid);
	    item.notifyObservers(item);
		return newBid;
	}
}
