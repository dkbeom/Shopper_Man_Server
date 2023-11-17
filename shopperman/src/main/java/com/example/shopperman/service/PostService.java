package com.example.shopperman.service;

import java.util.List;

import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.Post;
import com.example.shopperman.entity.RequesterLocation;

public interface PostService {
	
	// 게시물 등록 (요청자 주소, 가게 주소도 등록)
	void createPost(Post post);
	
	// 게시물 리스트 가져오기
	List<Post> getPostList();
	
	// 특정 게시물 가져오기
	Post getPost(Integer id);
	
	// 특정 게시물 삭제
	boolean deletePost(Integer id);

	// 게시물 게시자 찾기
	String getPublisherNicknameByPostId(Integer id);
	
	// 배달팁 계산
	Integer calculateDeliveryTip(RequesterLocation requesterLocation, MarketLocation marketLocation);
}
