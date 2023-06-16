package com.rgt.rgtmessaging.entity;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

	private String id;

	private String content;

	private String author;

	private long timestamp;

	private List<String> likes;
	private List<String> retweets;
	private List<String> replies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void like(User currentUser) {
		if (likes == null) {
			likes = new ArrayList<>();
		}
		String currentUsername = currentUser.getUsername();

		if (!likes.contains(currentUsername)) {
			likes.add(currentUsername);
			System.out.println("Tweet liked by @" + currentUsername);
		} else {
			System.out.println("You have already liked this tweet.");
		}
	}

	public void retweet(User currentUser) {
		if (retweets == null) {
			retweets = new ArrayList<>();
		}
		String currentUsername = currentUser.getUsername();
		if (!retweets.contains(currentUsername)) {
			retweets.add(currentUsername);
			System.out.println("Tweet retweeted by @" + currentUsername);
		} else {
			System.out.println("You have already retweeted this tweet.");
		}
	}

	public void reply(User currentUser, String content) {
		if (replies == null) {
			replies = new ArrayList<>();
		}
		String currentUsername = currentUser.getUsername();
		String reply = currentUsername + ": " + content;
		replies.add(reply);
		System.out.println("Reply posted successfully.");
	}

	public Tweet(String id, String content, String author, long timestamp) {
		super();
		this.id = id;
		this.content = content;
		this.author = author;
		this.timestamp = System.currentTimeMillis();
	}

	public Tweet(String id, String content, String author) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.timestamp = System.currentTimeMillis();
	}

	public Tweet() {
		super();
	}
}
