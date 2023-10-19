package com.example.shopperman.service;

import com.example.shopperman.entity.Post;

public interface PostService {
	
	// 게시물 등록 (요청자 주소, 가게 주소도 등록)
	void createPost(Post post);
	
	
}
