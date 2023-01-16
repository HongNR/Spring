package com.bs.spring.jpa.model.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;
import com.bs.spring.jpa.model.entity.JpaMember;

@Service
public class JpaServiceImpl implements JpaService{
	
	private EntityManager em;
	private JpaDao dao;
	
	@Autowired
	public JpaServiceImpl(EntityManager em, JpaDao dao) {
		super();
		this.em = em;
		this.dao = dao;
	}

	@Override
	public void insertMember(JpaMember m) {
		//EntityManager가 트렌젝션을 시작하고 db와 연동해서 처리
		//EntityManager.getTransaction()메소드를 이용해서 트렌젝션 생성
		EntityTransaction et=em.getTransaction();
		et.begin();//트렌젝션실행!
		dao.insertMember(em, m);
		et.commit();
		
	}

	@Override
	public JpaMember selectMemberById(Long memberId) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		JpaMember m=dao.selectMemberById(em, memberId);
		et.commit();
		return m;
	}

	@Override
	public void updateMember(Map<String, Object> param, Long memberId) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.updateMember(em, param, memberId);
		et.commit();
	}

	@Override
	public void deleteMember(Long memberId) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.deleteMember(em, memberId);
		et.commit();
	}

	@Override
	public List<JpaMember> selectMemberAll() {
		return dao.selectMemberAll(em);
	}
	
	

	
}
