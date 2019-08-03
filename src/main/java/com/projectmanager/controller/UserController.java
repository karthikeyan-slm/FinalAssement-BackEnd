package com.projectmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.domain.User;
import com.projectmanager.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/pmapp/userAction", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
		
		@Autowired
		private UserService userService;
		
		@RequestMapping(value="/addUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		public User addUser(@RequestBody User user)throws Exception
		{
			logger.info(" Entered addUser Method in UserController");
			userService.addUser(user);		
			return user;
		}
		
		@RequestMapping(value="/getAllUsers", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public List<User> getAllUsers() throws Exception
		{
			List<User> userList = null;
			logger.info(" Entered getAllUser Method in UserController");
			userList=userService.getAllUsers();
			return  userList;
		}
		
		@RequestMapping(value="/deleteUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		public User deleteUser(@RequestBody User user) throws Exception
		{
			User returned = null;
			logger.info(" Entered deleteUser Method in UserController");
			returned=userService.deleteUser(user);
			return  returned;
		}
		
		@RequestMapping(value="/getUserById/{userId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public User getUserById(@PathVariable(name="userId")String userId ) throws Exception
		{
			logger.info(" Entered getUserById Method in UserController");
			logger.info("User Id Searched for: " + userId);
			User user = userService.findUserById(Long.parseLong(userId));
			return user;
		}
	    
		@RequestMapping(value="/updateUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		public User updateUser(@RequestBody User user) throws Exception  
		{
			logger.info(" Entered updateUser Method in UserController");
		    userService.addUser(user);
			return  user;
		}
	}
