package com.example.shopperman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.LocationContainer;
import com.example.shopperman.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	
	// Location 리스트 가져오기
	@GetMapping("/get/list")
	public List<LocationContainer> getLocationsList() {
		return locationService.getLocationsList();
	}
	
	// Location id(숫자)를 이용해서, Location 가져오기
	@GetMapping("/getById")
	public LocationContainer getLocationsById(Integer id) {
		return locationService.getLocationsById(id);
	}
	
	// 심부름 요청자 이름으로 Location 리스트 찾기
	@GetMapping("/getByName/list")
	public List<LocationContainer> getLocationsByRequesterName(String requesterName){
		return locationService.getLocationsListByRequesterName(requesterName);
	}
	
	// 새로운 Location 넣기
	@PostMapping("/set/common")
	public String setLocation(@RequestBody Location location) {
	// 파라미터: addr, mapX, mapY
		
		if(locationService.setLocation(location)) {
			return "{\"result\" : \"SUCCESS\"}";
		} else {
			return "{\"result\" : \"FAILURE\"}";
		}
	}
}
