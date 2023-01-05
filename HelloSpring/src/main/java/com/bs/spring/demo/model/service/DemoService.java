package com.bs.spring.demo.model.service;

import java.util.List;

import com.bs.spring.demo.model.vo.Demo;

public interface DemoService {
	int insertDemo(Demo demo);
	
	List<Demo> selectDemoList();
	
}
