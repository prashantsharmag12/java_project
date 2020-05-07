package com.semanticsquare.thrillo.dao;

import java.util.List;

import com.semanticsquare.thrillo.DataStore;
import com.semanticsquare.thrillo.entities.User;

public class UserDao {
	public List<User> getUsers()
	{
		return DataStore.getUsers();
	}

}
