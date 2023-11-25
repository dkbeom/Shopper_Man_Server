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
import com.example.shopperman.entity.Post;
import com.example.shopperman.entity.SortRequest;
import com.example.shopperman.service.MemberService;
import com.example.shopperman.service.PostService;
import com.example.shopperman.service.SecurityService;
import com.example.shopperman.util.TspBackTrackingAlgorithm;

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
		
		Location myLocation = sortRequest.getMyLocation();
		List<Integer> postIdList = sortRequest.getPostIdList();
		
		List<Post> postList = postService.getPostListByIdList(myLocation, postIdList);
		
		List<Location> locationList = new ArrayList<>();
		for(Post post : postList) {
			locationList.add(post.getRequesterLocation());
			locationList.add(post.getMarketLocation());
		}
    	
    	// Greedy 알고리즘
//		long startTime1 = System.currentTimeMillis();
//    	TspGreedyAlgorithm tsp1 = new TspGreedyAlgorithm(locationList);
//    	List<Location> orderedLocationList1 = tsp1.getTspOrderedLocationList(myLocation.getMapX(), myLocation.getMapY()); // 현재 위치 입력
//    	long endTime1 = System.currentTimeMillis();
//    	long duration1 = endTime1 - startTime1;
//    	System.out.println("Greedy 알고리즘 => " + duration1 + "밀리초");
//    	System.out.println();
    	
    	// BackTracking 알고리즘
    	long startTime2 = System.currentTimeMillis();
    	TspBackTrackingAlgorithm tsp2 = new TspBackTrackingAlgorithm(locationList);
    	List<Location> orderedLocationList2 = tsp2.getTspOrderedLocationList(myLocation.getMapX(), myLocation.getMapY()); // 현재 위치 입력
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
		
		for(Integer postId : postIdList) {
			if(postService.getState(postId) == 0) {
				// POST 테이블에 배달원 이름 적기
				boolean setDeliverymanNickname = postService.setDeliverymanNickname(postId, deliverymanNickname);
				
				// 게시글 State 바꾸기 (대기중 -> 배달중)
				boolean setState = postService.setState(postId, 1);
				
				// 하나라도 문제 생기면 FAILURE
				if(!setDeliverymanNickname || !setState) {
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
		if(postService.getDeliverymanNickname(postId).equals(myNickname)) {
			// 게시글 State 바꾸기 (배달중 -> 배달 완료 신청)
			if(postService.getState(postId) == 1) {
				if(postService.setState(postId, 2)) {
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
		if(postService.getPublisherNicknameByPostId(postId).equals(myNickname)) {
			
			// 게시글 State 바꾸기 (배달 완료 신청 -> 배달 완료 확인)
			if(postService.getState(postId) == 2) {
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
			if(deliverymanNickname == null || price == null || deliveryTip == null) {
				return "{\"result\" : \"FAILURE\"}";
			}
			memberService.addPoint(deliverymanNickname, price + deliveryTip);
			
			return "{\"result\" : \"COMPLETE\"}";
			
		} else {
			return "{\"result\" : \"FAILURE\"}";
		}
	}
}
