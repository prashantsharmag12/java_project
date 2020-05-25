package com.semanticsquare.thrillo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.semanticsquare.thrillo.constants.BookGenre;
import com.semanticsquare.thrillo.constants.Gender;
import com.semanticsquare.thrillo.constants.KidFriendlyStatus;
import com.semanticsquare.thrillo.constants.MovieGenre;
import com.semanticsquare.thrillo.constants.UserType;
import com.semanticsquare.thrillo.entities.Bookmark;
import com.semanticsquare.thrillo.entities.User;
import com.semanticsquare.thrillo.entities.UserBookmark;
import com.semanticsquare.thrillo.managers.BookmarkManager;
import com.semanticsquare.thrillo.managers.UserManager;
import com.semanticsquare.thrillo.util.IOUtil;

public class DataStore {

	
	private static List<User> users = new ArrayList<>();
	public static List<User> getUsers() {
		return users;
	}

	
	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	
	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	private static List<UserBookmark> userBookmarks = new ArrayList<>();
	private static int bookmarkIndex;
	
	public static void loadData() {
		/*loadUsers();
		loadWebLinks();
		loadMovies();*/
		//loadBooks();
		
		
		
		System.out.println("ppppppppppppppppppppppppppppppppppppppppppppp");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "peesu");Statement stmt = conn.createStatement();){
			
			//loadUsers(stmt);
			//loadWebLinks(stmt);
			//loadMovies(stmt);
			
			System.out.println("---------hi i am here -----------");
			loadBooks(stmt);
			//loadUsers(stmt);
			//loadWebLinks(stmt);
			loadMovies(stmt);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

	private static void loadMovies(Statement stmt) throws SQLException {
		
		String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
				+ " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
				+ "where m.id = ma.movie_id and ma.actor_id = a.id and "
				      + "m.id = md.movie_id and md.director_id = d.id group by m.id";
		ResultSet rs = stmt.executeQuery(query);
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		while (rs.next()) {
			
			 //System.out.println(" y y  y y y y y ");
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int releaseYear = rs.getInt("release_year");
			String[] cast = rs.getString("cast").split(",");
			String[] directors = rs.getString("directors").split(",");			
			int genre_id = rs.getInt("movie_genre_id");
			MovieGenre genre = MovieGenre.values()[genre_id];
			double imdbRating = rs.getDouble("imdb_rating");
			
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, "", releaseYear, cast, directors, genre, imdbRating/*, values[7]*/);
    		bookmarkList.add(bookmark); 
		}
		bookmarks.add(bookmarkList);


		
		
	}

	private static void loadWebLinks(Statement smt) {
		
		String query = "select * from Weblink";
		try {
			ResultSet rs = smt.executeQuery(query);
			List<Bookmark> bookmarkList = new ArrayList<>();
			while (rs.next()) {
				long id = rs.getLong(1);
				String title = rs.getString(2);
				String url = rs.getString(3);
				String host = rs.getString(4);
				int kidFriendlyStatusId = rs.getInt(5);
				KidFriendlyStatus kidFriendlyStatus = KidFriendlyStatus.values()[kidFriendlyStatusId];
				Date createdDate = rs.getDate("created_date");
				Bookmark bookmark = BookmarkManager.getInstance().createWebLink(id, title, url, host, "");
				bookmarkList.add(bookmark);
			}
			bookmarks.add(bookmarkList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static void loadUsers(Statement smt) {
		
		String query = "select * from User";
		try {
			ResultSet rs = smt.executeQuery(query);
			while (rs.next()) {
				long id = rs.getLong(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				int genderId = rs.getInt(6);
				Gender gender = Gender.values()[genderId];
				int userTypeId = rs.getInt(7);
				
				UserType userType = UserType.values()[userTypeId];
				Date createdDate = rs.getDate("created_date");
				User user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender,
						userType);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void loadUsers() {
		//String[] data = new String[TOTAL_USER_COUNT];
		List<String> data = new ArrayList<>();
//		String[] data = new String[TOTAL_USER_COUNT];
		String fileName = "User.txt";
		IOUtil.read(data, fileName);
		for (String row : data) {
			String[] values = row.split("\t");
			Gender gender = Gender.MALE;
			if (values[5].equals("F"))
				gender = Gender.FEMALE;
			else if (values[5].equals("T"))
				gender = Gender.TRANSGENDER;
			User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3],
					values[4], gender, UserType.valueOf(values[6]));
			users.add(user);
		}
	}
	
	private static void loadWebLinks() {
		
		List<String> data = new ArrayList<>();
		String fileName = "WebLink.txt";
		IOUtil.read(data, fileName);
		List<Bookmark> bookmarkList = new ArrayList();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
					values[2], values[3], values[4]);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}
	
	private static void loadMovies() {
		List<String> data = new ArrayList<>();
		String fileName = "Movie.txt";
		IOUtil.read(data, fileName);
		List<Bookmark> bookmarkList = new ArrayList();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",
					Integer.parseInt(values[2]), values[3].split(","), values[4].split(","),
					MovieGenre.valueOf(values[5]), Double.parseDouble(values[6]));
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}
	
	private static void loadBooks(Statement stmt) throws SQLException {		    	
		
		String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
				+ " from Book b, Publisher p, Author a, Book_Author ba "
				+ "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
		
		ResultSet rs =stmt.executeQuery(query);
		
		//System.out.println(rs);
    	List<Bookmark> bookmarkList = new ArrayList<>();
    	while(rs.next()) {
    		
    		
    		
    		long id = rs.getLong("id");
			String title = rs.getString("title");
			int publicationYear = rs.getInt("publication_year");
			String publisher = rs.getString("name");		
			String[] authors = rs.getString("authors").split(",");			
			int genre_id = rs.getInt("book_genre_id");
			BookGenre genre = BookGenre.values()[genre_id];
			double amazonRating = rs.getDouble("amazon_rating");
			
			Date createdDate = rs.getDate("created_date");
			System.out.println("createdDate: " + createdDate);
			Timestamp timeStamp = rs.getTimestamp(8);
			System.out.println("timeStamp: " + timeStamp);
			
			
			//System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);
    		
    		
    		bookmarkList.add(BookmarkManager.getInstance().createBook(id, title, publicationYear, publisher, authors, genre, amazonRating)/*, values[7]*/);
    	}
    	bookmarks.add(bookmarkList);
    }	

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}	
}
