package org.loutr.whwatcher;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebSiteUtil {

	public static void printWebSite (String url) {
		
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		  scanner.close();
		}catch ( IOException e ) {
		    e.printStackTrace();
		}
		System.out.println(content);
	}
	
	public static void printWebSiteV2 (String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			//System.out.println(doc.outerHtml());
			System.out.println(doc.outerHtml());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Item> ParseWebsite (String url) {
		Document doc;
		ArrayList<Item> retVal = new ArrayList<>();
		try {
			doc = Jsoup.connect(url).get();
			//System.out.println(doc.outerHtml());
			//System.out.println(doc.getElementById("skip-to-resultlist"));
			Elements list = doc.getElementById("skip-to-resultlist").getElementsByClass("Box-sc-wfmb7k-0 jYFQjC");
			for (Element e : list) {
				Item temp = new Item(
						e.getElementsByClass("Text-sc-10o2fdq-0 bsRRaI").text(),
						e.getElementsByClass("Text-sc-10o2fdq-0 kKghqU").text(),
						e.getElementsByClass("Text-sc-10o2fdq-0 fTrhKU").text(),
						e.getElementsByClass("Text-sc-10o2fdq-0 jzPzUu").text(),
						e.getElementsByClass("Text-sc-10o2fdq-0 eiFRDf").text());
				if(temp.isValidItem())
					retVal.add(temp);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retVal;
	}
}
