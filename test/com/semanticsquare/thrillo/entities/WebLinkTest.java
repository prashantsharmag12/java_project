package com.semanticsquare.thrillo.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillo.managers.BookmarkManager;
public class WebLinkTest {

	
	@Test
	public void isKidFriendlyEligible() {
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com");
		
		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		
		//isKidFriendlyEligible= false;
		assertFalse(isKidFriendlyEligible);
		
		
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		 isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		
		assertFalse(isKidFriendlyEligible);
		
		
		
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.adult.com");
		
		 isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		
		assertFalse(isKidFriendlyEligible);
		
		
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",
				"http://www.javaworld.com");
		
		 isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		
		assertTrue(isKidFriendlyEligible);
		
		
		
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming adult, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		 isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		
		assertTrue(isKidFriendlyEligible);
		
		
		
		
		
		
		

		
	}

}
