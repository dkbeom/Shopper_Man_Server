package com.example.shopperman.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.Post;
import com.example.shopperman.entity.RequesterLocation;
import com.example.shopperman.entity.SortRequest;
import com.example.shopperman.service.MemberService;
import com.example.shopperman.service.PostService;
import com.example.shopperman.service.SecurityService;
import com.example.shopperman.util.TspBackTrackingAlgorithm;
import com.example.shopperman.util.TspGreedyAlgorithm;

@RestController
@RequestMapping("/delivery")
@Transactional
public class DeliveryController {

	@Autowired
	private PostService postService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private SecurityService securityService;

	// 최단거리 계산
	@PostMapping("/sort")
	public List<Location> sort(@RequestBody SortRequest sortRequest) {
	// 파라미터: myLocation, postIdList
		
		 List<Location> locationList = new ArrayList<>();
		  
		 RequesterLocation a = new RequesterLocation();
		 a.setAddr("a 주소");
		 a.setMapX("2.0");
		 a.setMapY("1.0");
		 a.setId(1);
		  
		 RequesterLocation b = new RequesterLocation();
		 b.setAddr("b 주소");
		 b.setMapX("4.0");
		 b.setMapY("2.0");
		 b.setId(2);
		  
		 RequesterLocation c = new RequesterLocation();
		 c.setAddr("c 주소");
		 c.setMapX("-1.0");
		 c.setMapY("2.0");
		 c.setId(3);
		  
		 RequesterLocation d = new RequesterLocation();
		 d.setAddr("d 주소");
		 d.setMapX("0.0");
		 d.setMapY("5.0");
		 d.setId(4);
		 
		 RequesterLocation e = new RequesterLocation();
		 e.setAddr("e 주소");
		 e.setMapX("-3.0");
		 e.setMapY("6.0");
		 e.setId(5);
		 
		 RequesterLocation f = new RequesterLocation();
		 f.setAddr("f 주소");
		 f.setMapX("1.0");
		 f.setMapY("4.0");
		 f.setId(6);
		 
		 RequesterLocation g = new RequesterLocation();
		 g.setAddr("g 주소");
		 g.setMapX("3.0");
		 g.setMapY("4.0");
		 g.setId(7);
		  
		 MarketLocation sa = new MarketLocation();
		 sa.setAddr("Sa 주소");
		 sa.setMapX("1.0");
		 sa.setMapY("3.0");
		 ArrayList<String> saList = new ArrayList<>();
		 saList.add("AAA");
		 sa.setId(1);
		  
		 MarketLocation sb = new MarketLocation();
		 sb.setAddr("Sb 주소");
		 sb.setMapX("3.0");
		 sb.setMapY("3.0");
		 ArrayList<String> sbList = new ArrayList<>();
		 sbList.add("BBB");
		 sb.setId(2);
		  
		 MarketLocation sc = new MarketLocation();
		 sc.setAddr("Sc 주소");
		 sc.setMapX("2.0");
		 sc.setMapY("5.0");
		 ArrayList<String> scList = new ArrayList<>();
		 scList.add("CCC");
		 sc.setId(3);
		  
		 MarketLocation sd = new MarketLocation();
		 sd.setAddr("Sd 주소");
		 sd.setMapX("0.0");
		 sd.setMapY("-1.0");
		 ArrayList<String> sdList = new ArrayList<>();
		 sdList.add("DDD");
		 sd.setId(4);
		 
		 MarketLocation se = new MarketLocation();
		 se.setAddr("Se 주소");
		 se.setMapX("5.0");
		 se.setMapY("6.0");
		 ArrayList<String> seList = new ArrayList<>();
		 seList.add("EEE");
		 se.setId(5);
		 
		 MarketLocation sf = new MarketLocation();
		 sf.setAddr("Sf 주소");
		 sf.setMapX("-2.0");
		 sf.setMapY("-3.0");
		 ArrayList<String> sfList = new ArrayList<>();
		 sfList.add("FFF");
		 sf.setId(6);
		 
		 MarketLocation sg = new MarketLocation();
		 sg.setAddr("Sg 주소");
		 sg.setMapX("3.0");
		 sg.setMapY("6.0");
		 ArrayList<String> sgList = new ArrayList<>();
		 sgList.add("GGG");
		 sg.setId(7);
		  
		 locationList.add(a);
		 locationList.add(b);
		 locationList.add(c);
		 locationList.add(d);
		 locationList.add(e);
		 locationList.add(f);
		 
		 locationList.add(sa);
		 locationList.add(sb);
		 locationList.add(sc);
		 locationList.add(sd);
		 locationList.add(se);
		 locationList.add(sf);
		 
		 locationList.add(g);
		 locationList.add(sg);
		 
		 

		 //////////////////////////////////////////////////////////////////////
		 
		
//		Location myLocation = sortRequest.getMyLocation();
//		List<Integer> postIdList = sortRequest.getPostIdList();
//		
//		List<Post> postList = postService.getPostListByIdList(myLocation, postIdList);
//		
//		List<Location> locationList = new ArrayList<>();
//		for(Post post : postList) {
//			locationList.add(post.getRequesterLocation());
//			locationList.add(post.getMarketLocation());
//		}
    	
    	// Greedy 알고리즘
		long startTime1 = System.currentTimeMillis();
    	TspGreedyAlgorithm tsp1 = new TspGreedyAlgorithm(locationList);
//    	List<Location> orderedLocationList1 = tsp1.getTspOrderedLocationList(myLocation.getMapX(), myLocation.getMapY()); // 현재 위치 입력
    	List<Location> orderedLocationList1 = tsp1.getTspOrderedLocationList("0.0", "0.0"); // 현재 위치 입력
    	long endTime1 = System.currentTimeMillis();
    	long duration1 = endTime1 - startTime1;
    	System.out.println("Greedy 알고리즘 => " + duration1 + "밀리초");
    	System.out.println();
    	
    	// BackTracking 알고리즘
    	long startTime2 = System.currentTimeMillis();
    	TspBackTrackingAlgorithm tsp2 = new TspBackTrackingAlgorithm(locationList);
//    	List<Location> orderedLocationList2 = tsp2.getTspOrderedLocationList(myLocation.getMapX(), myLocation.getMapY()); // 현재 위치 입력
    	List<Location> orderedLocationList2 = tsp2.getTspOrderedLocationList("0.0", "0.0"); // 현재 위치 입력
    	long endTime2 = System.currentTimeMillis();
    	long duration2 = endTime2 - startTime2;
    	System.out.println("BackTracking 알고리즘 => " + duration2 + "밀리초");
    	System.out.println();
		
		return orderedLocationList2;
	}

