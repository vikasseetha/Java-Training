package com.rgt.onlinebidding.service;

import java.util.List;

import com.rgt.onlinebidding.entity.Bid;
import com.rgt.onlinebidding.entity.Item;
import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.interfcae.Observer;

public class UserObserver implements Observer 
{
    private User user;
    
	public UserObserver(User user, List<Observer> observers) {
		super();
		this.user = user;
	}

	public UserObserver() {	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public void update(Item item ,Observer observer) 
	{
		for (Bid item2 : ((User) user).getBiddingHistory()) {
			if (item.getName().equals(item2.getItem().getName())
					&& item.getCurrentHighestBid() > item2.getAmount()) {
				System.out.println("Your OutBidded for Item :" + item.getName() + " By amount "
						+ (item.getCurrentHighestBid() - item2.getAmount()));
			}
		}
	}	
}
