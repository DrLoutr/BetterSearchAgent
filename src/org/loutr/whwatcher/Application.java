package org.loutr.whwatcher;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	private ArrayList<UpdateWatcher> watchers;

	public Application () {
		watchers = new ArrayList<>();
		//addURLToWatchers();
		WatcherServer srv = new WatcherServer(watchers);
		
		while(true) {
			srv.addWatcher();
		}
		
	}
	
	private void addURLToWatchers () {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the URL to monitor: ");
		while (true) {
			try {
				UpdateWatcher watcher = new UpdateWatcher(scan.next("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)"));
				watchers.add(watcher);
				watcher.run();
				scan.close();
				break;
			} catch (Exception e){
				System.out.println("Invalid URL. Please also enter http/s:// ...");
				scan.next();
			}
		}
		System.out.println("Got " + watchers.get(watchers.size()-1).getUrl());
		ArrayList<Item> items = WebSiteUtil.ParseWebsite(watchers.get(watchers.size()-1).getUrl());
		
		for(Item item : items) {
			System.out.println(item.toString());
		}
	}
	
}
