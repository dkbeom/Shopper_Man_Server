package com.example.shopperman.service;

import java.util.List;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.LocationContainer;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.RequesterLocation;

public interface LocationService {
	
	// get LocationsList
	List<LocationContainer> getLocationsList();
	
	// get Locations by ID
	LocationContainer getLocationsById(Integer id);
	
	// get LocationsList by Requester Name
	List<LocationContainer> getLocationsListByRequesterName(String requesterName);
	
	// set Location
	boolean setLocation(Location location);
}
