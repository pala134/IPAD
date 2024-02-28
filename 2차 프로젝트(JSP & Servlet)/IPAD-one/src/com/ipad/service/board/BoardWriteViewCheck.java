package com.ipad.service.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.board.BoardDao;
import com.ipad.dto.board.BoardDto;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;

public class BoardWriteViewCheck implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("boardWriteViewCheck 진입성공 =====");

		HttpSession session = request.getSession();
		MemberDto writeUser = (MemberDto) session.getAttribute("loggedInUser");
		String name = (writeUser != null) ? writeUser.getName() : null;
//		System.out.println("BoardWriteViewCheck  이름 체크 " + name);

		if (name != null && name.equals(writeUser.getName())) {
			System.out.println("boardWriteViewCheck  진입 성공 ");

			// boardlist.jsp의 num값을 리턴 받음
			int num = Integer.parseInt(request.getParameter("num"));
//			System.out.println("BoardWriteViewCheck num 확인 " + num);

			// 데이터베이스 접근
			BoardDao bDao = new BoardDao();
			// 하나의 게시글에 대한 정보를 리턴
			BoardDto bDto = bDao.getOneBoard(num);
			
			// 글 작성자의 아이디
		    String boardWriterId = bDto.getWriter();
		    
		    // 현재 로그인한 사용자가 글 작성자인지 확인
		    boolean isWriter = (writeUser != null && name.equals(boardWriterId));
//	        System.out.println("BoardWriteViewCheck  현재 로그인한 사용자가 글 작성자인지 확인 체크 " + isWriter);
				
	        session.setAttribute("isWriter", isWriter);
	        session.setAttribute("bDto", bDto);
	        
//			BoardDto bDtocheck = (BoardDto) request.getAttribute("bDto");
//			System.out.println("bDtocheck getContent 1 : " + bDtocheck.getContent());
//			System.out.println("bDtocheck getEmail 1 : " + bDtocheck.getEmail());
//			System.out.println("bDtocheck getSubject 1 : " + bDtocheck.getSubject());
//			System.out.println("bDtocheck getReadcount 1 : " + bDtocheck.getReadcount());

			

		} else {
			 // 비회원일 경우 또는 자기 게시글이 아닐 경우의 로직 추가
            int num = Integer.parseInt(request.getParameter("num"));
            BoardDao bDao = new BoardDao();
            BoardDto bDto = bDao.getOneBoard(num);

            session.setAttribute("isWriter", false);
            session.setAttribute("bDto", bDto);
//            BoardDto bDtocheck = (BoardDto) request.getAttribute("bDto");
//        	System.out.println("bDtocheck getContent 2 : " + bDtocheck.getContent());
//			System.out.println("bDtocheck getEmail 2 : " + bDtocheck.getEmail());
//			System.out.println("bDtocheck getSubject 2 : " + bDtocheck.getSubject());
//			System.out.println("bDtocheck getReadcount 2 : " + bDtocheck.getReadcount());
        }
		
		try {
			response.setContentType("text/html; charset=UTF-8");
        	PrintWriter out;
			out = response.getWriter();
			out.println("<script>location.href='boardWriteView.do';</script>");
        	out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
    }
}
		