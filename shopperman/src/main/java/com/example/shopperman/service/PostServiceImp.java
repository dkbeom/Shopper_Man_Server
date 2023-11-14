package com.example.shopperman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopperman.dao.PostDao;
import com.example.shopperman.entity.Post;

@Service("postService")
@Transactional
public class PostServiceImp implements PostService {
	
	@Autowired
	private PostDao postDao;

	@Override
	public void createPost(Post post) {
		postDao.insertPost(post);
	}

	@Override
	public Post getPost(Integer id) {
		return postDao.getPost(id);
	}
	
	@Override
	public List<Post> getPostList() {
		return postDao.getPostList();
	}

	@Override
	public boolean deletePost(Integer id) {
		return postDao.deletePost(id);
	}

	@Override
	public String getPublisherNicknameByPostId(Integer id) {
		return postDao.getPublisherNicknameByPostId(id);
	}
}
