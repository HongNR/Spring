package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	private BoardDao dao;
	private SqlSessionTemplate session;
	
	public BoardServiceImpl(BoardDao dao,SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
//	@Transactional //트랜젝션 처리하기!!!!!!!!!!!
	public int insertBoard(Board b) {
		//1. 게시글 등록
		//2. 첨부파일 등록
//		int boardNo=0;
		log.debug("insert 전 "+b.getBoardNo());
		int result=dao.insertBoard(session, b);//보드를 insert하면 보드번호가 생김
//		if(result>0) boardNo=dao.selectBoardSeq(session);// 그 보드의번호를 가져옴
//		b.setBoardNo(boardNo);//보드객체에 번호 주입.
		
		log.debug("insert 후 "+b.getBoardNo());
		if(result>0) {	
			result=0;
			for(Attachment a : b.getFiles()) {
				a.setBoard(b);
				result+=dao.insertAttachment(session,a);
			}
			if(result!=b.getFiles().size()) {
				throw new RuntimeException();//rollback처리를 위한 예외처리
			}
		}else {
			throw new RuntimeException("입력실패!");//rollback처리를 위한 예외처리
		}
		return result; 
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
