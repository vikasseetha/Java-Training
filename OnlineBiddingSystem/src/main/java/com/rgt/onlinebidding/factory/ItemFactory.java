package com.rgt.onlinebidding.factory;

import com.rgt.onlinebidding.entity.Item;

public class ItemFactory 
{
	public static Item createItem(String name, String description, double startingBid) {
        return new Item(name, description, startingBid);
    }
}
