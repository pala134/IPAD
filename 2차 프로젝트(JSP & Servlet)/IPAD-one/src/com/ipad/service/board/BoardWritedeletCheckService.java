package com.ipad.service.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.board.BoardDao;
import com.ipad.dao.member.MemberDao;
import com.ipad.dto.board.BoardDto;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;
import com.sun.net.httpserver.Authenticator.Result;

public class BoardWritedeletCheckService implements Service{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 글삭제이기에 글번호를 입력
		int num =  Integer.parseInt(request.getParameter("num"));
		
		//데이터베이스 접근하여 하나의 게시글을 리턴하는 메소드
		BoardDao bDao = new BoardDao();
		
		//빈클래스 타입으로 리턴
		BoardDto bDto = bDao.getoneUpdateBoard(num); //조회수를 증가시키지 않는 메소드
		
		MemberDao mDao = new MemberDao();
		HttpSession session = request.getSession();
		MemberDto deletUser = (MemberDto) session.getAttribute("loggedInUser");
		
		String id = deletUser.getId();
		String pass1 = deletUser.getPass1();
		String password = request.getParameter("password");
		
		if (pass1.equals(password)) {
			int deletBoard = bDao.deleteBoard(num);
			if(deletBoard > 0) {
				
				try {
					response.setContentType("text/html; charset=UTF-8");
				    PrintWriter out;
					out = response.getWriter();
					out.println("<script>alert('회원님의 글이 삭제되었습니다.'); location.href='boardList.do';</script>");
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			}
		    // 비밀번호가 일치할 때 삭제 로직 수행
		    // 예: bDao.deleteBoard(num);
			
		} else {
		    // 비밀번호가 일치하지 않을 때의 처리
		    // 예: 비밀번호가 일치하지 않다는 메시지를 클라이언트에 전송
		   
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.go(-1);</script>");
			    out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
		
		
	}

}
