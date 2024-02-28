package com.ipad.service.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.board.BoardDao;
import com.ipad.dto.board.BoardDto;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;
public class BoardWriteCheckService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("BoardWriteService 진입 성공");
		
		HttpSession session = request.getSession();
		MemberDto writeUser = (MemberDto) session.getAttribute("loggedInUser");
		//세션에서 로그인 정보 확인

		String id = (writeUser != null) ? writeUser.getId() : null;
		System.out.println("BoardWriteCheckService  ID 체크 " + id);
		if(id != null && id.equals(writeUser.getId())) {
			System.out.println("BoardWriteService 진입 성공");
			
			// 데이터를 읽어드림
			BoardDto bDto = new BoardDto();
			bDto.setWriter(request.getParameter("writer"));
			bDto.setSubject(request.getParameter("subject"));
			bDto.setEmail(request.getParameter("email"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setContent(request.getParameter("content"));

			BoardDao bDao = new BoardDao();
			bDao.insertBoard(bDto);

			
			try {
				response.setContentType("text/html; charset=UTF-8");
	        	PrintWriter out;
				out = response.getWriter();
				out.println("<script>window.location.href='" + request.getContextPath() + "/board/boardList.do';</script>");
	        	out.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
			try {
				response.setContentType("text/html; charset=UTF-8");
	        	PrintWriter out;
				out = response.getWriter();
				out.println("<script>alert('회원이 아닙니다.'); location.href='loginPage.do';</script>");
	        	out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
		}

		
	}
		
	

}
