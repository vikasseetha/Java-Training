package com.rgt.onlinebidding.interfcae;

import com.rgt.onlinebidding.entity.Item;
import com.rgt.onlinebidding.entity.User;

public interface BiddingStrategy 
{
	public double bid(Item item, User user,Double bidAmount);
}
