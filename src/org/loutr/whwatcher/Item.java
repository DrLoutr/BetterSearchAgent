package org.loutr.whwatcher;

import java.util.ArrayList;

public class Item implements Comparable<Item>{

	private String name, description, place, time;
	private String price;

	public Item(String name, String description, String place, String time, String price) {
		this.name = name;
		this.description = description;
		this.place = place;
		this.time = time;
		this.price = price;
	}
	
	public static ArrayList<Item> getNewItems (ArrayList<Item> newList, ArrayList<Item> lastList) {
		
		ArrayList<Item> retVal = new ArrayList<>();
		
		int i = 0;
		while (i<newList.size() &&  newList.get(i).compareTo(lastList.get(0)) != 0) {
			retVal.add(newList.get(i));
			i++;
		}
		
		return retVal;
	}
	
	public boolean isValidItem() {
		return !name.isEmpty() && !description.isEmpty() && !place.isEmpty() && !time.isEmpty() && !price.isEmpty();
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", place=" + place + ", time=" + time
				+ ", price=" + price + "]";
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getPlace() {
		return place;
	}

	public String getTime() {
		return time;
	}

	@Override
	public int compareTo(Item o) {
		if (this.name.equals(o.name) && this.description.equals(o.description) && this.place.equals(o.place) && this.price.equals(o.price) && this.time.equals(o.time)) {
				return 0;
		}
		return -1;
	}
	
}