	// 배달 시작
	@PostMapping("/start")
	public String start(@RequestHeader(value = "Authorization") String token, @RequestBody List<Integer> postIdList) {
		// 파라미터: POST id 리스트

		String deliverymanNickname = securityService.getSubject(token).get("nickname");

		for (Integer postId : postIdList) {
			if (postService.getState(postId) == 0) {
				// POST 테이블에 배달원 이름 적기
				boolean setDeliverymanNickname = postService.setDeliverymanNickname(postId, deliverymanNickname);

				// 게시글 State 바꾸기 (대기중 -> 배달중)
				boolean setState = postService.setState(postId, 1);

				// 하나라도 문제 생기면 FAILURE
				if (!setDeliverymanNickname || !setState) {
					return "{\"result\" : \"FAILURE\"}";
				}
			} else {
				return "{\"result\" : \"FAILURE\"}";
			}
		}

		return "{\"result\" : \"COMPLETE\"}";
	}

	// 배달 완료 신청
	@GetMapping("/complete/request")
	public String requestCompletion(@RequestHeader(value = "Authorization") String token, Integer postId) {

		String myNickname = securityService.getSubject(token).get("nickname");

		// 현재 로그인된 사용자가 게시글의 배달원인 경우
		if (postService.getDeliverymanNickname(postId).equals(myNickname)) {
			// 게시글 State 바꾸기 (배달중 -> 배달 완료 신청)
			if (postService.getState(postId) == 1) {
				if (postService.setState(postId, 2)) {
					return "{\"result\" : \"COMPLETE\"}";
				}
			} else {
				return "{\"result\" : \"FAILURE\"}";
			}
		}

		return "{\"result\" : \"FAILURE\"}";
	}

	// 배달 완료 확인
	@GetMapping("/complete/confirm")
	public String confirmCompletion(@RequestHeader(value = "Authorization") String token, Integer postId) {

		String myNickname = securityService.getSubject(token).get("nickname");

		// 현재 로그인된 사용자가 게시글의 게시자인 경우
		if (postService.getPublisherNicknameByPostId(postId).equals(myNickname)) {

			// 게시글 State 바꾸기 (배달 완료 신청 -> 배달 완료 확인)
			if (postService.getState(postId) == 2) {
				if (!postService.setState(postId, 3)) {
					return "{\"result\" : \"FAILURE\"}";
				}
			} else {
				return "{\"result\" : \"FAILURE\"}";
			}

			// 배달원 포인트 증가
			String deliverymanNickname = postService.getDeliverymanNickname(postId);
			Integer price = postService.getPrice(postId);
			Integer deliveryTip = postService.getDeliveryTip(postId);
			if (deliverymanNickname == null || price == null || deliveryTip == null) {
				return "{\"result\" : \"FAILURE\"}";
			}
			memberService.addPoint(deliverymanNickname, price + deliveryTip);

			return "{\"result\" : \"COMPLETE\"}";

		} else {
			return "{\"result\" : \"FAILURE\"}";
		}
	}
}
