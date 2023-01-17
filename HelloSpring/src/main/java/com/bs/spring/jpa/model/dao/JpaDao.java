package com.bs.spring.jpa.model.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bs.spring.jpa.model.entity.Club;
import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;
import com.bs.spring.jpa.model.entity.Student;

public interface JpaDao {

	void insertMember(EntityManager em, JpaMember m);
	
	JpaMember selectMemberById(EntityManager em, Long memberId);
	
	void updateMember(EntityManager em, Map<String,Object> param, Long memberId);
	
	void deleteMember(EntityManager em, Long memberId);
	
	List<JpaMember> selectMemberAll(EntityManager em);
	
	List<JpaMember> selectMemberSearch(EntityManager em,Double height);
	
	void insertMember(EntityManager em);
	
	Major selectMajor(EntityManager em, Long no);
	
	void insertStudentClub(EntityManager em);
	
	Student selectStudent(EntityManager em,Long no);
	Club selectClub(EntityManager em,Long no);
}

