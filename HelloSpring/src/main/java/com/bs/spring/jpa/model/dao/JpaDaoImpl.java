package com.bs.spring.jpa.model.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.model.entity.JpaMember;

@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void insertMember(EntityManager em, JpaMember m) {
		// 메개변수로 전달받은 멤버변수 저장하기
		em.persist(m);//영속성 컨텍스트에 매개변수의 entity를 저장

	}

	@Override
	public JpaMember selectMemberById(EntityManager em, Long memberId) {
		// TODO Auto-generated method stub
		return em.find(JpaMember.class, memberId);
	}

	@Override
	public void updateMember(EntityManager em, Map<String, Object> param, Long memberId) {
		JpaMember m=em.find(JpaMember.class, memberId);
		m.setAge((Integer)param.get("age"));
		m.setHeight((Double)param.get("height"));
		m.setIntro((String)param.get("intro"));
		em.persist(m);
	}

	@Override
	public void deleteMember(EntityManager em, Long memberId) {
		JpaMember m=em.find(JpaMember.class, memberId);
		em.remove(m);
	}

	@Override
	public List<JpaMember> selectMemberAll(EntityManager em) {
		// 전체조회하는 메소드를 제공하지 않음
		//JPQL구문을 이용해서 조회문을 작성해야한다.
		//JPQL은 java방식의 sql문 작성하는 방법 -> sql문 비슷함
		return em.createQuery("select m from JpaMember m",JpaMember.class).getResultList();
	}
	
	

}
