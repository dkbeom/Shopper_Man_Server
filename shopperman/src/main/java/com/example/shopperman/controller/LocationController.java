package com.example.shopperman.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.LocationContainer;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.RequesterLocation;
import com.example.shopperman.service.LocationService;
import com.example.shopperman.util.TspBackTrackingAlgorithm;
import com.example.shopperman.util.TspGreedyAlgorithm;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/tsp")
    public String tsp() {
    	
    	List<Location> l = new ArrayList<>();
    	
    	RequesterLocation a = new RequesterLocation();
    	a.setAddr("a 주소");
    	a.setMapX("2.0");
    	a.setMapY("1.0");
    	a.setRequesterName("AAA");
    	
    	RequesterLocation b = new RequesterLocation();
    	b.setAddr("b 주소");
    	b.setMapX("4.0");
    	b.setMapY("2.0");
    	b.setRequesterName("BBB");
    	
    	RequesterLocation c = new RequesterLocation();
    	c.setAddr("c 주소");
    	c.setMapX("-1.0");
    	c.setMapY("2.0");
    	c.setRequesterName("CCC");
    	
    	RequesterLocation d = new RequesterLocation();
    	d.setAddr("d 주소");
    	d.setMapX("0.0");
    	d.setMapY("5.0");
    	d.setRequesterName("DDD");
    	
    	RequesterLocation e = new RequesterLocation();
    	e.setAddr("e 주소");
    	e.setMapX("2.0");
    	e.setMapY("-1.0");
    	e.setRequesterName("EEE");
    	
    	RequesterLocation f = new RequesterLocation();
    	f.setAddr("f 주소");
    	f.setMapX("3.0");
    	f.setMapY("0.0");
    	f.setRequesterName("FFF");
    	
    	RequesterLocation g = new RequesterLocation();
    	g.setAddr("g 주소");
    	g.setMapX("-1.0");
    	g.setMapY("4.0");
    	g.setRequesterName("GGG");
    	
    	RequesterLocation h = new RequesterLocation();
    	h.setAddr("h 주소");
    	h.setMapX("0.0");
    	h.setMapY("1.0");
    	h.setRequesterName("HHH");
    	
    	
    	
    	
    	MarketLocation sa = new MarketLocation();
    	sa.setAddr("Sa 주소");
    	sa.setMapX("1.0");
    	sa.setMapY("3.0");
    	sa.setRequesterName("AAA");
    	
    	MarketLocation sb = new MarketLocation();
    	sb.setAddr("Sb 주소");
    	sb.setMapX("3.0");
    	sb.setMapY("3.0");
    	sb.setRequesterName("BBB");
    	
    	MarketLocation sc = new MarketLocation();
    	sc.setAddr("Sc 주소");
    	sc.setMapX("2.0");
    	sc.setMapY("5.0");
    	sc.setRequesterName("CCC");
    	
    	MarketLocation sd = new MarketLocation();
    	sd.setAddr("Sd 주소");
    	sd.setMapX("0.0");
    	sd.setMapY("-1.0");
    	sd.setRequesterName("DDD");
    	
    	MarketLocation se = new MarketLocation();
    	se.setAddr("Se 주소");
    	se.setMapX("5.0");
    	se.setMapY("5.0");
    	se.setRequesterName("EEE");
    	
    	MarketLocation sf = new MarketLocation();
    	sf.setAddr("Sf 주소");
    	sf.setMapX("-2.0");
    	sf.setMapY("0.0");
    	sf.setRequesterName("FFF");
    	
    	MarketLocation sg = new MarketLocation();
    	sg.setAddr("Sg 주소");
    	sg.setMapX("5.0");
    	sg.setMapY("0.0");
    	sg.setRequesterName("GGG");
    	
    	MarketLocation sh = new MarketLocation();
    	sh.setAddr("Sh 주소");
    	sh.setMapX("0.0");
    	sh.setMapY("2.0");
    	sh.setRequesterName("HHH");
    	
    	
    	l.add(a);
    	l.add(b);
    	l.add(c);
    	l.add(d);
    	l.add(e);
    	l.add(f);
//    	l.add(g);
//    	l.add(h);
    	
    	l.add(sa);
    	l.add(sb);
    	l.add(sc);
    	l.add(sd);
    	l.add(se);
    	l.add(sf);
//    	l.add(sg);
//    	l.add(sh);
    	
    	
    	// Greedy 알고리즘
        long startTime1 = System.currentTimeMillis();
    	TspGreedyAlgorithm tsp1 = new TspGreedyAlgorithm(l);
    	tsp1.getTspOrderedLocationList("0.0", "0.0");
    	long endTime1 = System.currentTimeMillis();
    	long duration1 = endTime1 - startTime1;
    	System.out.println("Greedy 알고리즘 => " + duration1 + "밀리초");
    	System.out.println();
    	
    	// BackTracking 알고리즘
    	long startTime2 = System.currentTimeMillis();
    	TspBackTrackingAlgorithm tsp2 = new TspBackTrackingAlgorithm(l);
    	tsp2.getTspOrderedLocationList("0.0", "0.0");
    	long endTime2 = System.currentTimeMillis();
    	long duration2 = endTime2 - startTime2;
    	System.out.println("BackTracking 알고리즘 => " + duration2 + "밀리초");
    	System.out.println();
    	
    	return "{\"result\" : \"COMPLETE\"}";
    }
	
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
