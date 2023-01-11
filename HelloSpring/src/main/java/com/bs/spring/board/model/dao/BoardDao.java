package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.vo.Board;

public interface BoardDao {
	
	int insertBoard(SqlSessionTemplate session,Board b);
	List<Board> selectBoardList(SqlSessionTemplate session,Map<String,Integer> page);
	int selectBoardCount(SqlSessionTemplate session);
	Board selectBoard(SqlSessionTemplate session,int boardNo);
}
