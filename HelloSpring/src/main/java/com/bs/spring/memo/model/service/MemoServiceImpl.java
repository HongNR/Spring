package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	private MemoDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public MemoServiceImpl(MemoDao dao,SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}
	@Override
	public List<Memo> selectMemoList() {
		return dao.selectMemoList(session);
	}
	@Override
	public List<Memo> selectMemoListPage(Map<String,Integer> param){
		return dao.selectMemoListPage(session, param);
	}
	
	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}


	@Override
	public int selectMemoListCount() {
		return dao.selectMemoListCount(session);
	}
	
	

}
