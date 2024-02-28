package com.ipad.service.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.board.BoardDao;
import com.ipad.dto.board.BoardDto;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;

public class BoardWriteUpdateCheckService implements Service {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("boardWriteUpdateCheckService 진입 완료");

        BoardDao bDao = new BoardDao();
        BoardDto bDto = new BoardDto();
        HttpSession session = request.getSession();
        MemberDto loggedInUser = (MemberDto) session.getAttribute("loggedInUser");
        String password = request.getParameter("password");
        System.out.println("boardWriteUpdateCheckService password 확인 : " + password);
        
   
        if (password != null && password.equals(loggedInUser.getPass1())) {
        	
           
            
            bDto.setEmail(request.getParameter("email"));
//            bDto.setEmail((String) session.getAttribute(loggedInUser.getEmail()));
            System.out.println("boardWriteUpdateCheckService Email 확인중 : " + bDto.getEmail());
            bDto.setSubject(request.getParameter("subject"));
            System.out.println("boardWriteUpdateCheckService subject 확인중 : " +bDto.getSubject());
            bDto.setContent(request.getParameter("content"));
            System.out.println("boardWriteUpdateCheckService content 확인중 : " +bDto.getContent());
            bDto.setNum(Integer.parseInt(request.getParameter("num")));
            
            
            int result = bDao.updateBoard(bDto);
       
            	
            if (result > 0) {
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('수정이 완료되었습니다.'); location.href='boardList.do';</script>");
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // DB 업데이트 실패 시 처리
                
				try {
					PrintWriter out;
					out = response.getWriter();
					out.print("<script>alert('수정 실패했습니다.'); history.go(-1);</script>");
		            out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
            }
        } else {
            // 비밀번호가 일치하지 않을 경우
            System.out.println("boardWriteUpdateCheckService session pass1 확인 : " + loggedInUser.getPass1());

            
			try {
				PrintWriter out;
				out = response.getWriter();
				out.print("<script>alert('비밀번호를 다시 입력해주세요.'); history.go(-1);</script>");
		        out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        }
    }
}