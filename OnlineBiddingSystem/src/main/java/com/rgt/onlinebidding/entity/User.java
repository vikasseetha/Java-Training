package com.rgt.onlinebidding.entity;

import java.util.ArrayList;
import java.util.List;

public class User 
{
	private String username;
    private String password;
    private List<Bid> biddingHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.biddingHistory = new ArrayList<>();
    }

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Bid> getBiddingHistory() {
		return biddingHistory;
	}

	public void setBiddingHistory(List<Bid> biddingHistory) {
		this.biddingHistory = biddingHistory;
	}
	
	public void addBid(Bid bid)
	{
		biddingHistory.add(bid);
	}
	
	}
