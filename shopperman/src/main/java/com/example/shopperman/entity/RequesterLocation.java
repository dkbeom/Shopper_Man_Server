package com.example.shopperman.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequesterLocation extends Location {
	// private String addr;        // 주소
	// private String mapX;        // X 좌표
	// private String mapY;        // Y 좌표
	private String requesterName;  // 사용자 이름
}
