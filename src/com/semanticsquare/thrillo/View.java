package com.semanticsquare.thrillo;

import java.util.List;

import com.semanticsquare.thrillo.constants.KidFriendlyStatus;
import com.semanticsquare.thrillo.constants.UserType;
import com.semanticsquare.thrillo.controller.BookmarkController;
import com.semanticsquare.thrillo.entities.Bookmark;
import com.semanticsquare.thrillo.entities.User;
import com.sematicsquare.thrillo.partner.Shareable;

public class View {
	
	
	public static void browse(User user,List<List<Bookmark>> bookmarks)
	{
		System.out.println(user.getEmail()+  " is browsing items ...");
		int bookmarkCount = 0;
		
		for(List<Bookmark> bookmarkList:bookmarks)
		{
			for(Bookmark bookmark:bookmarkList)
			{
				
			
				
					boolean isBookMarked = getBookMarkDecision(bookmark);
					if(isBookMarked)
					{
						bookmarkCount++;
					
					
					BookmarkController.getInstance().saveUserBookmark(user,bookmark);
					
					System.out.println("New item bookmarked " + bookmark);
					
					
				}
			
				
				
				
				/*if(user.getUserType()==UserType.EDITORIAL || user.getUserType() == UserType.CHIEF_EDITORIAL)
				{
					if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN))
					{
						String kidFriendlyStatus  = getKidFriendlyStatusDecision(bookmark);
						if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN))
						{
							BookmarkController.getInstance().setKidFriendlyStatus(user,kidFriendlyStatus,bookmark);
							
						}

					}
					
					if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable)
					{
						boolean isShared = getShareDecision();
						
						if(isShared)
						{
							BookmarkController.getInstance().share(user,bookmark);
						}
					}

				}*/
			
		}
		}
		
		
		
		
		
	}

	private static boolean getShareDecision() {
		
		return Math.random()<0.5?true:false;
		
	}

	private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
      
		
		double val = Math.random();
		
		if(val<0.4)
			return KidFriendlyStatus.APPROVED;
		
		if(val>=0.5 && val<0.8)
			return KidFriendlyStatus.REJECTED;
		
		return KidFriendlyStatus.UNKNOWN;
		
	}

	private static boolean getBookMarkDecision(Bookmark bookmark) {
		
		
		return Math.random()<0.5?true:false;
		
	}
	
	

}
