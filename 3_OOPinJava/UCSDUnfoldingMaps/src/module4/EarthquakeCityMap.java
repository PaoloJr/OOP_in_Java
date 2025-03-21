package module4;

import java.util.*;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;
import processing.core.PGraphics;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {
	
	// We will use member variables, instead of local variables, to store the data
	// that the setUp and draw methods will need to access (as well as other methods)
	// You will use many of these variables, but the only one you should need to add
	// code to modify is countryQuakes, where you will store the number of earthquakes
	// per country.
	
	// You can ignore this.  It's to get rid of eclipse warnings
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFILINE, change the value of this variable to true
	private static final boolean offline = false;
	
	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	

	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	// The files containing city names and info and country names and info
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";
	
	// The map
	private UnfoldingMap map;
	
	// Markers for each city
	private List<Marker> cityMarkers;
	// Markers for each earthquake
	private List<Marker> quakeMarkers;

	// A List of country markers
	private List<Marker> countryMarkers;
	
	public void setup() {		
		// (1) Initializing canvas and map tiles
		size(1000, 700, OPENGL);
		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom";  // The same feed, but saved August 7, 2015
		}
		else {
//			map = new UnfoldingMap(this, 200, 50, 650, 600, new Google.GoogleMapProvider());
			map = new UnfoldingMap(this, 200, 50, 900, 500, new OpenStreetMap.OpenStreetMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
		    earthquakesURL = "2.5_week.atom";
		}
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// FOR TESTING: Set earthquakesURL to be one of the testing files by uncommenting
		// one of the lines below.  This will work whether you are online or offline
//		earthquakesURL = "test1.atom";
//		earthquakesURL = "test2.atom";
		
		// WHEN TAKING THIS QUIZ: Uncomment the next line
		earthquakesURL = "quiz1.atom";
		
		
		// (2) Reading in earthquake data and geometric properties
	    //     STEP 1: load country features and markers
		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		//     STEP 2: read in city data
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for(Feature city : cities) {
		  cityMarkers.add(new CityMarker(city));
		}
	    
		//     STEP 3: read in earthquake RSS feed
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    quakeMarkers = new ArrayList<Marker>();
	    
	    for(PointFeature feature : earthquakes) {
		  //check if LandQuake
		  if(isLand(feature)) {
		    quakeMarkers.add(new LandQuakeMarker(feature));
		  }
		  // OceanQuakes
		  else {
		    quakeMarkers.add(new OceanQuakeMarker(feature));
		  }
	    }

	    // could be used for debugging
	    printQuakes();
	    
	    // testing outputs here
//	    for (Marker mk : cityMarkers) {
//	    	System.out.println(mk.getProperties());
//	    }
//	    for (Marker mk : countryMarkers) {
//	    	System.out.println(mk.getProperties().toString());
//	    }
//	    for (PointFeature pf : earthquakes) {
//	    	System.out.println(pf.getProperties());
//	    }
//	    for (Marker mk : quakeMarkers) {
//	    	System.out.println(mk.getProperties());
//	    }
	    
	    // (3) Add markers to map
	    //     NOTE: Country markers are not added to the map.  They are used
	    //           for their geometric properties
	    map.addMarkers(quakeMarkers);
	    map.addMarkers(cityMarkers);
	    
	}  // End setup
	
	
	public void draw() {
		background(0);
		map.draw();
		addKey();		
	}
	
	// helper method to draw key in GUI
	// TODO: Update this method as appropriate
	private void addKey() {
		// Remember you can use Processing's graphics methods here
//		fill(255, 250, 240);
//		rect(25, 50, 150, 250, 28);
//		
//		fill(0);
//		textAlign(LEFT, CENTER);
//		textSize(12);
//		text("Earthquake Key", 50, 75);
		
//		fill(color(255, 0, 0));
//		ellipse(50, 125, 15, 15);
//		fill(color(255, 255, 0));
//		ellipse(50, 175, 10, 10);
//		fill(color(0, 0, 255));
//		ellipse(50, 225, 5, 5);
//		
//		fill(0, 0, 0);
//		text("5.0+ Magnitude", 75, 125);
//		text("4.0+ Magnitude", 75, 175);
//		text("Below 4.0", 75, 225);
		
		
		fill(255, 250, 240);
		rect(25, 50, 150, 250, 28);
		
		fill(0);
		textSize(12);
		text("Earthquake Key", 50, 75);
		
		int triSize = 5;
		int x = 60;
		int y = 100;
		fill(218, 112, 214);
	    triangle(x, y - triSize, x - triSize, y + triSize, x + triSize, y + triSize);
	    
	    fill(255, 255, 255);
	    int size = 10;
	    ellipse(60, 120, size, size);
	    rect(55, 135, size, size);
	    
	    fill(255, 255, 0);
	    ellipse(60, 195, size, size);
	    fill(0, 0, 255);
	    ellipse(60, 215, size, size);
	    fill(255, 0, 0);
	    ellipse(60, 235, size, size);
	    fill(255, 255, 255);
	    ellipse(60, 255, size, size);
	    line(50, 265, 70, 245);
	    line(50, 245, 70, 265);
		
		
		fill(0, 0, 0);
		text("City Marker", 75, 105);
		text("Land Quake", 75, 125);
		text("Ocean Quake", 75, 145);
		text("Size ~ Magnitude", 55, 170);
		text("Shallow", 75, 200);
		text("Intermediate", 75, 220);
		text("Deep", 75, 240);
		text("Past Day", 75, 260);
	}

	
	
	// Checks whether this quake occurred on land.  If it did, it sets the 
	// "country" property of its PointFeature to the country where it occurred
	// and returns true.  Notice that the helper method isInCountry will
	// set this "country" property already.  Otherwise it returns false.
	private boolean isLand(PointFeature earthquake) {
		
		
		// Loop over all the country markers.  
		// For each, check if the earthquake PointFeature is in the 
		// country in m.  Notice that isInCountry takes a PointFeature
		// and a Marker as input.  
		// If isInCountry ever returns true, isLand should return true.
		for (Marker m : countryMarkers) {
			// TODO: Finish this method using the helper method isInCountry
			boolean isInCountry = isInCountry(earthquake, m);
			if (isInCountry) {
				return true;
			}			
		}	
		
		// not inside any country
		return false;
	}
	
	/* prints countries with number of earthquakes as
	 * Country1: numQuakes1
	 * Country2: numQuakes2
	 * ...
	 * OCEAN QUAKES: numOceanQuakes
	 * */
	private void printQuakes() 
	{
		// TODO: Implement this method
		// One (inefficient but correct) approach is to:
		//   Loop over all of the countries, e.g. using 
		//        for (Marker cm : countryMarkers) { ... }
		//        
		//      Inside the loop, first initialize a quake counter.
		//      Then loop through all of the earthquake
		//      markers and check to see whether (1) that marker is on land
		//     	and (2) if it is on land, that its country property matches 
		//      the name property of the country marker.   If so, increment
		//      the country's counter.
		
		// Here is some code you will find useful:
		// 
		//  * To get the name of a country from a country marker in variable cm, use:
		//     String name = (String)cm.getProperty("name");
		//  * If you have a reference to a Marker m, but you know the underlying object
		//    is an EarthquakeMarker, you can cast it:
		//       EarthquakeMarker em = (EarthquakeMarker)m;
		//    Then em can access the methods of the EarthquakeMarker class 
		//       (e.g. isOnLand)
		//  * If you know your Marker, m, is a LandQuakeMarker, then it has a "country" 
		//      property set.  You can get the country with:
		//        String country = (String)m.getProperty("country");
		HashMap<String, Integer> allCounts = new HashMap<String, Integer>();
		for (Marker em : quakeMarkers) {
			String country = (String)em.getProperty("country");
			String oceanQuakes = "OCEAN QUAKES";
			 if (country == null) {
			        // Handle ocean quakes
			        if (allCounts.containsKey(oceanQuakes)) {
			            int value = allCounts.get(oceanQuakes);
			            allCounts.put(oceanQuakes, value + 1);
			        } else {
			            allCounts.put(oceanQuakes, 1);
			        }
			    } else {
			        // Handle country-specific quakes
			        if (allCounts.containsKey(country)) {
			            int value = allCounts.get(country);
			            allCounts.put(country, value + 1);
			        } else {
			            allCounts.put(country, 1);
			        }
			    }
			}
		
		for (String s : allCounts.keySet()) {
			int value = allCounts.get(s);
			System.out.println(s + ": " + value);
		}
	}
	
	
	
	// helper method to test whether a given earthquake is in a given country
	// This will also add the country property to the properties of the earthquake 
	// feature if it's in one of the countries.
	// You should not have to modify this code
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();

		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if(country.getClass() == MultiMarker.class) {
				
			// looping over markers making up MultiMarker
			for(Marker marker : ((MultiMarker)country).getMarkers()) {
					
				// checking if inside
				if(((AbstractShapeMarker)marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
						
					// return if is inside one
					return true;
				}
			}
		}
			
		// check if inside country represented by SimplePolygonMarker
		else if(((AbstractShapeMarker)country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));
			
			return true;
		}
		return false;
	}

}
