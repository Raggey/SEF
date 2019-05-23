package Class;
import java.io.*;
import java.util.*;

public class location {
	
	private Map< String,Integer> locationMap; 
	
	public location () {
		locationMap = new HashMap< String,Integer>(); 
	}

	public void addNewLocation(String locationName, int locationPostcode ) {
		locationMap.put(locationName, new Integer(locationPostcode)); 
		return;

	}// end addNewLocation

	public String getLocationByPostcode(int locationPostcode) {
	
//		//Need to perform error checking here
//		String locationName =  locationMap.get(locationPostcode);
//		return locationName;
		return "Need to implement ;) ";
	}
	public int getLocationByName(String locationName) {

		//Need to perform error checking here
		int locationPostcode =  locationMap.get(locationName);
		return locationPostcode;
		
	}
	
	public void printAllLocations() {
		Set< Map.Entry< String,Integer> > st = locationMap.entrySet();
        for (Map.Entry< String,Integer> me:st) 
        { 
           System.out.print(me.getKey()+":"); 
           System.out.println(me.getValue()); 
        } 
	}

}
