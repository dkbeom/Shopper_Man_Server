package com.example.shopperman.dao.mybatis;

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

}
