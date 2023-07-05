package com.rgt.onlinebidding.service;

import com.rgt.onlinebidding.entity.Item;
import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.interfcae.BiddingStrategy;

public class AutomaticBiddingStrategy implements BiddingStrategy {

	private double maxBidAmount;
	
	private double incrementValue;
	
	Item item = new Item();

    public AutomaticBiddingStrategy(double maxBidAmount ,double incrementValue) {
        this.maxBidAmount = maxBidAmount;
        this.incrementValue =incrementValue;
    }

    public double bid(Item item, User user,Double bidAmount) {
        double currentBid = item.getCurrentHighestBid();
        double newBidAmount = currentBid + incrementValue; 
    	if (newBidAmount <= maxBidAmount) {
            item.placeBid(user, newBidAmount);
            currentBid = newBidAmount;
            newBidAmount += incrementValue;
        }
    	item.placeBid(user, newBidAmount);
    	item.notifyObservers(item);
        return newBidAmount;
    }

}
