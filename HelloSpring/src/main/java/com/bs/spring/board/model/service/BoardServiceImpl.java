package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardDao dao;
	private SqlSessionTemplate session;
	
	public BoardServiceImpl(BoardDao dao,SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public int insertBoard(Board b) {
		return dao.insertBoard(session, b);
	}
	
	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}
	
	@Override
	public List<Board> selectBoardList(Map<String,Integer> page){
		return dao.selectBoardList(session,page);
	}
	
	@Override
	public Board selectBoard(int boardNo) {
		return dao.selectBoard(session,boardNo);
	}
}
