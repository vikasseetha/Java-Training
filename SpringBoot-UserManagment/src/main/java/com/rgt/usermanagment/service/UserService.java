package com.rgt.usermanagment.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgt.usermanagment.entity.User;
import com.rgt.usermanagment.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) throws IOException {
		user.setId(System.currentTimeMillis());
		return userRepository.save(user);
	}

	public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		return userRepository.getAllUsers();
	}

	public User getUser(Long id) throws IOException, ClassNotFoundException {
		return userRepository.findById(id);
	}

	public User updateUser(long id, User user) throws IOException, ClassNotFoundException {
		User existingUser = userRepository.findById(id);
		if (existingUser == null) {
			throw new IllegalArgumentException("User not found");
		}
		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return userRepository.save(existingUser);
	}

	public String deleteUser(long id) throws IOException, ClassNotFoundException {
		User existingUser = userRepository.findById(id);
		if (existingUser == null) {
			throw new IllegalArgumentException("User not found");
		}
		userRepository.deleteUser(existingUser);
		return "User deleted successfully";
	}
}
