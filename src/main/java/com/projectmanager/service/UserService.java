/*
 * 
 */
package com.projectmanager.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.domain.User;
import com.projectmanager.repository.UserRepository;

@Service
@Transactional
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepo;

	public User addUser(User user) {
		logger.info("Entered Method Name: addUser");
		User i = userRepo.save(user);
		return i;
	}

	public User findUserById(Long Id) {
		logger.info("Entered Method Name: findUserById ");
		Optional<User> userIfExist = userRepo.findById(Id);
		if (userIfExist != null)
			return (User) userIfExist.get();
		else
			return null;
	}

	public List<User> getAllUsers() {
		logger.info("Entered Method Name: getAllUsers ");
		return (List<User>) userRepo.findAll();
	}

	public User deleteUser(User user) {
		logger.info("Entered Method Name: deleteUser ");
		userRepo.delete(user);
		return user;
	}

}
