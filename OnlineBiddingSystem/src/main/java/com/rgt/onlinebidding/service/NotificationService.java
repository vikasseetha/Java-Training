package com.rgt.onlinebidding.service;

import com.rgt.onlinebidding.entity.Item;
import com.rgt.onlinebidding.entity.User;

public class NotificationService
{
	public void notifyUser(User user,Item item) {
		System.out.println("You have been outbid on item: "+item.getName());
	}
}
