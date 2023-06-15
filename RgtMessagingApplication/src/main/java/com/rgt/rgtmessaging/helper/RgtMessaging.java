package com.rgt.rgtmessaging.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rgt.rgtmessaging.entity.Tweet;
import com.rgt.rgtmessaging.entity.User;
import com.rgt.rgtmessaging.exception.UserAlreadyExistsException;
import com.rgt.rgtmessaging.exception.UserNotFoundException;

public class RgtMessaging {
	private HashMap<String, User> users;
	private ArrayList<Tweet> tweets;
	private User currentUser;

	public RgtMessaging() {
		users = new HashMap<>();
		tweets = new ArrayList<>();
	}

	public boolean registerUser(String username, String password, String name, String bio) throws UserAlreadyExistsException {
		if (users.containsKey(username)) {
			throw new UserAlreadyExistsException("User with username '" + username + "' already exists");
		}
		User user = new User(username, password, name,bio);
		users.put(username, user);
		return true;
	}

	public User login(String username, String password) throws UserNotFoundException {
		User user = users.get(username);

		if (user == null) {
			throw new UserNotFoundException("User not found.");
		}

		if (user != null && user.getPassword().equals(password)) {
			currentUser = user;
			return user;
		}
		return null;
	}
	
	  public boolean follow(String username) {
        if (currentUser == null) {
            return false;
        }
        User user = users.get(username);
        if (user != null && !currentUser.getFollowings().contains(username)) {
            currentUser.follow(username);
            user.getFollowers().add(currentUser.getUsername());
            return true;
        }
        return false;
    }
	  
	  public boolean unfollow(String username) {
	        if (currentUser == null) {
	            return false;
	        }

	        User user = users.get(username);
	        if (user != null && currentUser.getFollowings().contains(username)) {
	            currentUser.unfollow(username);
	            user.getFollowers().remove(currentUser.getUsername());
	            return true;
	        }
	        return false;
	    }

	public void postTweet(String username, String content) {
		if (users.containsKey(username)) {
			User user = users.get(username);
			user.postTweet(content);
		}
	}

	public User searchUsers(String query) {
	    for (User user : users.values()) {
	        if (user.getUsername().equalsIgnoreCase(query)) {
	            return user;
	        }
	    }
	    return null;
	}
	
	public List<Tweet> searchTweets(String query) {
	    List<Tweet> searchResults = new ArrayList<>();

	    for (User user : users.values()) {
	        List<Tweet> userTweets = user.getTweets();
	        for (Tweet tweet : userTweets) {
	            if (tweet.getContent().toLowerCase().contains(query.toLowerCase())) {
	                searchResults.add(tweet);
	            }
	        }
	    }
	    return searchResults;
	}
	
	public User getProfile(String username) {
	    if (users.containsKey(username)) {
	        return users.get(username);
	    } else {
	        return null;
	    }
	}
	
	public void logout() {
	    currentUser = null;
	    System.out.println("Logged out successfully.");
	}

	public void saveData(String fileName) {
        // Implementation to save data to a file
        DataStore.saveData(users, tweets, fileName);
    }

    public void loadData(String fileName) {
        // Implementation to load data from a file
        DataStore.loadData(users, tweets, fileName);
    }
}
