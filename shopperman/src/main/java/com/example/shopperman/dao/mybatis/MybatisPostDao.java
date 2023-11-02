package com.example.shopperman.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopperman.dao.PostDao;
import com.example.shopperman.entity.Post;

@Repository("postDao")
@Transactional
public class MybatisPostDao implements PostDao {

private PostDao mapper;
	
	@Autowired
    public MybatisPostDao(SqlSession sqlSession) {
        mapper = sqlSession.getMapper(PostDao.class);
    }
	
	// ---------------------------------------------------------------------------------
	
	@Override
	public void insertPost(Post post) {
		mapper.insertPost(post);
	}

	@Override
	public Post getPost(Integer id) {
		return mapper.getPost(id);
	}
	
	@Override
	public List<Post> getPostList() {
		return mapper.getPostList();
	}

	@Override
	public boolean deletePost(Integer id) {
		return mapper.deletePost(id);
	}
}
