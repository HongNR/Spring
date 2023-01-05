package com.bs.spring.demo.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.vo.Demo;

@Repository
public class DemoUpdateDaoImpl implements DemoUpdateDao {
	
	@Override
	public Demo selectDemo(SqlSessionTemplate session, int no) {
		return session.selectOne("demoUpdate.selectDemo",no);
	}
	
	@Override
	public int updateDemo(SqlSessionTemplate session,Demo d) {
		return session.update("demoUpdate.updateDemo",d);
	}

}
