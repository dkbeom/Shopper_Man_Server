package com.example.shopperman.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.RequesterLocation;
import com.example.shopperman.util.TspBackTrackingAlgorithm;
import com.example.shopperman.util.TspGreedyAlgorithm;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	// @Autowired
	// private LocationService locationService;
	
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
    	
    	MarketLocation sa = new MarketLocation();
    	sa.setAddr("Sa 주소");
    	sa.setMapX("1.0");
    	sa.setMapY("3.0");
    	ArrayList<String> saList = new ArrayList<>();
    	saList.add("AAA");
    	sa.setRequesterNameList(saList);
    	
    	MarketLocation sb = new MarketLocation();
    	sb.setAddr("Sb 주소");
    	sb.setMapX("3.0");
    	sb.setMapY("3.0");
    	ArrayList<String> sbList = new ArrayList<>();
    	sbList.add("BBB");
    	sb.setRequesterNameList(sbList);
    	
    	MarketLocation sc = new MarketLocation();
    	sc.setAddr("Sc 주소");
    	sc.setMapX("2.0");
    	sc.setMapY("5.0");
    	ArrayList<String> scList = new ArrayList<>();
    	scList.add("CCC");
    	sc.setRequesterNameList(scList);
    	
    	MarketLocation sd = new MarketLocation();
    	sd.setAddr("Sd 주소");
    	sd.setMapX("0.0");
    	sd.setMapY("-1.0");
    	ArrayList<String> sdList = new ArrayList<>();
    	sdList.add("DDD");
    	sd.setRequesterNameList(sdList);
    	
    	l.add(a);
    	l.add(b);
    	l.add(c);
    	l.add(d);
    	l.add(sa);
    	l.add(sb);
    	l.add(sc);
    	l.add(sd);
    	
    	// Greedy 알고리즘
        long startTime1 = System.currentTimeMillis();
    	TspGreedyAlgorithm tsp1 = new TspGreedyAlgorithm(l);
    	List<Location> hello1 = tsp1.getTspOrderedLocationList("0.0", "0.0");
    	long endTime1 = System.currentTimeMillis();
    	long duration1 = endTime1 - startTime1;
    	System.out.println("Greedy 알고리즘 => " + duration1 + "밀리초");
    	
    	// BackTracking 알고리즘
    	long startTime2 = System.currentTimeMillis();
    	TspBackTrackingAlgorithm tsp2 = new TspBackTrackingAlgorithm(l);
    	List<Location> hello2 = tsp2.getTspOrderedLocationList("0.0", "0.0");
    	long endTime2 = System.currentTimeMillis();
    	long duration2 = endTime2 - startTime2;
    	System.out.println("BackTracking 알고리즘 => " + duration2 + "밀리초");
    	
    	return "{\\\"result\\\" : \\\"COMPLETE\\\"}";
    }
}