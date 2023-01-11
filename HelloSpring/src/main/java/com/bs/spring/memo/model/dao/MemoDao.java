package com.bs.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoDao {
	List<Memo> selectMemoList(SqlSessionTemplate session);
	
	int insertMemo(SqlSessionTemplate session,Memo m);
	
	int selectMemoListCount(SqlSessionTemplate session);
	
	List<Memo> selectMemoListPage(SqlSessionTemplate session,Map<String,Integer> param);
}
