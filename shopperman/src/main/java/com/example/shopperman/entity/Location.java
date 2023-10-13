package com.example.shopperman.entity;

import lombok.Data;

@Data
public class Location {
	private String addr;  // 주소
	private String mapX;  // X 좌표
	private String mapY;  // Y 좌표
}
