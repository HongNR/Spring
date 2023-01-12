package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.interceptor.PageFactory;
import com.bs.spring.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	private BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service=service;
	}
	
	@RequestMapping("/board.do")
	public ModelAndView selectBoardList(ModelAndView mv,
			@RequestParam(value="cPage", defaultValue="1")int cPage,
			@RequestParam(value="numPerpage", defaultValue="5")int numPerpage) {
		
		mv.addObject("boards",service.selectBoardList(
				Map.of("cPage",cPage,"numPerpage",numPerpage))
				);
		//페이징처리
		int totalData=service.selectBoardCount();
		mv.addObject("totalContents",totalData);
		mv.addObject("pageBar",PageFactory.getPage(cPage,numPerpage,totalData,"board.do"));
		
		mv.setViewName("board/boardlist");
		
		return mv;
	}
	
	@RequestMapping("/boardView.do")
	public ModelAndView selectBoard(ModelAndView mv,int boardNo) {
		mv.addObject("board",service.selectBoard(boardNo));
		mv.setViewName("board/boardView");
		
		return mv;
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite() {
		
		return "board/boardWrite";
	}
	
	@RequestMapping("/insertBoard.do")
	public ModelAndView insertBoard(ModelAndView mv,MultipartFile[] upFile,
			String boardTitle, String boardContent, String boardWriter,
			HttpSession session) {
//		log.debug(upFile[0].getName());
//		log.debug(upFile[0].getOriginalFilename());
//		log.debug(upFile[1].getName());
//		log.debug(upFile[1].getOriginalFilename());
		log.debug(boardTitle+boardContent+boardWriter);
		//파일 업로드하기
		//저장위치 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		//폴더 있는지 확인하고 폴더를 생성해주기
		File dir=new File(path);
		if(!dir.exists()) dir.mkdirs();
		List<Attachment> files=new ArrayList();
		
		for(MultipartFile f : upFile) {
			//리네임드규칙을 생성하기
//			if(!upFile.isEmpty()) {
			if(!f.isEmpty()) {
				//전송된 파일이 있다면....
				//파일 리네임처리 직접하기
//				String originalFileName=upFile.getOriginalFilename();
				String originalFileName=f.getOriginalFilename();
				String ext=originalFileName.substring(originalFileName.lastIndexOf("."));
				//중복되지 않는 이름 설정하는 값 지정하기
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rnd=(int)(Math.random()*10000)+1;
				String renameFile=sdf.format(System.currentTimeMillis())+"_"+rnd+ext;
				
				//파일 업로드하기
				try {
					//MultipartFile클래스가 제공해주는 메소드 이용해서 저장처리
//					upFile.transferTo(new File(path+renameFile));
					f.transferTo(new File(path+renameFile));
					files.add(Attachment.builder()
							.originalFilename(originalFileName)
							.renamedFilename(renameFile).build());
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Board b=Board.builder()
				.boardTitle(boardTitle)
				.boardContent(boardContent)
				.boardWriter(Member.builder().userId(boardWriter).build())
				.files(files)
				.build();
		
		int result=service.insertBoard(b);
		mv.addObject("msg",result>0?"게시글 등록 성공":"게시글 등록실패");
		mv.addObject("loc","/board/board.do");
		
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/filedown.do")
	public void fileDownload(String ori,String re
			,HttpServletResponse response//응답을 위함
			,HttpSession session
			,@RequestHeader(value="User-agent") String header) {//인코딩처리를 위한것
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+re);
		try(FileInputStream fis=new FileInputStream(downloadFile);
				BufferedInputStream bis=new BufferedInputStream(fis);){//속도향상
			ServletOutputStream sos=response.getOutputStream();//서버꺼
			
			//파일명 인코딩하기
			boolean isMS=header.contains("Trident")||header.contains("MSIE");
			String encodeFilename="";
			if(isMS) {
				encodeFilename=URLEncoder.encode(ori,"UTF-8");
				encodeFilename=encodeFilename.replaceAll("\\+", "%20");
			}else {
				encodeFilename=new String(ori.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment:filename=\""+encodeFilename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				sos.write(read);
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
