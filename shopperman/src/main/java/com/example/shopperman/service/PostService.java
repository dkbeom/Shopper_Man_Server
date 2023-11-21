package com.example.shopperman.service;

import java.util.List;

import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.Post;

public interface PostService {
	
	// 게시물 등록 (요청자 주소, 가게 주소도 등록)
	void createPost(Post post);
	
	// 게시물 리스트 가져오기
	List<Post> getPostList(Location location);
	
	// 특정 게시물 가져오기
	Post getPost(Location location, Integer id);
	
	// 특정 게시물 삭제
	boolean deletePost(Integer id);

	// 게시물 게시자 찾기
	String getPublisherNicknameByPostId(Integer id);
	
	// 거리에 따른 배달팁 계산
	Integer calculateDeliveryTip(Integer distance);
}
