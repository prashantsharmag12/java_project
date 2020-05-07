package com.semanticsquare.thrillo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.semanticsquare.thrillo.DataStore;
import com.semanticsquare.thrillo.entities.Bookmark;
import com.semanticsquare.thrillo.entities.Movie;
import com.semanticsquare.thrillo.entities.UserBookmark;

public class BookMarkDao {
	
	public List<List<Bookmark>> getBookmarks()
	{
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userbookmark) {
		//DataStore.add(userbookmark);
		
try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "peesu");Statement stmt = conn.createStatement();){
			
			//loadUsers(stmt);
			//loadWebLinks(stmt);
			//loadMovies(stmt);
			
			System.out.println("---------hi i am here -----------");
			//loadBooks(stmt);
			
			if(userbookmark.getBookmark() instanceof Bookmark)
			{
				saveUserBookmark(userbookmark,stmt);
			}
			else if(userbookmark.getBookmark() instanceof Movie)
			{
				saveMovieBookmark(userbookmark,stmt);
			}
			else
				{
					saveUserWeblink(userbookmark,stmt);
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void saveUserWeblink(UserBookmark userbookmark, Statement stmt) {
		
		
		String query  = "insert into User_WebLink (user_id, weblink_id) values (" + 
							userbookmark.getUser().getId() + "," + userbookmark.getBookmark().getId() + ")";
		
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void saveMovieBookmark(UserBookmark userbookmark, Statement stmt) {
		
		String query  = "insert into User_Movie (user_id, movie_id) values (" + 
				userbookmark.getUser().getId() + "," + userbookmark.getBookmark().getId() + ")";
try {
stmt.executeUpdate(query);
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
		
	}

	private void saveUserBookmark(UserBookmark userbookmark, Statement stmt) {
		

		String query  = "insert into User_Book (user_id, book_id) values (" + 
							userbookmark.getUser().getId() + "," + userbookmark.getBookmark().getId() + ")";
		
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
