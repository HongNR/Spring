package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.vo.Board;
import com.bs.spring.memo.model.vo.Memo;

public interface BoardDao {
	int selectBoardCount(SqlSessionTemplate session);
	
	List<Board> selectBoardList(SqlSessionTemplate session,Map<String,Integer> param);
	
	Board selectBoardView(SqlSessionTemplate session,int boardNo);
}
