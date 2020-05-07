package com.semanticsquare.thrillo;

import java.util.ArrayList;
import java.util.List;

import com.semanticsquare.thrillo.entities.Bookmark;
import com.semanticsquare.thrillo.entities.User;
import com.semanticsquare.thrillo.managers.BookmarkManager;
import com.semanticsquare.thrillo.managers.UserManager;

public class Launch {

	private static List<User> users = new ArrayList<>();
	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	
	private static void loadData() {
		
		System.out.println("1. loading .... ");
		
		DataStore.loadData();
		
		
		
		users = UserManager.getInstance().getUsers();
		bookmarks= BookmarkManager.getInstance().getBookmarks();
		
		printUserData();
		
		printBookmardData();
		
	}
	
	private static void printBookmardData() {
		
		for(List<Bookmark> bookmarkList: bookmarks)
		{
			for(Bookmark bookmark:bookmarkList)
			{
				System.out.println(bookmark);
			}
		}
		
	}

	private static void printUserData() {
		for(User user:users)
		{
			System.out.println(user);
		}
		
	}

	private static void start() {
		System.out.println("2. book marking ");
		
		for(User user:users)
		{
			View.browse(user, bookmarks);
		}
		
	}
	public static void main(String[] args) {
		//System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		
		loadData();
		start();

	}

	


}
