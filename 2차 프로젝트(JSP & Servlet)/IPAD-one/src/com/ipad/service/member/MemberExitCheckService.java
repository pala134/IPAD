package com.ipad.service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.member.MemberDao;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;


public class MemberExitCheckService implements Service {

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("MemberExitCheckService 입장 완료");

        MemberDao mDao = new MemberDao();

        HttpSession session = request.getSession();
        MemberDto loggedInUser = (MemberDto) session.getAttribute("loggedInUser");

        String id = loggedInUser.getId();
        String pass = loggedInUser.getPass1();
        String pass1 = request.getParameter("pass1");

        try {
            if (pass.equals(pass1)) {
                int exitResult = mDao.delete(id, pass);
                if (exitResult > 0) {
                    System.out.println("탈퇴 성공");
                    session.invalidate(); // 세션 무효화
                    session = request.getSession(); // 새로운 세션 생성
                    response.setContentType("text/html; charset=UTF-8");
            		PrintWriter out = response.getWriter();
            		out.println("<script>alert('탈퇴가 완료되었습니다.'); location.href='/IPAD/main.do';</script>");
            		out.flush();
                
                   
                } 
                
                
            } else {
                session.setAttribute("exitMessage", "*비밀번호가 틀렸습니다. 다시 입력해주세요.*");
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>window.location.href ='" + request.getContextPath() + "/member/mypageExit.do';</scrpt>");
                out.flush();
                
               
               
            }
        
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
    }
}