package com.example.shopperman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.Post;
import com.example.shopperman.service.LocationService;
import com.example.shopperman.service.PostService;
import com.example.shopperman.service.SecurityService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private SecurityService securityService;
	
	
	// 게시물 등록
	@PostMapping("/create")
	public String createPost(@RequestHeader(value = "Authorization") String token, @RequestBody Post post) {
	// 파라미터: title, item, price, requesterLocation(addr, mapX, mapY), marketLocation(addr, mapX, mapY, marketName)
		
		String currentUserNickname = securityService.getSubject(token).get("nickname");
		if(currentUserNickname == null) {
			return "{\"result\" : \"FAILURE\"}";
		}
		post.setPublisherNickname(currentUserNickname);
		post.getRequesterLocation().setRequesterName(currentUserNickname);
		post.getMarketLocation().setRequesterName(currentUserNickname);
		
		// 배달팁 계산
		Integer distance = locationService.calculateDistance(post.getRequesterLocation(), post.getMarketLocation());
		Integer deliveryTip = postService.calculateDeliveryTip(distance);
		if(deliveryTip == null) {
			return "{\"result\" : \"FAILURE\"}";
		}
		post.setDeliveryTip(deliveryTip);
		
		postService.createPost(post);
		
		return "{\"result\" : \"COMPLETE\"}";
	}
	
	// 게시물 리스트 조회 (배달하려는 사람 주소 넘겨주는 경우)
	@PostMapping("/get/list")
	public List<Post> getPostList(@RequestBody Location location) {
		// 파라미터: mapX, mapY

		return postService.getPostList(location);
	}
	
	// 게시물 id로, 해당 게시물 하나 조회
	@PostMapping("/get")
	public Post getPost(@RequestBody Location location, Integer id){
		return postService.getPost(location, id);
	}
	
	// 게시물 삭제
	@GetMapping("/delete")
	public String deletePost(Integer id) {
		if(postService.deletePost(id)) {
			return "{\"result\" : \"COMPLETE\"}";
		} else {
			return "{\"result\" : \"FAILURE\"}";
		}
	}
}
