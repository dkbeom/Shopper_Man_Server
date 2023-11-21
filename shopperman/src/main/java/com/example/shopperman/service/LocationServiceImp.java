package com.example.shopperman.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopperman.dao.LocationDao;
import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.LocationContainer;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.RequesterLocation;

@Service("locationService")
public class LocationServiceImp implements LocationService {

	@Autowired
	private LocationDao locationDao;

	// ---------------------------------------------------------------------------------

	@Override
	public List<LocationContainer> getLocationsList() {

		List<Integer> idList = locationDao.getIdList();
		List<RequesterLocation> requesterLocationList = locationDao.getRequesterLocationList();
		List<MarketLocation> marketLocationList = locationDao.getMarketLocationList();

		List<LocationContainer> locationContainerList = new ArrayList<>();
		for (Integer id : idList) {
			LocationContainer locationContainer = new LocationContainer();
			for (RequesterLocation requesterLocation : requesterLocationList) {
				if(requesterLocation.getId() == id) {
					locationContainer.setRequesterLocation(requesterLocation);
				}
			}
			for (MarketLocation marketLocation : marketLocationList) {
				if(marketLocation.getId() == id) {
					locationContainer.setMarketLocation(marketLocation);
				}
			}
			locationContainerList.add(locationContainer);
		}

		return locationContainerList;
	}
	
	@Override
	public LocationContainer getLocationsById(Integer id) {

		LocationContainer locationContainer = new LocationContainer();

		locationContainer.setRequesterLocation(locationDao.getRequesterLocationById(id));
		locationContainer.setMarketLocation(locationDao.getMarketLocationById(id));

		return locationContainer;
	}

	@Override
	public List<LocationContainer> getLocationsListByRequesterName(String requesterName) {
		
		List<Integer> idList = locationDao.getIdListByRequesterName(requesterName);
		List<RequesterLocation> requesterLocationList = locationDao.getRequesterLocationListByRequesterName(requesterName);
		List<MarketLocation> marketLocationList = locationDao.getMarketLocationListByRequesterName(requesterName);
		
		List<LocationContainer> locationContainerList = new ArrayList<>();
		for (Integer id : idList) {
			LocationContainer locationContainer = new LocationContainer();
			for (RequesterLocation requesterLocation : requesterLocationList) {
				if(requesterLocation.getId() == id) {
					locationContainer.setRequesterLocation(requesterLocation);
				}
			}
			for (MarketLocation marketLocation : marketLocationList) {
				if(marketLocation.getId() == id) {
					locationContainer.setMarketLocation(marketLocation);
				}
			}
			locationContainerList.add(locationContainer);
		}
		
		return locationContainerList;
	}

	@Override
	public boolean setLocation(Location location) {
		return locationDao.setLocation(location);
	}
	
	@Override
	public Integer calculateDistance(Location locationA, Location locationB) {
		
		BigDecimal locationADecimalX = new BigDecimal(locationA.getMapX());
		BigDecimal locationBDecimalX = new BigDecimal(locationB.getMapX());
		
		BigDecimal locationADecimalY = new BigDecimal(locationA.getMapY());
		BigDecimal locationBDecimalY = new BigDecimal(locationB.getMapY());
		
		BigDecimal x = locationADecimalX.subtract(locationBDecimalX).multiply(new BigDecimal(88));
    	BigDecimal y = locationADecimalY.subtract(locationBDecimalY).multiply(new BigDecimal(111));
    	BigDecimal x2 = x.pow(2);
    	BigDecimal y2 = y.pow(2);
    	MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
    	BigDecimal distanceDecimal = x2.add(y2).sqrt(mc);
    	
    	Integer distance = (int)(distanceDecimal.doubleValue() * 1000);
		
		return distance;
	}
}
