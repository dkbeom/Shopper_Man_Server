package com.example.shopperman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.Member;
import com.example.shopperman.entity.Post;
import com.example.shopperman.service.LocationService;
import com.example.shopperman.service.MemberService;
import com.example.shopperman.service.PostService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("/location")
	public List<Location> getLocationList(){
		return locationService.getLocationList();
	}
	
	@GetMapping("/member")
	public List<Member> getMemberList(){
		return memberService.getMemberList();
	}
	
	@GetMapping("/post")
	public List<Post> getPostList(){
		return postService.getPostList();
	}
}