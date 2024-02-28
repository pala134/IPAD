package com.ipad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ipad.service.Service;
import com.ipad.service.board.BoardListService;
import com.ipad.service.board.BoardListWriteClickService;
import com.ipad.service.board.BoardWriteCheckService;
import com.ipad.service.board.BoardWriteEidtService;
import com.ipad.service.board.BoardWriteUpdateCheckService;
import com.ipad.service.board.BoardWriteViewCheck;
import com.ipad.service.board.BoardWritedeletCheckService;


public class BoardController implements Controller {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		System.out.println("보드 컨트롤러 입장==============");
		String viewPage = "";
		Service service = null;

		if (com.equals("/board/boardList.do")) { //게시판의 목록 리스트 불러오기
			service = new BoardListService();
			service.execute(request, response);
			viewPage = "/jsp/board/boardList.jsp";
//			System.out.println("boardList 진입==============");
			
		}else if(com.equals("/board/boardListWriteClickAction.do")){ //게시판의 글쓰기를 눌렀을 때의 액션
			service = new BoardListWriteClickService();
			service.execute(request, response);
			
		}else if (com.equals("/board/boardWrite.do")) { //게시글 폼
			viewPage = "/jsp/board/boardWrite.jsp";
//			System.out.println("boardWrite 진입==============");
			
		}else if (com.equals("/board/boardWriteCheck.do")) { // 게시글 폼 저장 action
			service = new BoardWriteCheckService();
			service.execute(request, response);
//			System.out.println("boardWriteCheck 진입==============");
			
		}else if (com.equals("/board/boardWriteView.do")) { //게시목록  view 페이지 
			viewPage = "/jsp/board/boardWriteView.jsp";
//			System.out.println("boardWriteView 진입==============");
			
		}else if (com.equals("/board/boardWriteViewCheck.do")) { //게시목록 subject 제목 버튼 view 페이지 action
			service = new BoardWriteViewCheck();
			service.execute(request, response);
//			System.out.println("boardWriteViewCheck 진입============");

		}else if (com.equals("/board/boardWriteEidt.do")) { //게시글 수정 페이지 view 진행
			service = new BoardWriteEidtService();
			service.execute(request, response);
//			System.out.println("boardWriteViewCheck 진입============");

		}else if (com.equals("/board/boardWriteUpdate.do")) { //게시글 수정 페이지
			viewPage = "/jsp/board/boardWriteUpdate.jsp";
//			System.out.println("boardWriteUpdate 진입============");
			
		}else if (com.equals("/board/boardWriteUpdateCheck.do")) { //게시글 수정 action
			service = new BoardWriteUpdateCheckService();
			service.execute(request, response);
//			System.out.println("boardWriteUpdateCheck 진입============");
		}else if (com.equals("/board/boardWritedelet.do")) { //게시글  삭제 action
			service = new BoardWritedeletCheckService();
			service.execute(request, response);
//			System.out.println("boardWriteUpdateCheck 진입============");
		}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}


