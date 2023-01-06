package com.bs.spring.member.service;

import com.bs.spring.member.vo.Member;

public interface MemberService {
	void test();
	Member selecetMemberById(Member m);
	int insertMember(Member m);
}
