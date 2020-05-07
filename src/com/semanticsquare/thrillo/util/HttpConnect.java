package com.semanticsquare.thrillo.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpConnect {
	
	public static String download(String sourceUrl) throws IOException
	{
		
		System.out.println("Downloading.....");
		try {
			URL url = new URI(sourceUrl).toURL();
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			int responseCode = con.getResponseCode();
			if(responseCode>=200 && responseCode<300)
			{
				return IOUtil.read(con.getInputStream());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
