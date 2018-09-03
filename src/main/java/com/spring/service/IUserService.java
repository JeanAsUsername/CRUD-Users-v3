package com.spring.service;

import java.util.List;

import com.spring.model.User;

public interface IUserService {
	
	public List<User> findAllUsers() throws Exception;
	
	public User findUserById(Long id) throws Exception;
	
	public User findUserByUsername(String username) throws Exception;
	
	public User createNewUser(User commingUser) throws Exception;
	
	public User updateUser(User commingUser, User oldUser) throws Exception;
	
	public void deleteUser(User userToDelete) throws Exception;
	
	public List<User> findUsersByLanguageName(String name) throws Exception;

}
