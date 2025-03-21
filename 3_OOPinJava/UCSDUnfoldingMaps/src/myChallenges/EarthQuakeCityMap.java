package myChallenges;

import java.util.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthQuakeCityMap extends PApplet {
	private UnfoldingMap map;
	private UnfoldingMap map2;
	
	public void setup() {
		size(950, 600, OPENGL);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		map2 = new UnfoldingMap(this, 200, 50, 700, 500, new OpenStreetMap.OpenStreetMapProvider());
		map.zoomToLevel(1);
		map2.zoomToLevel(1);
		MapUtils.createDefaultEventDispatcher(this, map);
		MapUtils.createDefaultEventDispatcher(this, map2);
		// create Locations for each point
		Location valLoc = new Location(-38.14f, -73.03f);
		Location alaskaLoc = new Location(61.02f, -147.65f);
		Location sumatraLoc = new Location(3.30f, 95.78f);
		Location japanLoc = new Location(38.322f, 142.369f);
		Location kamchatkaLoc = new Location(52.76, 160.06);		
		
		// create PointFeatures, adding data / properties to each
		PointFeature valEq = new PointFeature(valLoc);
		valEq.addProperty("title",  "Valdiva, Chile");
		valEq.addProperty("magnitude",  "9.5");
		valEq.addProperty("date",  "May 22, 1960");
		valEq.addProperty("year",  1960);
		PointFeature alaskaEq = new PointFeature(alaskaLoc);
		alaskaEq.addProperty("title", "Great Alaska Earthquake");
		alaskaEq.addProperty("magnitude", "9.2");
		alaskaEq.addProperty("date", "March 28, 1964");
		alaskaEq.addProperty("year", 1964);
		PointFeature sumatraEq = new PointFeature(sumatraLoc);
		sumatraEq.addProperty("title", "West Coast of Sumatra");
		sumatraEq.addProperty("magnitude", "9.1");
		sumatraEq.addProperty("date", "December 26, 2004");
		sumatraEq.addProperty("year", 2004);
//		System.out.println(sumatraEq.properties);
		PointFeature japanEq = new PointFeature(japanLoc);
		japanEq.addProperty("title", "East Coast of Honshu, Japan");
		japanEq.addProperty("magnitude", "9.0");
		japanEq.addProperty("date", "March 11, 2011");
		japanEq.addProperty("year", 2011);
		PointFeature kamchatkaEq = new PointFeature(kamchatkaLoc);
		kamchatkaEq.addProperty("title", "Kamchatka");
		kamchatkaEq.addProperty("magnitude", "9.0");
		kamchatkaEq.addProperty("date", "November 4, 1952");
		kamchatkaEq.addProperty("year", 1952);
	
		
		// using PointFeature/Feature class for additional data and List to store locations
		// ArrayList is the object that implements the abstract-data type (interface) List
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add(valEq);
		bigEqs.add(alaskaEq);
		bigEqs.add(sumatraEq);
		bigEqs.add(japanEq);
		bigEqs.add(kamchatkaEq);
		
		List<Marker> markers = new ArrayList<Marker>();
		int yellow = color(255, 255, 0);
		int gray = color(150, 150, 150);
		
		for (PointFeature eq : bigEqs) {
			markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));			
		}
		map.addMarkers(markers);
		
		for (Marker mk : markers) {
			if ((int) mk.getProperty("year") > 2000) {
				mk.setColor(yellow);
			} else {
				mk.setColor(gray);
			}
		}
		
		
//		Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
		
//		SimplePointMarker val = new SimplePointMarker(valLoc);
		// using Marker interface instead
//		Marker val2 = new SimplePointMarker(valLoc);
//		map.addMarker(val);
//		map.addMarker(val2);
//		map.addMarker(valMk);
	}
	
	public void draw() {
		background(10);
		map.draw();
//		map2.draw();
//		addKey();		
	}
}