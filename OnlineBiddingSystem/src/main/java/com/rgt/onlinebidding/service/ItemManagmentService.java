package com.rgt.onlinebidding.service;

import java.util.ArrayList;
import java.util.List;

import com.rgt.onlinebidding.entity.Bid;
import com.rgt.onlinebidding.entity.Item;
import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.factory.ItemFactory;

public class ItemManagmentService {
    private List<Item> items;

	public ItemManagmentService() {
        this.items = new ArrayList<>();
    }

    public Item addItem(String name, String description, double startingBid) {
    	Item item = ItemFactory.createItem(name, description, startingBid);
       	items.add(item);
		return item;
    }

    public List<Item> searchItems(String keyword) {
        List<Item> searchResults = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }

    public List<Bid> viewBiddingHistory(User user) {
      List<Bid> biddingHistory = new ArrayList<>();
      for (Item item : items) {
          List<Bid> itemBids = item.getBids();
          for (Bid bid : itemBids) {
              if (bid.getBidder().equals(user)) {
                  biddingHistory.add(bid);
              }
          }
      }
      return biddingHistory;
  }
}
