package com.example.shopperman.entity;

import lombok.Data;

@Data
public class Post {
	private Integer id;                          // 시퀀스 번호
	private String title;                        // 제목
	private String item;                         // 심부름 받을 물건
	private Integer price;                       // 물건 가격
	private Integer deliveryTip;                 // 배달팁
	private RequesterLocation requesterLocation; // 심부름 요청자의 위치
	private MarketLocation marketLocation;       // 가게 위치
}