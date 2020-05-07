package com.semanticsquare.thrillo.managers;

import java.util.List;

import com.semanticsquare.thrillo.constants.Gender;
import com.semanticsquare.thrillo.dao.UserDao;
import com.semanticsquare.thrillo.entities.User;

public class UserManager {
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();
	private UserManager()
	{
		
	}
	
	public static UserManager getInstance()
	{
		return instance;
	}
	
	public List<User> getUsers()
	{
		return dao.getUsers();
	}
	
	public User createUser(long id,String email,String password,String firstName,String lastName,Gender gender,String userType)
	{
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setUserType(userType);
		
		
		return user;
	}

}
