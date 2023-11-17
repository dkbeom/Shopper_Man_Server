package com.example.shopperman.dao;

import java.util.List;

import com.example.shopperman.entity.Post;

public interface PostDao {

	// 게시물 등록
	void insertPost(Post post);

	// 게시물 리스트 가져오기
	List<Post> getPostList();
	
	// 특정 게시물 가져오기
	Post getPost(Integer id);

	// 게시물 삭제
	boolean deletePost(Integer id);

	// 게시자 찾기
	String getPublisherNicknameByPostId(Integer id);
}
