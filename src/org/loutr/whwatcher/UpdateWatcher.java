package org.loutr.whwatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpdateWatcher extends Thread{
	
	private String url = "";
	
	public UpdateWatcher (String url) {
		this.url = url;
	}

	@Override
	public void run() {
		
		super.run();
		ArrayList<Item> lastItems = new ArrayList<>();
		while(true) {
			System.out.println("test");
			ArrayList<Item> items =  WebSiteUtil.ParseWebsite(url);
			if(lastItems.size() == 0) {
				lastItems = (ArrayList<Item>) items.clone();
			}
			System.out.println(lastItems);
			System.out.println("-------------\n}" + items);
			ArrayList<Item> newItems = Item.getNewItems(items, lastItems);
			lastItems = (ArrayList<Item>) items.clone();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date) + ": " + newItems.toString());
			try {
				Thread.sleep(6000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getUrl() {
		return url;
	}
}
