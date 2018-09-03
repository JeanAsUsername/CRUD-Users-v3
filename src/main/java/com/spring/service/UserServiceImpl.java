package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.UserRepository;
import com.spring.exceptions.DuplicatedNameException;
import com.spring.exceptions.InvalidNameException;
import com.spring.model.User;

@Transactional
@Service("UserService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAllUsers() throws Exception {
		
		List<User> users = new ArrayList<User>();
		
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User findUserById(Long id) throws Exception {
		return userRepository.findOne(id);
	}
	
	@Override
	public User findUserByUsername(String username) throws Exception {
		return userRepository.findByUsername(username);
	}

	@Override
	public User createNewUser(User commingUser) throws Exception {
		
		String username = commingUser.getUsername();
		byte age = commingUser.getAge();
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		
		// User validation
		
		if(!username.matches("[a-zA-Z]+") || username.length() > 25) {
			throw new InvalidNameException();
		}
		
		if (age > 110 || age < 10) {
			throw new Exception();
		}
		
		for (User user:users) {
			
			// if the user name is already in use
			if (user.getUsername().equalsIgnoreCase(username)) {
				throw new DuplicatedNameException();
			}
		}
		
		return userRepository.save(commingUser);
	}
	
	@Override
	public User updateUser(User commingUser, User oldUser) throws Exception {
		
		String username = commingUser.getUsername();
		byte age = commingUser.getAge();
		
		// User validation
		
		if(!username.matches("[a-zA-Z]+") || username.length() > 25) {
			throw new InvalidNameException();
		}
		
		if (age > 110 || age < 10) {
			throw new Exception();
		}
		
		// the name can't be updated
		if ( !(commingUser.getUsername().equalsIgnoreCase( oldUser.getUsername() )) ) {
			throw new Exception();
		}
		
		return userRepository.save(commingUser);
	}

	@Override
	public void deleteUser(User userToDelete) throws Exception {
		userRepository.delete(userToDelete);
	}
	
	@Override
	public List<User> findUsersByLanguageName(String name) throws Exception {
		
		List<User> users = new ArrayList<User>();
		
		userRepository.findUsersByLanguage(name).forEach(users::add);;
		return users;
		
	}

}
