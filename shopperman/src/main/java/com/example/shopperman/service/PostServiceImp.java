package com.example.shopperman.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopperman.dao.LocationDao;
import com.example.shopperman.dao.PostDao;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.Post;
import com.example.shopperman.entity.RequesterLocation;

@Service("postService")
@Transactional
public class PostServiceImp implements PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private LocationDao locationDao;
	
	// ---------------------------------------------------------------------------------

	@Override
	public void createPost(Post post) {
		postDao.insertPost(post);
	}
	
	@Override
	public List<Post> getPostList() {
		
		List<Post> postList = postDao.getPostList();
		
		for(Post post : postList) {
			post.setRequesterLocation(locationDao.getRequesterLocationById(post.getId()));
			post.setMarketLocation(locationDao.getMarketLocationById(post.getId()));
		}
		
		return postList;
	}

	@Override
	public Post getPost(Integer id) {
		
		Post post = postDao.getPost(id);
		
		post.setRequesterLocation(locationDao.getRequesterLocationById(id));
		post.setMarketLocation(locationDao.getMarketLocationById(id));
		
		return post;
	}

	@Override
	public boolean deletePost(Integer id) {
		boolean deleteLocations = locationDao.deleteLocations(id);
		boolean deletePost = postDao.deletePost(id);
		return deleteLocations && deletePost;
	}

	@Override
	public String getPublisherNicknameByPostId(Integer id) {
		return postDao.getPublisherNicknameByPostId(id);
	}

	@Override
	public Integer calculateDeliveryTip(RequesterLocation requesterLocation, MarketLocation marketLocation) {
		
		BigDecimal requesterLocationDecimalX = new BigDecimal(requesterLocation.getMapX());
		BigDecimal marketLocationDecimalX = new BigDecimal(marketLocation.getMapX());
		
		BigDecimal requesterLocationDecimalY = new BigDecimal(requesterLocation.getMapY());
		BigDecimal marketLocationDecimalY = new BigDecimal(marketLocation.getMapY());
		
		BigDecimal x = requesterLocationDecimalX.subtract(marketLocationDecimalX).multiply(new BigDecimal(88));
    	BigDecimal y = requesterLocationDecimalY.subtract(marketLocationDecimalY).multiply(new BigDecimal(111));
    	BigDecimal x2 = x.pow(2);
    	BigDecimal y2 = y.pow(2);
    	MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
    	BigDecimal distance = x2.add(y2).sqrt(mc);
    	
    	// 거리에 따른 배달비 계산 (미터 단위)
    	Double distanceMeter = distance.doubleValue() * 1000;
    	
    	// 100m 당 100 포인트
    	Integer deliveryTip = (int)Math.round(distanceMeter / 100) * 100;
		
		return deliveryTip;
	}
}
