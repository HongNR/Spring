package com.bs.spring.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public void test() {
		System.out.println("dao - test() 실행");
	}

	@Override
	public Member selecetMemberById(SqlSessionTemplate session, Member m) {
		return session.selectOne("member.selecetMemberById",m);
	}
	
	@Override
	public int insertMember(SqlSessionTemplate session,Member m) {
		return session.insert("member.insertMember",m);
	}

	@Override
	public List<Member> selectMemberList(SqlSessionTemplate session) {
		return session.selectList("member.selectMemberList");
	}
	
	
}
