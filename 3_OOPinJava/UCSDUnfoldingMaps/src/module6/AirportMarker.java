package module6;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
//	public List<SimpleLinesMarker> routes;
	private ArrayList<Marker> destinations;
	
	private String cityAndCountry;
	private String airportName;
	private String airportCode;
	private String airportAltitude;
	private String airportID;
	private int numRoutes;
	private String airportText = "Airport Name: ";
	private String airportCodeText = "Airport Code: ";
	private String airportIdText = "Airport ID: ";
	private String airportAltitudeText = "Altitude: ";
	private String routesText = "# of routes: ";
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		this.destinations = new ArrayList<Marker>();
		this.cityAndCountry = getCity().replace("\"", "") + ", " + getCountry().replace("\"", "");
		this.airportName = getAirportName().replace("\"", "");
		this.airportCode = getCode().replace("\"", "");
		this.airportAltitude = getAltitude().replace("\"", "");
		this.airportID = city.getId();
		this.numRoutes = 0;
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		if (numRoutes < 5) {
			pg.fill(255, 255, 0);
		} else if (numRoutes > 50) {
			pg.fill(255, 0, 0);
		} else {
			pg.fill(0, 0, 255);
		}
		pg.ellipse(x, y, 5, 5);		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		float cityAndCountryText = pg.textWidth(cityAndCountry + airportName) + 6;
		
		pg.pushStyle();
		pg.rectMode(PConstants.CORNER);
		
		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y + 15, cityAndCountryText, 110, 5);
		
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0);
		pg.text(cityAndCountry, x + 3 , y + 18);
		pg.text(airportText, x + 3, y + 35);
		pg.text(airportName, x + pg.textWidth(airportText) + 5, y + 35);
		pg.text(airportCodeText, x + 3, y + 52);
		pg.text(airportCode, x + pg.textWidth(airportCodeText) + 5, y + 52);
		pg.text(airportIdText, x + 3, y + 69);
		pg.text(airportID, x + pg.textWidth(airportIdText) + 5, y + 69);
		pg.text(airportAltitudeText, x + 3, y + 86);
		pg.text(airportAltitude, x + pg.textWidth(airportAltitudeText) + 5, y + 86);
		pg.text(routesText, x + 3, y + 103);
		pg.text(numRoutes, x + pg.textWidth(routesText) + 5, y + 103);		
		
		pg.popStyle();		
	}
	
	private String getCity() {
		return getStringProperty("city");
	}
	
	private String getCountry()	{
		return getStringProperty("country");
	}
	
	private String getAirportName()	{
		return getStringProperty("name");
	}
	
	public String getCode() {
		return getStringProperty("code");
	}
	
	public String getAirportID() {
		return this.airportID;
	}
	
	public String getAltitude() {
		return getStringProperty("altitude");
	}
	
	 public void setNumRoutes(int numRoutes) {
	        this.numRoutes = numRoutes;
	    }
	
	public void addDestination(AirportMarker destination) {
		this.destinations.add(destination);
	}
    
    public ArrayList<Marker> getDestinations() {
    	return destinations;
    }
	
	public int getNumberOfDestinations() {
		return destinations.size();
	}
		
}
	
