package org.loutr.whwatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WatcherServer {

	ServerSocket sskt;
	ArrayList<Socket> skts;
	private ArrayList<UpdateWatcher> watchers;
	
	public WatcherServer (ArrayList<UpdateWatcher> watchers) {
		skts = new ArrayList<>();
		this.watchers = watchers;
		
		try {
			sskt = new ServerSocket(6969);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void addWatcher () {
		
		try {
			Socket skt = sskt.accept();
			
			BufferedReader bufferedReader = 
	 	    new BufferedReader(
	 	 	new InputStreamReader(
	 		    skt.getInputStream()));
			
			System.out.println("Got incoming connection from: "+skt.getInetAddress().toString() + " on port " + skt.getPort());
			try {
				String turl = bufferedReader.readLine();
				//System.out.println(turl);
				URL url = new URL(turl);
				System.out.println(url);
				UpdateWatcher upd = new UpdateWatcher(url.toString());
				upd.run();
				watchers.add(upd);
			} catch (MalformedURLException e) {
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Socket> getSockets() {
		return skts;
	}
	
}
