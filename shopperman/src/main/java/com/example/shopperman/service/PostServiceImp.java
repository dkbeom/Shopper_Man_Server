package com.example.shopperman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopperman.dao.LocationDao;
import com.example.shopperman.dao.PostDao;
import com.example.shopperman.entity.Location;
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
	
	@Autowired
	private LocationService locationService;
	
	// ---------------------------------------------------------------------------------

	@Override
	public void createPost(Post post) {
		postDao.insertPost(post);
	}
	
	@Override
	public List<Post> getPostList(Location location) {
		
		// 모든 게시물을 최근 순으로 나열
		List<Post> allPostList = postDao.getPostList();
		
		List<Post> postList = new ArrayList<>();
		for(Post post : allPostList) {
			
			// 게시물이 10개가 넘어가면, 10개까지만 반환
			if(postList.size() >= 10) {
				return postList;
			}
			
			RequesterLocation requesterLocation = locationDao.getRequesterLocationById(post.getId());
			MarketLocation marketLocation = locationDao.getMarketLocationById(post.getId());
			
			Integer deliveryToRequesterDistance = null;
			if(requesterLocation != null) {
				// 배달하는 사람과 배달받을 사람 사이의 거리(미터)
				deliveryToRequesterDistance = locationService.calculateDistance(location, requesterLocation);
			}
			Integer deliveryToMarketDistance = null;
			if(marketLocation != null) {
				// 배달하는 사람과 가게 사이의 거리(미터)
				deliveryToMarketDistance = locationService.calculateDistance(location, marketLocation);
			}
			
			// 거리 제한?
			// deliveryToRequesterDistance
			// deliveryToMarketDistance
			
			post.setRequesterLocation(requesterLocation);
			post.setMarketLocation(marketLocation);
			post.setDeliveryToRequesterDistance(deliveryToRequesterDistance);
			post.setDeliveryToMarketDistance(deliveryToMarketDistance);
			
			postList.add(post);
		}
		
		return postList;
	}

	@Override
	public Post getPost(Location location, Integer id) {
		
		Post post = postDao.getPost(id);
		
		if(post == null) {
			return null;
		}
		
		RequesterLocation requesterLocation = locationDao.getRequesterLocationById(post.getId());
		MarketLocation marketLocation = locationDao.getMarketLocationById(post.getId());
		
		Integer deliveryToRequesterDistance = null;
		if(requesterLocation != null) {
			// 배달하는 사람과 배달받을 사람 사이의 거리(미터) 삽입
			deliveryToRequesterDistance = locationService.calculateDistance(location, requesterLocation);
		}
		Integer deliveryToMarketDistance = null;
		if(marketLocation != null) {
			// 배달하는 사람과 가게 사이의 거리(미터)
			deliveryToMarketDistance = locationService.calculateDistance(location, marketLocation);
		}
		
		post.setRequesterLocation(locationDao.getRequesterLocationById(id));
		post.setMarketLocation(locationDao.getMarketLocationById(id));
		post.setDeliveryToRequesterDistance(deliveryToRequesterDistance);
		post.setDeliveryToMarketDistance(deliveryToMarketDistance);
		
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
	public Integer calculateDeliveryTip(Integer distance) {
    	
    	// 1000m 당 3000 포인트
    	Integer deliveryTip = (int)Math.round((double)distance / 1000) * 3000;
		
		return deliveryTip;
	}
}
