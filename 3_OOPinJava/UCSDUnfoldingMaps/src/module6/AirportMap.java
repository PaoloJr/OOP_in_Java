package module6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	HashMap<Integer, ArrayList<Marker>> airportRouteCounts;
	
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;
//	private CommonMarker countrySelection;
	
	public void setup() {
		// setting up PApplet
		size(1200,800, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 300, 50, 900, 700);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		 airportRouteCounts = new HashMap<Integer, ArrayList<Marker>>();
		
		// create markers from features
		for(PointFeature feature : features) {
			
			// put airport in hashmap with OpenFlights unique id for key
			int airportId = Integer.parseInt(feature.getId());
			Location airportLocation = feature.getLocation();
//			System.out.println("airportId" + airportId + " airportLocation: " + airportLocation);
			
			AirportMarker m = new AirportMarker(feature);
			m.setRadius(5);
			airports.put(airportId, airportLocation);
			airportList.add(m);
			airportRouteCounts.put(airportId, new ArrayList<Marker>());
			
//			System.out.println(feature.getProperties());				
		}
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		
		for(ShapeFeature route : routes) {
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
//			System.out.println(sl.getProperties());
			airportRouteCounts.get(source).add(sl);
			
//			System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			routeList.add(sl);
		}
		
		// Set numRoutes for each AirportMarker
		for (Marker marker : airportList) {
		        AirportMarker airportMarker = (AirportMarker) marker;
		        int airportId = Integer.parseInt(airportMarker.getAirportID());
		        
		        // Check if airportRouteCounts contains the airportId
		        if (airportRouteCounts.containsKey(airportId)) {
		            int numRoutes = airportRouteCounts.get(airportId).size();
		            airportMarker.setNumRoutes(numRoutes);
		        } else {
		            System.out.println("No route count found for airportId: " + airportId);
		        }
	    	}
	
		// FOR DEBUGGING
//		for (Integer i : airportRouteCounts.keySet()) {
//			System.out.println(i + " --> " + airportRouteCounts.get(i).size());
//			ArrayList<Marker> markers = airportRouteCounts.get(i);
//			for (Marker marker : markers) {
//				System.out.println(marker.getProperties());
//			}
//		} 

//		for (Marker amk : airportList) {
//			System.out.println(amk.getProperties());
//		}
		
//		for (Marker rmk : routeList) {
//			System.out.println(rmk.getProperties());
//		}
		
//		for(ShapeFeature route : routes) {
//			System.out.println(route.getLocations() + " --> " + route.getProperties());
//		}
		
		map.addMarkers(routeList);
		map.addMarkers(airportList);		
		
	}
	
	public void draw() {
		background(0);
		map.draw();
        addKey();
    }
	
			
	private void addKey() {	
		fill(255, 250, 240);
		
		int xbase = 25;
		int ybase = 50;
		int keyWidth = 200;
		int keyHeight = 500;
		int red = color(255, 0, 0);
		int yellow = color(255, 255, 0);
		int blue = color(0, 0, 255);
		String textC = "Click 'c' for Canada";
		String textU = "Click 'u' for US";
		String textI = "Click 'i' for Italy";
		String textA = "Click 'a' for All";
		String textR = "Click 'r' for routes";
		String hover = "Hover over airport for more details";
		String click = "Click an airport to see routes";
		
		rect(xbase, ybase, keyWidth, keyHeight, 20);
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(16);
		text("Global Airports", xbase + 25, ybase + 25);
		
		fill(red);
		int circle_xbase = xbase + 35;
		int circle_ybase = ybase + 50;
		ellipse(circle_xbase, circle_ybase + 10, 10, 10);
		fill(yellow);
		ellipse(circle_xbase, circle_ybase + 40, 10, 10);
		fill(blue);
		ellipse(circle_xbase, circle_ybase + 70, 10, 10);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		textSize(14);
		text("Routes > 50", circle_xbase + 15, circle_ybase + 10);
		text("Routes < 5", circle_xbase + 15, circle_ybase + 40);
		text("Routes 5 - 50", circle_xbase + 15, circle_ybase + 70);
		text("------------------------", circle_xbase, circle_ybase + 95);
		text(textC, circle_xbase, circle_ybase + 125);
		text(textU, circle_xbase, circle_ybase + 155);
		text(textI, circle_xbase, circle_ybase + 185);
		text(textA, circle_xbase, circle_ybase + 215);
		text(textR, circle_xbase, circle_ybase + 245);
		text(hover, xbase + 20, circle_ybase + 255, 165, 165);
		text(click, xbase + 20, circle_ybase + 315, 175, 175);
		
	}
	
	
	public void mouseClicked() {
		if (lastClicked != null) {
			unhideMarkers();
			lastClicked = null;
		} else if (lastClicked == null) {
			checkMarkersForClick();
		}
	}
	
	private void unhideMarkers() {
		for (Marker marker : airportList) {
			marker.setHidden(false);
		}
		
		for (Marker marker : routeList) {
			marker.setHidden(false);
		}
	}
	
	public void mouseMoved()
	{
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(airportList);
	}

	private void selectMarkerIfHover(List<Marker> markers)
	{
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			CommonMarker marker = (CommonMarker)m;
			if (marker.isInside(map,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}
	
	private void checkMarkersForClick() {
		for (Marker marker : airportList) {
			if (!marker.isHidden() && marker.isInside(map, mouseX, mouseY)) {
				lastClicked = (AirportMarker)marker;
				System.out.println(lastClicked.getProperties());
				
				// get ID of clicked airport
				int selectedAirport = Integer.parseInt(((AirportMarker) lastClicked).getAirportID());
				
				// Hide all routes and airports initially
	            for (Marker route : routeList) {
	                route.setHidden(true);
	            }

	            for (Marker airport : airportList) {
	                airport.setHidden(true);
	            }

	            // Show the clicked airport
	            lastClicked.setHidden(false);
				
				 // show routes for the clicked airport:
              if (airportRouteCounts.containsKey(selectedAirport)) {
            	  ArrayList<Marker> routesForSelectedAirport = airportRouteCounts.get(selectedAirport);
            	  for (Marker route : routesForSelectedAirport) {
            		  route.setHidden(false);
            		  
            		  // show the destination airport
            		  int dest = Integer.parseInt((String) route.getProperty("destination"));
            		  
            		  for (Marker airport : airportList) {
            			  AirportMarker apm = (AirportMarker) airport;
            			  int airportId = Integer.parseInt(apm.getAirportID());
            			  if (airportId == dest) {
            				  airport.setHidden(false);
            			  }
            		  }
            	  }
              }
				return;
			}
		}
	}
	
	public void keyPressed() {
//		if (countrySelection != null) return;
		
		if (key == 'c') {
			System.out.println("Displaying Canadian airports...");
			for (Marker marker : airportList) {
				String country = marker.getStringProperty("country").replace("\"", "");
				if (!country.equalsIgnoreCase("canada")) {
					marker.setHidden(true);
				}
			}
		} else if (key == 'i') {
			System.out.println("Displaying Italian airports");
			for (Marker marker : airportList) {
				String country = marker.getStringProperty("country").replace("\"", "");
				if (!country.equalsIgnoreCase("Italy")) {
					marker.setHidden(true);
				}
			}
		} else if (key == 'u') {
			System.out.println("Displaying US airports");
			for (Marker marker : airportList) {
				String country = marker.getStringProperty("country").replace("\"", "");
				if (!country.equalsIgnoreCase("United States")) {
					marker.setHidden(true);
				}
			}
		} else if (key == 'a') {
			System.out.println("Displaying all airports");
			for (Marker marker : airportList) {
					marker.setHidden(false);
				}
		} else if (key == 'r') {
			System.out.println("Toggling route visibility");
			boolean anyRouteVisible = false;
			for (Marker marker : routeList) {
				if(!marker.isHidden()) {
					anyRouteVisible = true;
					break;
				}
			}
			for (Marker mk : routeList) {
				mk.setHidden(anyRouteVisible);
			}
		}
	}
}
