package com.rgt.onlinebidding.entity;

import java.util.ArrayList;
import java.util.List;

import com.rgt.onlinebidding.interfcae.Observer;

public class Item {
    private String name;
    private String description;
    private double currentHighestBid;
    private User highestBidder;
    private List<Bid> bids;
    private List<Observer> observers;

    
    public Item(String name, String description, double startingBid) {
        this.name = name;
        this.description = description;
        this.currentHighestBid = startingBid;
        this.highestBidder = null;
        this.bids = new ArrayList<>();
		this.observers = new ArrayList<>();
    }

    public Item() {
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }

    public void setCurrentHighestBid(double currentHighestBid) {
        this.currentHighestBid = currentHighestBid;
    }

    public User getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(User highestBidder) {
        this.highestBidder = highestBidder;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    
    public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
	
	 public void notifyObservers(Item item) {
	        for (Observer observer : observers) {
	            observer.update(item,observer);
	        }
	    }  
	 
	 public void addObserver(Observer observer) {
	        observers.add(observer);
	    }

    public void placeBid(User user, double bidAmount) {
        Bid newBid = new Bid(this, user, bidAmount, false);
        bids.add(newBid);

        if (bidAmount > currentHighestBid) {
            currentHighestBid = bidAmount;
            highestBidder = user;
            newBid.setWinningBid(true);
            user.addBid(newBid);
        }
    }  
}

