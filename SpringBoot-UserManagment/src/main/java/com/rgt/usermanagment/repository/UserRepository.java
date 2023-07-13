package com.rgt.usermanagment.repository;

import org.springframework.stereotype.Repository;

import com.rgt.usermanagment.entity.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
	private final String path =  "C:/Users/RGTAdmin/Java-Training/SpringBoot-UserManagment/data/";
	
	/**
	 * This method will save the user data in the file with user object 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	public User save(User user) throws IOException {
		File file = new File(path + user.getId() + ".ser");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(user);
		}
		return user;
	}
	
	/**
	 * This method with return all the user details 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	  public List<User> getAllUsers() throws IOException, ClassNotFoundException {
	        List<User> users = new ArrayList<>();
	        File dir = new File(path);
	        if (dir.exists() && dir.isDirectory()) {
	            for (File file : dir.listFiles()) {
	                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
	                    users.add((User) in.readObject());
	                }
	            }
	        }
	        return users;
	    }

	/**
	 * This method will get the users by id from the file
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User findById(Long id) throws IOException, ClassNotFoundException {
		File file = new File(path + id + ".ser");
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			return (User) in.readObject();
		}
	}
	
	/**
	 * This method with delete the user details total from the file
	 * @param user
	 * @throws IOException
	 */
	public void deleteUser(User user) throws IOException {
		File file = new File(path + user.getId() + ".ser");
		if (file.exists()) {
			if (file.delete()) {
				return;
			} else {
				throw new RuntimeException("Failed to delete user");
			}
		} else {
			throw new IllegalArgumentException("User file not found");
		}
	}
}
