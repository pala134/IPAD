package com.ipad.service.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.dao.board.BoardDao;
import com.ipad.dto.board.BoardDto;
import com.ipad.service.Service;

public class BoardListService implements Service{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			
		System.out.println("BoardListService 진입 성공" );
		
		
		//화면에 보여질 게시글의 개수를 지정
		int pageSize = 10;
		//현재 보여지고 있는 페이지의 넘버값
		String pageNum = request.getParameter("pageNum");
		//처음에는 페이지 값이 없기 때문에 null처리를 함
		if(pageNum == null) {
			pageNum = "1";
		}
		//전체 글의 개수를 지정
		int count = 0;
		
		//jsp 페이지 내에서 보여질 넘버링 숫자값을 저장하는 변수
		int number = 0;
		
		//현재 보여지고 있는 페이지 문자를 숫자로 변환
		int currentPage = Integer.parseInt(pageNum);
		
		//전체 게시글의 개수를 가져와야 하기에 데이터베이스 객체 생성
		BoardDao bDao = new BoardDao();
		count = bDao.getAllCount();
		
		//현재 보여질 페이지 시작 변호를 설정
		int startRow = (currentPage-1) * pageSize+1;
		int endRow = currentPage * pageSize;
		
		//최시글 10개를 기준으로 게시글을 리턴 받아주는 메소드 호출
		ArrayList<BoardDto> list = bDao.getAllBoard(startRow, endRow);
		number = count - (currentPage -1) * pageSize;
		
		//수정, 삭제 시 비밀번호가 틀렸다면
		String msg =(String)request.getAttribute("msg");
		
		
		//request에 담아서 BoardList.jsp로 넘겨줌
		request.setAttribute("list", list);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("msg", msg);
		
		
		
	

	}
	

}
