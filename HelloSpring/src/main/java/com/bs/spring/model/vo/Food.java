package com.bs.spring.model.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
//어노테이션을 이용해서 SpringBean으로 등록하기
@Component
public class Food {
	private String name;
	private int price;
	private String type;
	
}
