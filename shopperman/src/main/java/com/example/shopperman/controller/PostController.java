package com.example.shopperman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/get")
	public Post getPost(Integer id){
		return postService.getPost(id);
	}
	
	@GetMapping("/get/list")
	public List<Post> getPostList(){
		return postService.getPostList();
	}
	
	@GetMapping("/delete")
	public String deletePost(Integer id) {
		if(postService.deletePost(id)) {
			return "{\"result\" : \"COMPLETE\"}";
		} else {
			return "{\"result\" : \"FAILURE\"}";
		}
	}
}
