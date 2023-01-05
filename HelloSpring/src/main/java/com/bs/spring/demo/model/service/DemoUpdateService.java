package com.bs.spring.demo.model.service;

import com.bs.spring.demo.model.vo.Demo;

public interface DemoUpdateService {
	
	//리스트 반환용
	Demo selectDemo(int no);
	// 업데이트용
	int updateDemo(Demo d);
}
