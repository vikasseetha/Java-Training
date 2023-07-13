package com.rgt.usermanagment.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.usermanagment.entity.User;
import com.rgt.usermanagment.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
    @Autowired
    UserService userService;
    
    @PostMapping
    public User createUser(@RequestBody User user) throws IOException
    {
    	return userService.createUser(user);
    }
    
    @GetMapping
    public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		return userService.getAllUsers();
       
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws IOException, ClassNotFoundException {
        return userService.getUser(id);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) throws IOException, ClassNotFoundException {
        return userService.updateUser(id, user);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) throws IOException, ClassNotFoundException {
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }   
}
