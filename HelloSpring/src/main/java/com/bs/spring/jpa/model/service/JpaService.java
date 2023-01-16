package com.bs.spring.jpa.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.jpa.model.entity.JpaMember;

public interface JpaService {
	
	void insertMember(JpaMember m);
	
	JpaMember selectMemberById(Long memberId);
	
	void updateMember(Map<String,Object> param, Long memberId);
	
	void deleteMember(Long memberId);
	
	List<JpaMember> selectMemberAll();
}
