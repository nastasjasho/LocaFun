package com.example.dugmici.model;

import android.location.Location;

public class FunLocation {

	private Location location;
	private String details;
	private int funPoints;

	public FunLocation(Location location, String details, int funPoints) {
		super();
		this.location = location;
		this.details = details;
		this.funPoints = funPoints;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getFunPoints() {
		return funPoints;
	}

	public void setFunPoints(int funPoints) {
		this.funPoints = funPoints;
	}

}
