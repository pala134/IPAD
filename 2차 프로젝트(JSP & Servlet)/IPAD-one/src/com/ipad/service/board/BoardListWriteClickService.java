package com.ipad.service.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.member.MemberDao;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;

public class BoardListWriteClickService implements Service{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//로그인 유저의 아디 패스워드값 받아옴
		
		
		MemberDao mDao = new MemberDao();
		MemberDto mDto = new MemberDto();
		HttpSession session = request.getSession();
		MemberDto loggedInUser = (MemberDto) session.getAttribute("loggedInUser");
		request.setAttribute("loggedInUser", loggedInUser);
		
		

		
		
		
	 
		if(loggedInUser != null) {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>window.location.href='" + request.getContextPath() + "/board/boardWrite.do';</script>");
				out.flush();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		
		}
		
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("<script>alert('회원만 이용 가능합니다.'); location.href='boardList.do';</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
