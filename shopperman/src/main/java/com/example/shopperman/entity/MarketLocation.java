package com.example.shopperman.entity;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MarketLocation extends Location {
	// private String addr;                       // 주소
	// private String mapX;                       // X 좌표
	// private String mapY;                       // Y 좌표
	private String marketName;                    // 가게 이름
	private ArrayList<String> requesterNameList;  // 심부름을 요청한 사용자 이름 리스트
}
