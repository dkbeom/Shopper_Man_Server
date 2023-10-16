package com.example.shopperman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopperman.dao.LocationDao;
import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.RequesterLocation;

@Service("locationService")
public class LocationServiceImp implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	// ---------------------------------------------------------------------------------
	
	@Override
	public List<Location> getLocationsById(Integer id) {
		return locationDao.getLocationsById(id);
	}

	@Override
	public List<Location> getLocationsByRequesterName(String requesterName) {
		return locationDao.getLocationsByRequesterName(requesterName);
	}

	@Override
	public List<Location> getLocationList() {
		return locationDao.getLocationList();
	}
	
	@Override
	public boolean setLocation(Location location) {
		return locationDao.setLocation(location);
	}

	@Override
	public boolean setMarketLocation(MarketLocation marketLocation) {
		return locationDao.setMarketLocation(marketLocation);
	}
	
	@Override
	public boolean setRequesterLocation(RequesterLocation requesterlocation) {
		return locationDao.setRequesterLocation(requesterlocation);
	}
}
