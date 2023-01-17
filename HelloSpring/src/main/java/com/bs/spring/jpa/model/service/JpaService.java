package com.bs.spring.jpa.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.jpa.model.entity.Club;
import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;
import com.bs.spring.jpa.model.entity.Student;

public interface JpaService {
	
	void insertMember(JpaMember m);
	
	JpaMember selectMemberById(Long memberId);
	
	void updateMember(Map<String,Object> param, Long memberId);
	
	void deleteMember(Long memberId);
	
	List<JpaMember> selectMemberAll();
	
	List<JpaMember> selectMemberSearch(Double height);
	void insertMember();
	
	Major selectMajor(Long no);
	
	void insertStudentClub();
	
	Student selectStudent(Long no);
	Club selectClub(Long no);
}
