package com.rgt.usermanagment.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgt.usermanagment.entity.User;
import com.rgt.usermanagment.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;
    
	/**
	 * In this method Admin can access all the user details with help of the spring security
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		return userRepository.getAllUsers();
	}
	
	
    /**
     * This method with return users by id from the file 
     * @param id
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public User getUser(Long id) throws IOException, ClassNotFoundException {
		return userRepository.findById(id);
	}
	
	/**
	 * In this method Admin can have the permission to delete the user details with the help of admin username password
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String deleteUser(long id) throws IOException, ClassNotFoundException {
		User existingUser = userRepository.findById(id);
		if (existingUser == null) {
			throw new IllegalArgumentException("User not found");
		}
		userRepository.deleteUser(existingUser);
		return "User deleted successfully";
	}

}
