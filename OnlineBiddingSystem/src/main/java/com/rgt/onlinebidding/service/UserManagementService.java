package com.rgt.onlinebidding.service;

import java.util.ArrayList;
import java.util.List;

import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.exception.UserAlreadyExistsException;
import com.rgt.onlinebidding.exception.UserNotFoundException;

public class UserManagementService {
	private List<User> users=new ArrayList<User>();
	private User currentUser;

	public UserManagementService() {
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void createUser(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				throw new UserAlreadyExistsException("User with username '" + username + "' already exists");
			}
		}
		User user = new User(username, password);
		users.add(user);
	}

	public User authenticateUser(String username, String password) throws UserNotFoundException {
	    boolean userFound = false; 
	    for (User user : users) {
	            userFound = true;
	            if (user.getUsername().equals(username) && user.getPassword().equals(password)) 
	            {
					currentUser = user;
					return user;
				}
	    }
	    if (!userFound) {
	        throw new UserNotFoundException("User not found");
	    }
		return null; 
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

}
    
