package com.test;

public class GPS_location {
	double lat;
	double lon;
	public GPS_location(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}
	public GPS_location(){
		
		
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	
	

}
