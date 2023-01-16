package com.bs.spring.jpa.model.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bs.spring.jpa.model.entity.JpaMember;

public interface JpaDao {

	void insertMember(EntityManager em, JpaMember m);
	
	JpaMember selectMemberById(EntityManager em, Long memberId);
	
	void updateMember(EntityManager em, Map<String,Object> param, Long memberId);
	
	void deleteMember(EntityManager em, Long memberId);
	
	List<JpaMember> selectMemberAll(EntityManager em);
	
}

