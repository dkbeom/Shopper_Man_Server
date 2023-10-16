package com.example.shopperman.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopperman.dao.LocationDao;
import com.example.shopperman.entity.Location;
import com.example.shopperman.entity.MarketLocation;
import com.example.shopperman.entity.RequesterLocation;

@Repository("locationDao")
@Transactional
public class MybatisLocationDao implements LocationDao {

	private LocationDao mapper;
	
	@Autowired
    public MybatisLocationDao(SqlSession sqlSession) {
        mapper = sqlSession.getMapper(LocationDao.class);
    }
	
	// ---------------------------------------------------------------------------------
	
	@Override
	public List<Location> getLocationsById(Integer id) {
		return mapper.getLocationsById(id);
	}

	@Override
	public List<Location> getLocationsByRequesterName(String requesterName) {
		return mapper.getLocationsByRequesterName(requesterName);
	}

	@Override
	public List<Location> getLocationList() {
		return mapper.getLocationList();
	}

	@Override
	public boolean setLocation(Location location) {
		return mapper.setLocation(location);
	}

	@Override
	public boolean setMarketLocation(MarketLocation marketLocation) {
		return mapper.setMarketLocation(marketLocation);
	}
	
	@Override
	public boolean setRequesterLocation(RequesterLocation requesterLocation) {
		return mapper.setRequesterLocation(requesterLocation);
	}
}
