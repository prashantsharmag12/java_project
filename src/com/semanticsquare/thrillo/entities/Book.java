package com.semanticsquare.thrillo.entities;

import java.util.Arrays;

import org.junit.platform.commons.util.StringUtils;

import com.semanticsquare.thrillo.constants.BookGenre;
import com.sematicsquare.thrillo.partner.Shareable;

public class Book extends Bookmark implements Shareable{

	private int publicationYear;
	private String publisher;
	private String[] authors;
	private BookGenre genre;
	private double amazonRating;

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public BookGenre getGenre() {
		return genre;
	}

	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	public double getAmazonRating() {
		return amazonRating;
	}

	public void setAmazonRating(double amazonRating) {
		this.amazonRating = amazonRating;
	}

	@Override
	public String toString() {
		return "Book [publicationYear=" + publicationYear + ", publisher=" + publisher + ", authors="
				+ Arrays.toString(authors) + ", genre=" + genre + ", amazonRating=" + amazonRating + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
		builder.append("<type>Book</type>");
		builder.append("<title>").append(getTitle()).append("</title>");
		StringBuilder builder1 = new StringBuilder();
		for(String aut:authors)
		{
			builder1.append(aut+";");
		}
		builder.append("<author>").append(builder1).append("</author>");
		builder.append("<publisher>").append(publisher).append("</publisher>");
		builder.append("<publicationYear>").append(publicationYear).append("</publicationYear>");
		builder.append("<genre>").append(genre).append("</genre>");
		builder.append("<amazonRating>").append(amazonRating).append("</amazonRating>");
		
		
		builder.append("</item>");
		return builder.toString();
	}

}
