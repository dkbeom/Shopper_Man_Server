package com.example.shopperman.service;

import java.util.List;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.RequesterLocation;

public interface LocationService {
	
	// get Location by ID
	List<Location> getLocationsById(Integer id);
	
	// get Locations by Requester Name
	List<Location> getLocationsByRequesterName(String requesterName);
	
	// get LocationList
	List<Location> getLocationList();
	
	// set Location
	boolean setLocation(Location location);
	
	// set MarketLocation
	boolean setMarketLocation(MarketLocation marketlocation);
	
	// set RequesterLocation
	boolean setRequesterLocation(RequesterLocation requesterLocation);
}
