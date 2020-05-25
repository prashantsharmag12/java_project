package com.semanticsquare.thrillo.entities;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import com.semanticsquare.thrillo.entities.WebLink;
import com.semanticsquare.thrillo.managers.BookmarkManager;

class WeblinkTest {

	@Test
	void testIsKidFriendkyEligiblePornInUrl() {
		// Test 1- porn in url-- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com", "unknown");
		boolean isKidFriendkyEligible = weblink.isKidFriendlyEligible();

		assertFalse("Porn in url- must return false", isKidFriendkyEligible);

	}

	@Test
	void testIsKidFriendkyEligiblePornInTitle() {
		// Test 1- porn in title-- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com", "unknown");
		boolean isKidFriendkyEligible = weblink.isKidFriendlyEligible();

		assertFalse("Porn in title- must return false", isKidFriendkyEligible);

	}

	@Test
	void testIsKidFriendkyEligibleAdultInHost() {
		// Test- adult in host-- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com",
				"unknown");
		boolean isKidFriendkyEligible = weblink.isKidFriendlyEligible();

		assertFalse("adult in host- must return false", isKidFriendkyEligible);

	}

	@Test
	void testIsKidFriendkyEligibleAdultInURLButNotInHost() {
		// Test 4- adult in url but not in host-- true
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",
				"http://www.javaworld.com", "unknown");
		boolean isKidFriendkyEligible = weblink.isKidFriendlyEligible();

		assertTrue("adult in url- must return true", isKidFriendkyEligible);
	}

	@Test
	void testIsKidFriendkyEligibleAdultInTitleOnly() {
		// Test 1- adult in title only-- true
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming adult, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com", "unknown");
		boolean isKidFriendkyEligible = weblink.isKidFriendlyEligible();

		assertTrue("adult in title- must return true", isKidFriendkyEligible);

	}

}
