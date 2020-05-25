package com.semanticsquare.thrillo.managers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.semanticsquare.thrillo.constants.BookGenre;
import com.semanticsquare.thrillo.constants.KidFriendlyStatus;
import com.semanticsquare.thrillo.constants.MovieGenre;
import com.semanticsquare.thrillo.dao.BookMarkDao;
import com.semanticsquare.thrillo.entities.Book;
import com.semanticsquare.thrillo.entities.Bookmark;
import com.semanticsquare.thrillo.entities.Movie;
import com.semanticsquare.thrillo.entities.User;
import com.semanticsquare.thrillo.entities.UserBookmark;
import com.semanticsquare.thrillo.entities.WebLink;
import com.semanticsquare.thrillo.util.HttpConnect;
import com.semanticsquare.thrillo.util.IOUtil;

public class BookmarkManager {
	
	
	private static BookmarkManager instance = new BookmarkManager();
	private static BookMarkDao dao = new BookMarkDao();
	private BookmarkManager() {
		
	}
	
	public static BookmarkManager getInstance()
	{
		return instance;
	}
	
	/*public WebLink createWebLink(long id,String title,String url,String host)
	{
		
		WebLink weblink = new WebLink();
		
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);
		
		
		return weblink;
	}*/
	
	public WebLink createWebLink(long id, String title, String url, String host, String profileUrl) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setProfileUrl(profileUrl);
		weblink.setUrl(url);
		weblink.setHost(host);
		return weblink;
	}
	
	public Book createBook(long id,String title,int publicationYear, String publisher,String[] authors,BookGenre genre,double amazonRating)
	{
		Book book = new Book();
		
		
		book.setId(id);
		book.setTitle(title);
		book.setAmazonRating(amazonRating);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		
		return book;
	}
	
	public Movie createMovie(long id,String title,String profileUrl,int releaseYear,String[] cast,String[] directors,MovieGenre genre,double imdbRating)
	{
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);
		
		return movie;
	}
	
	public List<List<Bookmark>> getBookmarks()
	{
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userbookmark = new UserBookmark();
		userbookmark.setUser(user);
		userbookmark.setBookmark(bookmark);
		
		if(bookmark instanceof WebLink)
		{
			try {
				String url = ((WebLink)bookmark).getUrl();
				if(!url.endsWith(".pdf"))
				{
					String webpage = HttpConnect.download(url);
					if(webpage!=null)
					{
						IOUtil.write(webpage,bookmark.getId());
					}
				}
				
			}
			catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		dao.saveUserBookmark(userbookmark);
		
		
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		System.out.println("kid friendly status " + kidFriendlyStatus + ", " +"Marked by" + user.getEmail()+ ", "+ bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		System.out.println(" data to be shared  ");
		
		if(bookmark instanceof Book)
		{
			System.out.println(((Book)bookmark).getItemData());
		}
		else if(bookmark instanceof WebLink)
		{
			System.out.println(((WebLink)bookmark).getItemData());
		}
		
		
	}
	
	

}
