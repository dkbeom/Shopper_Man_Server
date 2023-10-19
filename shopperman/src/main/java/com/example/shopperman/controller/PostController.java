package com.example.shopperman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopperman.entity.Post;
import com.example.shopperman.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@PostMapping("/create")
	public String createPost(@RequestBody Post post) {
	// 파라미터: title, item, price, deliveryTip, requesterLocation(RequesterLocation 객체), marketLocation(MarketLocation 객체)
		
		postService.createPost(post);
		
		return "{\"result\" : \"COMPLETE\"}";
	}
	
	
}
