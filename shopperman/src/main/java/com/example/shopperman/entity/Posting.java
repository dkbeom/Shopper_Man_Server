package com.example.shopperman.entity;

import lombok.Data;

@Data
public class Posting {
	private String title;                   // 제목
	private RequesterLocation memberLocation;  // 유저 위치
	private MarketLocation marketLocation;  // 가게 위치
	
	// 가격
}